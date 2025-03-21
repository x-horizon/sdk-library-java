package org.horizon.sdk.library.java.tool.convert.jackson.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.*;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class FoolVO implements Serializable {

    @Serial private static final long serialVersionUID = -1787807505198100207L;

    @Builder.Default
    @JsonSerialize(using = JacksonFloatToStringSerializer.class)
    private Float floatNumberValue = 0.1F;

    @Builder.Default
    @JsonSerialize(using = JacksonDoubleToStringSerializer.class)
    private Double doubleNumberValue = 0.11;

    @Builder.Default
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long longNumberValue = 10000000000000L;

    @Builder.Default
    @JsonSerialize(using = JacksonListLongToListStringSerializer.class)
    private List<Long> longNumberValues = List.of(10000000000000L, 10000000000001L);

    @Builder.Default
    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private FoolType foolTypeIntegerValue = FoolType.FOOL1;

    @Builder.Default
    @JsonSerialize(using = JacksonEnumToLongSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private FoolType foolTypeLongValue = FoolType.FOOL1;

    @Builder.Default
    @JsonSerialize(using = JacksonEnumToStringSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private FoolType foolTypeStringValue = FoolType.FOOL1;

    @Builder.Default
    @JsonSerialize(using = JacksonListEnumToListIntegerSerializer.class)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    private List<FoolType> foolTypeListIntegerValues = List.of(FoolType.FOOL1);

    @Builder.Default
    @JsonSerialize(using = JacksonListEnumToListLongSerializer.class)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    private List<FoolType> foolTypeListLongValues = List.of(FoolType.FOOL1);

    @Builder.Default
    @JsonSerialize(using = JacksonListEnumToListStringSerializer.class)
    @JsonDeserialize(using = JacksonListEnumValueToListEnumDeserializer.class)
    private List<FoolType> foolTypeListStringValues = List.of(FoolType.FOOL1);

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(using = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime localDateTimeLongValue = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToStringMS0Serializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateTimeMS0Deserializer.class)
    private LocalDateTime localDateTimeStringMS0Value = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToStringMS1Serializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateTimeMS1Deserializer.class)
    private LocalDateTime localDateTimeStringMS1Value = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToStringMS2Serializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateTimeMS2Deserializer.class)
    private LocalDateTime localDateTimeStringMS2Value = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToStringMS3Serializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateTimeMS3Deserializer.class)
    private LocalDateTime localDateTimeStringMS3Value = LocalDateTime.now();

    // @Builder.Default
    // @JsonSerialize(using = JacksonLocalDateTimeToStringMS4Serializer.class)
    // @JsonDeserialize(using = JacksonStringToLocalDateTimeMS4Deserializer.class)
    // private LocalDateTime localDateTimeStringMS4Value = LocalDateTime.now();
    //
    // @Builder.Default
    // @JsonSerialize(using = JacksonLocalDateTimeToStringMS6Serializer.class)
    // @JsonDeserialize(using = JacksonStringToLocalDateTimeMS5Deserializer.class)
    // private LocalDateTime localDateTimeStringMS5Value = LocalDateTime.now();
    //
    // @Builder.Default
    // @JsonSerialize(using = JacksonLocalDateTimeToStringMS0Serializer.class)
    // @JsonDeserialize(using = JacksonStringToLocalDateTimeMS6Deserializer.class)
    // private LocalDateTime localDateTimeStringMS6Value = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateTimeToStringRFC3339Serializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateTimeRFC3339Deserializer.class)
    private LocalDateTime localDateTimeStringRFC3339Value = LocalDateTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalDateToStringSerializer.class)
    @JsonDeserialize(using = JacksonStringToLocalDateDeserializer.class)
    private LocalDate localDateStringValue = LocalDate.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalTimeToStringHourMinuteSerializer.class)
    @JsonDeserialize(using = JacksonStringToLocalTimeDeserializer.class)
    private LocalTime localTimeStringHourMinuteValue = LocalTime.now();

    @Builder.Default
    @JsonSerialize(using = JacksonLocalTimeToStringHourMinuteSecondSerializer.class)
    @JsonDeserialize(using = JacksonStringToLocalTimeDeserializer.class)
    private LocalTime localTimeStringHourMinuteSecondValue = LocalTime.now();

}