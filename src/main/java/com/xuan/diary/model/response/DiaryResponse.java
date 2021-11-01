package com.xuan.diary.model.response;

import com.xuan.diary.model.entity.Theme;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class DiaryResponse {
    private Integer id;

    private List<ThemeResponse> themes;

    private Integer userId;

    private String title;

    private String content;

    private boolean favoritesList;

    private LocalDateTime dateCreate;

    private LocalDateTime dateUpdate;
}
