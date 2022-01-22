package com.youtube.tracker.models.request;

import com.youtube.tracker.enums.PartType;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class TrackerRequest {
    @NotBlank
    String keyword;
    @NotNull
    Integer interval;
    @NotNull
    PartType type;
    @NotEmpty
    List<String> channels;
    String targetChannel;
}
