package com.reven.onlinestore.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SystemErrorCode {

    INVALID_PARAM_REQUEST("INVALID_PARAM_REQUEST"),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND"),
    UNKNOWN_SYSTEM_EXCEPTION("SYSTEM_EXCEPTION");

    private final String name;
}
