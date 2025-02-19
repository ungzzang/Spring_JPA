package com.green.springjpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass //Entity 부모역할(상속되면서 작동되도록 하려면 필요함)
@EntityListeners(AuditingEntityListener.class) //이벤트 연결(binding), insert가 될 때 현재일시값을 넣자. 윈도우 키고 버튼누르고 등 뭔가 발생했을때를 이벤트라함.
public class CreatedAt {
    @CreatedDate //insert가 되었을 때 현재일시값을 넣는다. 이 애노테이션이 작동을 하려면 @EntityListeners세팅이 되어 있어야 한다.
    @Column(nullable = false) //이 애노테이션은 자동으로 작성이 되는데 설정을 좀 더 해주고 싶다면 이 애노테이션을 붙여야 한다, (nullable = false) 얘는 NotNull하고 싶을때 넣는다.
    private LocalDateTime createdAt;
}
