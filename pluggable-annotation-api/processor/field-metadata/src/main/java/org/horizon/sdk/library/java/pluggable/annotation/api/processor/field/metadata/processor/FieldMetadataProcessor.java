package org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.SneakyThrows;
import org.dromara.hutool.core.reflect.ClassUtil;
import org.horizon.sdk.library.java.contract.constant.library.Knife4jClassNames;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.*;
import org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata.FieldMetadata;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author wjm
 * @since 2025-04-16 02:39
 */
@AutoService(Processor.class)
public class FieldMetadataProcessor extends AbstractProcessor {

    private static final List<CharSequence> NEED_TO_REMOVE_CLASS_NAME_SUFFIXES = List.of(
            POJO.class.getSimpleName(),
            BO.class.getSimpleName(),
            PO.class.getSimpleName(),
            DO.class.getSimpleName(),
            VO.class.getSimpleName(),
            DTO.class.getSimpleName()
    );

    private static final boolean IS_KNIFE4J_SCHEMA_CLASS_EXIST = ClassUtil.isClassExists(Knife4jClassNames.SCHEMA, null);

    private static final List<String> LOMBOK_INNER_CLASS_NAME_SUFFIXES = Collections.ofImmutableList("Builder", "BuilderImpl");

    private static final Predicate<String> LOMBOK_INNER_CLASS_NAME_FILTER = className -> LOMBOK_INNER_CLASS_NAME_SUFFIXES.stream().noneMatch(className::endsWith);

    private static final String PLACEHOLDER = "$S";

    private static final String ENTITY_FIELD_CLASS_PACKAGE_NAME_SUFFIX = ".field";

    private static final String ENTITY_FIELD_CLASS_NAME_SUFFIX = "EntityField";

    private static final String METHOD_GET_PREFIX = "get";

    private static final String CODE_RETURN_TEMPLATE = "return $L;";

    private static final String ENTITY_FIELD_CLASS_FIELD_NAME_NAME = "name";

    private static final String ENTITY_FIELD_CLASS_FIELD_COMMENT_NAME = "comment";

    private Elements elementUtil;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.elementUtil = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.ofImmutableSet(FieldMetadata.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> ignore, RoundEnvironment roundEnvironment) {
        roundEnvironment.getElementsAnnotatedWith(FieldMetadata.class)
                .stream()
                .filter(TypeElement.class::isInstance)
                .map(TypeElement.class::cast)
                .forEach(this::processEntityFieldClass);
        return true;
    }

    @SneakyThrows
    private void processEntityFieldClass(TypeElement typeElement) {
        String className = getEntityFieldClassName(typeElement);
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(EntityField.class)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build()
                );

        processCurrentAndParentClass(classBuilder, typeElement);

