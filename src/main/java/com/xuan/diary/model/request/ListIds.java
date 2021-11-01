package com.xuan.diary.model.request;

import com.xuan.diary.common.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ListIds {

    @NotNull(message = Message.NOT_NULL)
    private Set<Integer> ids;
}
