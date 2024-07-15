// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.validation.json.schema.test;

import com.networknt.schema.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidateJsonSchemaTest {

    @Test
    void testValidateJsonSchema() {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);

        SchemaValidatorsConfig.Builder builder = SchemaValidatorsConfig.builder();
// By default the JDK regular expression implementation which is not ECMA 262 compliant is used
// Note that setting this requires including optional dependencies
// builder.regularExpressionFactory(GraalJSRegularExpressionFactory.getInstance());
// builder.regularExpressionFactory(JoniRegularExpressionFactory.getInstance());
        SchemaValidatorsConfig config = builder.locale(Locale.CHINESE).build();

// Due to the mapping the meta-schema will be retrieved from the classpath at classpath:draft/2020-12/schema.
        JsonSchema schema = jsonSchemaFactory.getSchema(SchemaLocation.of(SchemaId.V202012), config);
        String input = """
                {
                  "type": "object",
                  "properties": {
                    "key": {
                      "title" : "My key",
                      "type": "invalidtype"
                    }
                  }
                }
                """;
        Set<ValidationMessage> assertions = schema.validate(input, InputFormat.JSON, executionContext -> {
            // By default since Draft 2019-09 the format keyword only generates annotations and not assertions
            executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
        });

        System.out.println();

        // String schemaData = """
        //         {
        //           "$id": "https://schema/myschema",
        //           "properties": {
        //             "startDate": {
        //               "format": "date",
        //               "minLength": 6
        //             }
        //           }
        //         }
        //         """;
        // String inputData = """
        //         {
        //           "startDate": "1"
        //         }
        //         """;
        // JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012,
        //         builder -> builder.jsonNodeReader(JsonNodeReader.builder().locationAware().build()));
        // SchemaValidatorsConfig config = SchemaValidatorsConfig.builder().build();
        // JsonSchema schema = factory.getSchema(schemaData, InputFormat.JSON, config);
        // Set<ValidationMessage> messages = schema.validate(inputData, InputFormat.JSON, executionContext -> {
        //     executionContext.getExecutionConfig().setFormatAssertionsEnabled(true);
        // });
        // List<ValidationMessage> list = messages.stream().toList();
        // ValidationMessage format = list.get(0);
        // JsonLocation formatInstanceNodeTokenLocation = JsonNodes.tokenLocationOf(format.getInstanceNode());
        // JsonLocation formatSchemaNodeTokenLocation = JsonNodes.tokenLocationOf(format.getSchemaNode());
        // ValidationMessage minLength = list.get(1);
        // JsonLocation minLengthInstanceNodeTokenLocation = JsonNodes.tokenLocationOf(minLength.getInstanceNode());
        // JsonLocation minLengthSchemaNodeTokenLocation = JsonNodes.tokenLocationOf(minLength.getSchemaNode());
        //
        // Assertions.assertEquals("format", format.getType());
        // Assertions.assertEquals("date", format.getSchemaNode().asText());
        // Assertions.assertEquals(5, formatSchemaNodeTokenLocation.getLineNr());
        // Assertions.assertEquals(17, formatSchemaNodeTokenLocation.getColumnNr());
        // Assertions.assertEquals("1", format.getInstanceNode().asText());
        // Assertions.assertEquals(2, formatInstanceNodeTokenLocation.getLineNr());
        // Assertions.assertEquals(16, formatInstanceNodeTokenLocation.getColumnNr());
        // Assertions.assertEquals("minLength", minLength.getType());
        // Assertions.assertEquals("6", minLength.getSchemaNode().asText());
        // Assertions.assertEquals(6, minLengthSchemaNodeTokenLocation.getLineNr());
        // Assertions.assertEquals(20, minLengthSchemaNodeTokenLocation.getColumnNr());
        // Assertions.assertEquals("1", minLength.getInstanceNode().asText());
        // Assertions.assertEquals(2, minLengthInstanceNodeTokenLocation.getLineNr());
        // Assertions.assertEquals(16, minLengthInstanceNodeTokenLocation.getColumnNr());
        // Assertions.assertEquals(16, minLengthInstanceNodeTokenLocation.getColumnNr());
    }

}