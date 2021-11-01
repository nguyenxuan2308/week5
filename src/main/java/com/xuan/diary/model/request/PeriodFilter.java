package com.xuan.diary.model.request;

import com.xuan.diary.common.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class PeriodFilter {

    @NotNull(message = Message.NOT_NULL)
    private LocalDateTime timeStart;

    @NotNull(message = Message.NOT_NULL)
    private LocalDateTime timeEnd;
}
