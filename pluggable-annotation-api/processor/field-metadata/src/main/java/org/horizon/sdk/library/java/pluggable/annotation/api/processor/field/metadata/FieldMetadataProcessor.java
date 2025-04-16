package org.horizon.sdk.library.java.pluggable.annotation.api.processor.field.metadata;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.*;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
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

    private static final String ENTITY_FIELD_CLASS_PACKAGE_NAME_SUFFIX = ".field";

    private static final String ENTITY_FIELD_CLASS_NAME_SUFFIX = "EntityField";

    private static final String PLACEHOLDER = "$S";

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
    private void buildEntityFieldClass(Element typeElement) {
        String packageName = Strings.subBefore(this.elementUtil.getPackageOf(typeElement).toString(), SymbolConstant.DOT) + ENTITY_FIELD_CLASS_PACKAGE_NAME_SUFFIX;
        String entityFieldClassName = Strings.removeIfAnyEndWith(typeElement.getSimpleName(), NEED_TO_REMOVE_CLASS_NAME_SUFFIXES) + ENTITY_FIELD_CLASS_NAME_SUFFIX;

        TypeSpec.Builder entityFieldClassTypeSpecBuilder = TypeSpec.classBuilder(entityFieldClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build());

        // TODO wjm 修改包名

        typeElement.getEnclosedElements().stream()
                .filter(element -> element.getKind().isField())
                .filter(element -> Collections.notContains(element.getModifiers(), Modifier.STATIC))
                .filter(element -> Collections.notContains(element.getModifiers(), Modifier.FINAL))
                .forEach(field -> {
                    String fieldName = field.getSimpleName().toString();
                    String fieldComment = Strings.removeBlank(this.elementUtil.getDocComment(field));
                    if (Nil.isNull(fieldComment)) {
                        Schema schema = field.getAnnotation(Schema.class);
                        fieldComment = (Nil.isNotNull(schema)) ? schema.description() : SymbolConstant.EMPTY;
                    }
                    entityFieldClassTypeSpecBuilder.addType(TypeSpec.classBuilder(fieldName)
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .addMethod(MethodSpec.constructorBuilder()
                                    .addModifiers(Modifier.PRIVATE)
                                    .build())
                            .addField(FieldSpec.builder(String.class, "name")
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                    .initializer(PLACEHOLDER, fieldName)
                                    .build())
                            .addField(FieldSpec.builder(String.class, "comment")
                                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                    .initializer(PLACEHOLDER, fieldComment)
                                    .build())
                            .build()
                    );
                });

        JavaFile.builder(packageName, entityFieldClassTypeSpecBuilder.build())
                .build()
                .writeTo(processingEnv.getFiler());
    }

}