package com.green.springjpa.config.relationenum;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumSet;


@NoArgsConstructor(access = AccessLevel.PRIVATE) //private 기본생성자 만드는거.
public class EnumConvertUtils {
    //enumClass: SchoolTypeCode.class, value: "00101" >> SchoolTypeCode.ELEMENTARY 타입이 리턴
    //enumClass: SchoolTypeCode.class, value: "00102" >> SchoolTypeCode.MIDDLE 타입이 리턴
    public static <E extends Enum<E> & EnumMapperType> E ofCode(Class<E> enumClass, String code) {
        if(StringUtils.isBlank(code)) { //isBlank: 널이거나 비어있거나.
            return  null;
        }

        return EnumSet.allOf(enumClass) //Enum이 가지고 있는 모든 아이템을 가진 콜렉션 리턴 (1)
               .stream() //(1)을 스트림 생성
               .filter(item -> item.getCode().equals(code)) //스트림 아이템 중에 원하는 아이템만 다시 스트림 생성한다. (2)  //filter: 원본이 3개라면 filter거쳐서 같거나 작아지게함.(predicate)
               .findFirst() //(2)스트림 아이템 중 첫번째 아이템을 리턴(Optional) (3)
               .orElse(null); //(3)이 null이었다면 null리턴
    }

    //enumItem으로 SchoolTypeCode.ELEMENTARY가 들어오면 "00101" 리턴
    //enumItem으로 SchoolTypeCode.MIDDLE가 들어오면 "00102" 리턴
    public static <E extends Enum<E> & EnumMapperType> String toCode(E enumItem) {
        if(enumItem == null) {
            return null;
        }
        return enumItem.getCode();
    }

}
