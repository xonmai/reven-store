package com.reven.onlinestore.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SystemErrorCode {

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND"),
    UNKNOWN_SYSTEM_EXCEPTION("SYSTEM_EXCEPTION");

    private final String name;
}
