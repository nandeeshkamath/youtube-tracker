package com.youtube.tracker.models;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ResultInfo {
    @NotBlank
    String id;
    @NotBlank
    String message;
}
