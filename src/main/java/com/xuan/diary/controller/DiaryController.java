package com.xuan.diary.controller;

import com.xuan.diary.common.message.Message;
import com.xuan.diary.common.response.Response;
import com.xuan.diary.common.response.ResponseCode;
import com.xuan.diary.model.request.ContentDiaryRequest;
import com.xuan.diary.model.request.DiaryRequest;
import com.xuan.diary.model.response.DiaryResponse;
import com.xuan.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/diaries")
@Validated
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping()
    public Response<DiaryResponse> create(@Valid @RequestBody DiaryRequest diaryRequest) {
        DiaryResponse diaryResponse = diaryService.create(diaryRequest);
        return new Response<>(diaryResponse, ResponseCode.R_201, Message.RESPONSE_CREATE);
    }



}