        String packageName = Strings.subBefore(this.elementUtil.getPackageOf(typeElement).toString(), SymbolConstant.DOT) + ENTITY_FIELD_CLASS_PACKAGE_NAME_SUFFIX;
        JavaFile.builder(packageName, classBuilder.build())
                .build()
                .writeTo(this.processingEnv.getFiler());
    }

    private void processCurrentAndParentClass(TypeSpec.Builder classBuilder, TypeElement typeElement) {
        // build current class fields and parent class fields
        Stream.iterate(typeElement, Nil::isNotNull, this::getSuperClass)
                .flatMap(element -> element.getEnclosedElements().stream()
                        .filter(enclosedElement -> enclosedElement.getKind() == ElementKind.FIELD)
                        .filter(fieldElement -> Collections.notContainsAny(fieldElement.getModifiers(), Modifier.STATIC, Modifier.FINAL, Modifier.TRANSIENT))
                )
                .forEach(fieldElement -> processClass(classBuilder, fieldElement));

        // build current class inner classes and parent class inner classes
        Stream.iterate(typeElement, Nil::isNotNull, this::getSuperClass)
                .flatMap(element -> element.getEnclosedElements().stream()
                        .filter(enclosedElement -> enclosedElement.getKind() == ElementKind.CLASS)
                        .filter(classElement -> LOMBOK_INNER_CLASS_NAME_FILTER.test(classElement.getSimpleName().toString()))
                )
                .forEach(innerClassElement -> processInnerClass(classBuilder, innerClassElement));
    }

    private void processClass(TypeSpec.Builder classBuilder, Element fieldElement) {
        String fieldName = fieldElement.getSimpleName().toString();
        String fieldComment = this.getFieldComment(fieldElement);
        String className = Strings.upperFirst(fieldElement.getSimpleName().toString());
        String underlineUpperCaseEntityFieldClassFieldNameName = Strings.underlineUpperCase(ENTITY_FIELD_CLASS_FIELD_NAME_NAME);
        String underlineUpperCaseEntityFieldClassFieldCommentName = Strings.underlineUpperCase(ENTITY_FIELD_CLASS_FIELD_COMMENT_NAME);
        assert underlineUpperCaseEntityFieldClassFieldNameName != null;
        assert underlineUpperCaseEntityFieldClassFieldCommentName != null;
        classBuilder.addType(TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build()
                )
                .addField(FieldSpec.builder(String.class, underlineUpperCaseEntityFieldClassFieldNameName)
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer(PLACEHOLDER, fieldName)
                        .build()
                )
                .addField(FieldSpec.builder(String.class, underlineUpperCaseEntityFieldClassFieldCommentName)
                        .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .initializer(PLACEHOLDER, fieldComment)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder(getMethodName(ENTITY_FIELD_CLASS_FIELD_NAME_NAME))
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addCode(CodeBlock.of(CODE_RETURN_TEMPLATE, underlineUpperCaseEntityFieldClassFieldNameName))
                        .returns(String.class)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder(getMethodName(ENTITY_FIELD_CLASS_FIELD_COMMENT_NAME))
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addCode(CodeBlock.of(CODE_RETURN_TEMPLATE, underlineUpperCaseEntityFieldClassFieldCommentName))
                        .returns(String.class)
                        .build()
                )
                .build()
        );
    }

    private void processInnerClass(TypeSpec.Builder classBuilder, Element innerClassElement) {
        String innerClassName = getEntityFieldClassName(innerClassElement);
        TypeSpec.Builder innerClassBuilder = TypeSpec.classBuilder(innerClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .addSuperinterface(EntityField.class)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build()
                );
        processCurrentAndParentClass(innerClassBuilder, (TypeElement) innerClassElement);
        classBuilder.addType(innerClassBuilder.build());
    }

    private String getFieldComment(Element fieldElement) {
        String fieldComment = Strings.removeBlank(this.elementUtil.getDocComment(fieldElement));
        if (Nil.isNull(fieldComment) && IS_KNIFE4J_SCHEMA_CLASS_EXIST) {
            Schema schema = fieldElement.getAnnotation(Schema.class);
            fieldComment = (Nil.isNull(schema)) ? SymbolConstant.EMPTY : schema.description();
        }
        fieldComment = Nil.isBlank(fieldComment) ? SymbolConstant.EMPTY : fieldComment;
        return fieldComment;
    }

    private static String getMethodName(String fieldName) {
        return METHOD_GET_PREFIX + Strings.upperFirst(fieldName);
    }

    private String getEntityFieldClassName(Element element) {
        return Strings.removeIfAnyEndWith(element.getSimpleName(), NEED_TO_REMOVE_CLASS_NAME_SUFFIXES) + ENTITY_FIELD_CLASS_NAME_SUFFIX;
    }

    private TypeElement getSuperClass(TypeElement typeElement) {
        TypeMirror superClassMirror = typeElement.getSuperclass();
        if (TypeKind.DECLARED == superClassMirror.getKind()) {
            Element superElement = this.processingEnv.getTypeUtils().asElement(superClassMirror);
            if (superElement instanceof TypeElement superTypeElement && isNotObjectClass(superTypeElement)) {
                return superTypeElement;
            }
        }
        return null;
    }

    private boolean isObjectClass(TypeElement typeElement) {
        return typeElement.getQualifiedName().toString().equals(Object.class.getName());
    }

    private boolean isNotObjectClass(TypeElement typeElement) {
        return !isObjectClass(typeElement);
    }

}