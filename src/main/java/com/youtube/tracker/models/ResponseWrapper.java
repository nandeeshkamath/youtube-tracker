package com.youtube.tracker.models;

import com.youtube.tracker.constants.ResultInfoConstants;
import lombok.Value;

@Value
public class ResponseWrapper<T> {
    ResultInfo resultInfo;
    T data;

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, data);
    }

    public static <T> ResponseWrapper<T> success() {
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, null);
    }
}
