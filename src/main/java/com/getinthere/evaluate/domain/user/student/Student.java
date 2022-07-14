package com.getinthere.evaluate.domain.user.student;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.getinthere.evaluate.config.auth.SessionUser;
import com.getinthere.evaluate.domain.user.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Student implements SessionUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer no;
    private String name;
    private String birthday;
    private String state; // 취업, 중도탈락, 미이수, 이수, 재학중
    private LocalDateTime dropOutDate; // 그만둔 날짜
    private String dropOutReason;
    private String sign;

    @JoinColumn(name = "userId")
    @OneToOne
    private Users user;

    private String comment; // 학생 전체 Course 총평

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Builder
    public Student(Long id, Integer no, String name, String birthday, String state, LocalDateTime dropOutDate,
            String dropOutReason, Users user, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.birthday = birthday;
        this.state = state;
        this.dropOutDate = dropOutDate;
        this.dropOutReason = dropOutReason;
        this.user = user;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

}
