package com.youtube.tracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartType {
    SNIPPET("snippet");

    private String value;
}
