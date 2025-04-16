package org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.SneakyThrows;
import org.dromara.hutool.core.reflect.ClassUtil;
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
import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Set;

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

    private static final String KNIFE4J_SCHEMA_CLASS_NAME = "io.swagger.v3.oas.annotations.media.Schema";

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
                .forEach(this::buildEntityFieldClass);
        return true;
    }

    @SneakyThrows
    private void buildEntityFieldClass(Element element) {
        String entityFieldClassName = getEntityFieldClassName(element);
        TypeSpec.Builder entityFieldClassBuilder = TypeSpec.classBuilder(entityFieldClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(EntityField.class)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build()
                );

        processClassElements(element, entityFieldClassBuilder);

        String packageName = Strings.subBefore(this.elementUtil.getPackageOf(element).toString(), SymbolConstant.DOT) + ENTITY_FIELD_CLASS_PACKAGE_NAME_SUFFIX;
        JavaFile.builder(packageName, entityFieldClassBuilder.build())
                .build()
                .writeTo(this.processingEnv.getFiler());
    }

    private void processClassElements(Element element, TypeSpec.Builder classBuilder) {
        // handle outer class field
        element.getEnclosedElements().stream()
                .filter(enclosedElement -> enclosedElement.getKind().isField())
                .filter(field -> Collections.notContainsAll(field.getModifiers(), Modifier.STATIC, Modifier.FINAL))
                .forEach(field -> {
                    String fieldName = field.getSimpleName().toString();
                    String fieldComment = this.getFieldComment(field);
                    String className = Strings.upperFirst(field.getSimpleName().toString());
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
                });

        // handle inner class
        element.getEnclosedElements().stream()
                .filter(enclosedElement -> enclosedElement.getKind() == ElementKind.CLASS)
                .forEach(innerClass -> {
                    String innerEntityFieldClassName = getEntityFieldClassName(innerClass);
                    TypeSpec.Builder innerClassBuilder = TypeSpec.classBuilder(innerEntityFieldClassName)
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .addMethod(MethodSpec.constructorBuilder()
                                    .addModifiers(Modifier.PRIVATE)
                                    .build()
                            );
                    processClassElements(innerClass, innerClassBuilder);
                    classBuilder.addType(innerClassBuilder.build());
                });
    }

    private String getFieldComment(Element field) {
        String fieldComment = Strings.removeBlank(this.elementUtil.getDocComment(field));
        if (Nil.isNull(fieldComment) && ClassUtil.isClassExists(KNIFE4J_SCHEMA_CLASS_NAME, null)) {
            Schema schema = field.getAnnotation(Schema.class);
            fieldComment = (Nil.isNull(schema)) ? SymbolConstant.EMPTY : schema.description();
        }
        fieldComment = Nil.isBlank(fieldComment) ? SymbolConstant.EMPTY : fieldComment;
        return fieldComment;
    }

    private static String getMethodName(String fieldName) {
        return METHOD_GET_PREFIX + Strings.upperFirst(fieldName);
    }

    private String getEntityFieldClassName(Element typeElement) {
        return Strings.removeIfAnyEndWith(typeElement.getSimpleName(), NEED_TO_REMOVE_CLASS_NAME_SUFFIXES) + ENTITY_FIELD_CLASS_NAME_SUFFIX;
    }

}