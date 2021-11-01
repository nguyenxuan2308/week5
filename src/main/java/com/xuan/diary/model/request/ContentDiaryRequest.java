package com.xuan.diary.model.request;

import com.xuan.diary.common.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ContentDiaryRequest {

    @NotBlank(message = Message.NOT_BLANK)
    @Length(max = 2000)
    private String content;

    @NotNull(message = Message.NOT_NULL)
    private List<String> urlImages;

}
