package com.xuan.diary.model.entity;

import com.xuan.diary.common.status.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "images")
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "diary_id", columnDefinition = "int default 0")
    private Integer diaryId;

    @Column(name = "url", columnDefinition = "varchar(50) not null")
    private String url;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;
}
