package cn.library.java.tool.validation.json.schema.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidateJsonSchemaTest {

    @Test
    void testValidateJsonSchema() {

    }

    @SneakyThrows
    public static void main(String[] args) {
        String schemaStr = """
                {
                    "$schema": "https://json-schema.org/draft/2020-12/schema",
                    "type": "object",
                    "properties": {
                        "name": {
                            "type": "string"
                        },
                        "age": {
                            "type": "integer",
                            "minimum": 0
                        }
                    },
                    "required": [
                        "name",
                        "age"
                    ]
                }
                """;

        String dataStr = """
                {
                  "name": "John",
                  "age": -1
                }
                """;

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        JsonSchema schema = schemaFactory.getSchema(schemaStr);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(dataStr);

        Set<ValidationMessage> validationResult = schema.validate(jsonNode);

        if (validationResult.isEmpty()) {
            System.out.println("JSON 数据验证成功！");
        } else {
            System.out.println("JSON 数据验证失败：");
            validationResult.forEach(vm -> System.out.println(vm.getMessage()));
        }
    }

}