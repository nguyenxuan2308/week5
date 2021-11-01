package com.xuan.diary.model.entity;

import com.xuan.diary.common.status.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "users")
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null")
    private String name;

    @Column(name = "user_name", columnDefinition = "varchar(50)")
    private String userName;

    @Column(name = "password", columnDefinition = "varchar(50)")
    private String password;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;
}
