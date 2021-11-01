package com.xuan.diary.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class ThemeResponse {
    private Integer id;

    private String name;

}
