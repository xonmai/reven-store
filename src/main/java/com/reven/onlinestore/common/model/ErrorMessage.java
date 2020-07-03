package com.reven.onlinestore.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {

    private String errorCode;

    private String message;

}
