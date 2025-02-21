package com.green.springjpa.config.relationenum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@RequiredArgsConstructor
@Converter(autoApply = true)
public abstract class AbstractEnumCodeConverter<E extends Enum<E> & EnumMapperType>
        implements AttributeConverter<E, String> {
    private final Class<E> targetEnumClass;
    private final boolean nullable; //null가능
    private final String enumName; //문제가 생겼을 때 ENUM때문인지 출력하기 위해 사용

    //DB에 값을 넣을 때 사용 (insert, update)
    @Override
    public String convertToDatabaseColumn(E enumItem) {
        if(!nullable && enumItem == null) { //null허용을 하지 않았는데 attribute값이 null이었다면
            throw new IllegalArgumentException(String.format("%s(는)은 NULL로 저장할 수 없습니다.", enumName));
        }
        return EnumConvertUtils.toCode(enumItem);
    }

    //DB에서 값을 가져올 때 (select)
    @Override
    public E convertToEntityAttribute(String dbData) {
        if(!nullable && StringUtils.isBlank(dbData) || dbData == null) {
            throw new IllegalArgumentException(String.format("%s(는)가 DB에 NULL 혹은 Empty로 저장되어 있습니다.", enumName));
        }
        return EnumConvertUtils.ofCode(targetEnumClass, dbData);
    }


}
