// package cn.srd.library.quality.check.style.core;
//
// import com.google.auto.service.AutoService;
//
// import javax.annotation.processing.AbstractProcessor;
// import javax.annotation.processing.Processor;
// import javax.annotation.processing.RoundEnvironment;
// import javax.annotation.processing.SupportedAnnotationTypes;
// import javax.lang.model.element.Element;
// import javax.lang.model.element.ElementKind;
// import javax.lang.model.element.TypeElement;
// import javax.tools.Diagnostic;
// import java.util.Set;
//
// @AutoService(Processor.class)
// @SupportedAnnotationTypes("*")
// public class CodeStyleCheckProcessor extends AbstractProcessor {
//
//     @Override
//     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//         for (Element element : roundEnv.getRootElements()) {
//             if (element.getKind() == ElementKind.CLASS) {
//                 // 判断是否有 @CodeStyleCheck 注解
//                 if (element.getAnnotation(CodeStyleCheck.class) != null) {
//                     checkClass((TypeElement) element);
//                 }
//             }
//         }
//         return true;
//     }
//
//     private void checkClass(TypeElement classElement) {
//         // 校验类名规范
//         String className = classElement.getSimpleName().toString();
//         if (!className.matches("^[A-Z][a-zA-Z]*$")) {
//             processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
//                     "Class name should match the pattern ^[A-Z][a-zA-Z]*$: " + className, classElement);
//         }
//
//         for (Element element : classElement.getEnclosedElements()) {
//             if (element.getKind() == ElementKind.METHOD) {
//                 // 校验方法名规范
//                 String methodName = element.getSimpleName().toString();
//                 if (!methodName.matches("^[a-z][a-zA-Z]*$")) {
//                     processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
//                             "Method name should match the pattern ^[a-z][a-zA-Z]*$: " + methodName, element);
//                 }
//             } else if (element.getKind() == ElementKind.FIELD) {
//                 // 校验字段名规范
//                 String fieldName = element.getSimpleName().toString();
//                 if (!fieldName.matches("^[a-z][a-zA-Z]*$")) {
//                     processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
//                             "Field name should match the pattern ^[a-z][a-zA-Z]*$: " + fieldName, element);
//                 }
//             }
//         }
//     }
// }
