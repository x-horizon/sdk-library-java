package org.horizon.library.java.tool.enums;

import org.horizon.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEnumAutowired(scanPackagePaths = {"org.horizon.library.*.java"})
@SpringBootApplication
public class ToolEnumsApplication {

}