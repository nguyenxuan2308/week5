package com.xuan.diary.model.entity;

import com.xuan.diary.common.status.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "diaries")
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
@Table
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", columnDefinition = "int default 0")
    private Integer userId;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "content", columnDefinition = "text not null")
    private String content;

    @Column(name = "favorites_list", columnDefinition = "boolean default true")
    private boolean favoritesList;

    @Column(name = "date_create", columnDefinition = "timestamp")
    @CreationTimestamp
    private LocalDateTime dateCreate;

    @Column(name = "date_update", columnDefinition = "timestamp")
    @UpdateTimestamp
    private LocalDateTime dateUpdate;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;

}
