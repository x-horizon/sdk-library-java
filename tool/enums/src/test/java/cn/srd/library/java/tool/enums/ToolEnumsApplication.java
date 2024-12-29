package cn.srd.library.java.tool.enums;

import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEnumAutowired(scanPackagePaths = {"cn.srd.*.java"})
@SpringBootApplication
public class ToolEnumsApplication {

}