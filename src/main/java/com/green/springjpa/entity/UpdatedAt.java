package com.green.springjpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass //Entity 부모역할
@EntityListeners(AuditingEntityListener.class) // application의 @EnableJpaAuditing추가해서 활성화시킨다.
public class UpdatedAt extends CreatedAt {
    @LastModifiedDate //insert, update 되었을 때 현재일시값을 넣는다.(insert, update 모두 수정으로 본다.)
    @Column(nullable = false) //이 애노테이션은 자동으로 작성이 되는데 설정을 좀 더 해주고 싶다면 이 애노테이션을 붙여야 한다. (nullable = false) 얘는 NotNull하고 싶을때 넣는다.
    private LocalDateTime updatedAt;
}
