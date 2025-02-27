package org.horizon.sdk.library.java.tool.enums;

import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEnumAutowired(scanPackagePaths = {"org.horizon.sdk.library.*.java"})
@SpringBootApplication
public class ToolEnumsApplication {

}