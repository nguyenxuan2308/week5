package com.xuan.diary.model.request;

import com.xuan.diary.common.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ThemeRequest {

    @NotBlank(message = Message.NOT_BLANK)
    @Length(max = 50)
    private String name;

    @NotBlank(message = Message.NOT_BLANK)
    @Length(max = 20)
    private String status;
}
