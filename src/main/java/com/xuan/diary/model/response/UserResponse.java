package com.xuan.diary.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    private Integer id;

    private String name;

    private String userName;

    private String password;
}
