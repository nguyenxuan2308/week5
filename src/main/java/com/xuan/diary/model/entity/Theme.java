package com.xuan.diary.model.entity;

import com.xuan.diary.common.status.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "themes")
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null" )
    private String name;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;
}
