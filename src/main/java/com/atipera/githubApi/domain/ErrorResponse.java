package com.atipera.githubApi.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ErrorResponse {
    private int status;
    private String message;
}
