package cn.library.java.tool.enums;

import cn.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEnumAutowired(scanPackagePaths = {"cn.library.*.java"})
@SpringBootApplication
public class ToolEnumsApplication {

}