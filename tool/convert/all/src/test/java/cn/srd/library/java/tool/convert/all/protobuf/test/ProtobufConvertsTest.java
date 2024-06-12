package cn.srd.library.java.tool.convert.all.protobuf.test;

import cn.srd.library.java.tool.convert.all.Converts;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProtobufConvertsTest {

    @SneakyThrows
    @Test
    void testProtobufConverts() {
        PersonProto.Person person = PersonProto.Person.newBuilder()
                .setId(1234)
                .setName("Alice")
                .setEmail("alice@example.com")
                .addPhones(PersonProto.Person.PhoneNumber.newBuilder()
                        .setNumber("123-456-7890")
                        .setType(PersonProto.Person.PhoneType.HOME)
                        .build()
                )
                .build();

        byte[] bytes = person.toByteArray();
        PersonProto.Person deserializedPerson = PersonProto.Person.parseFrom(bytes);

        String d = Converts.withProtobuf().toString(person);
        System.out.println(d);
        String a = Converts.withProtobuf().toJsonString(person);
        System.out.println(a);
        PersonProto.Person.Builder b = Converts.withProtobuf().toBuilder(a, PersonProto.Person.newBuilder());
        PersonProto.Person c = Converts.withProtobuf().toBean(a, PersonProto.Person.newBuilder());

        System.out.println();
    }

}