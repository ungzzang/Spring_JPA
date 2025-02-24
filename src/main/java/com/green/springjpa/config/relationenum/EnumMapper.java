package com.green.springjpa.config.relationenum;

import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumMapper {
    private Map<String, List<EnumMapperValue>> factory = new LinkedHashMap<>();

    public void put(String key, Class<? extends EnumMapperType> e) { //EnumMapperType을 상속받은것만 제한. 없으면 모두 다 가능
        factory.put(key, toEnumValues(e));
    }

    //e.getEnumConstants(): SchoolTypeCode[] schoolTypeCodeArray = [SchoolTypeCode.ELEMENTARY, SchoolTypeCode.MIDDLE, SchoolTypeCode.HIGH ];
    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e) {
        return Arrays.stream(e.getEnumConstants())// Array to Stream
                .map(EnumMapperValue::new) // 같은 크기의 스트림을 만든다. 메소드 참조, .map(item -> new EnumMapperValue(item)) 이렇게 작성된 것과 같다.
                .toList(); // 최종연산
    }
    // 스트림 ( EnumMapperValue객체, EnumMapperValue객체, EnumMapperValue객체)
    // 첫번째 EnumMapperValue는 code="00101", value="초등학교"
    // 두번째 EnumMapperValue는 code = "00102", value="중학교"
    // 두번째 EnumMapperValue는 code = "00103", value="고등학교"
    // 최종연산은 toList니까 Stream -> ArrrayList바뀐다.

    public List<EnumMapperValue> get(String key) {
        return factory.get(key);
    }

}
