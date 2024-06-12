package cn.srd.library.java.tool.serialization.protobuf.google;

import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;

public class ProtobufExample {

    @SneakyThrows
    public static void main(String[] args) {
        // 创建一个 Person 对象  
        PersonProto.Person.Builder personBuilder = PersonProto.Person.newBuilder();
        personBuilder.setId(1234);
        personBuilder.setName("Alice");
        personBuilder.setEmail("alice@example.com");

        // 添加电话号码  
        PersonProto.Person.PhoneNumber.Builder phoneBuilder = PersonProto.Person.PhoneNumber.newBuilder();
        phoneBuilder.setNumber("123-456-7890");
        phoneBuilder.setType(PersonProto.Person.PhoneType.HOME);
        personBuilder.addPhones(phoneBuilder.build());

        // 构建并序列化 Person 对象  
        PersonProto.Person person = personBuilder.build();
        byte[] bytes = person.toByteArray();

        // 反序列化并打印 JSON 格式（可选，用于调试）  
        try {
            String jsonString = JsonFormat.printer().print(person);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ... 在这里你可以将 bytes 发送到网络或保存到文件等 ...  

        // 反序列化 Person 对象  
        PersonProto.Person deserializedPerson = PersonProto.Person.parseFrom(bytes);
        System.out.println(deserializedPerson.getName()); // 输出: Alice  
    }

}