package com.bci.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorMessage {
    private long timestamp;
    private int codigo;
    private String detail;
}
