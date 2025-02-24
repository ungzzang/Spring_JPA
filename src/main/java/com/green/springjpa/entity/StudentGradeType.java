package com.green.springjpa.entity;


import com.green.springjpa.config.relationenum.AbstractEnumCodeConverter;
import com.green.springjpa.config.relationenum.EnumMapperType;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentGradeType implements EnumMapperType {

      GRADE_1("00201", "1학년")
    , GRADE_2("00202", "2학년")
    , GRADE_3("00203", "3학년")
    ;

    private final String code;
    private final String value;

    @Converter(autoApply = true) //@Converter(autoApply = true): StudentGradeType Enum을 사용하는 Entity는 자동으로 컨버터가 작동한다.
    public static class CodeConverter extends AbstractEnumCodeConverter<StudentGradeType> {
        public CodeConverter() {
            super(StudentGradeType.class, false);
        }
    }
}
