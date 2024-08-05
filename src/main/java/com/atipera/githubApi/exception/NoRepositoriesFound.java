package com.atipera.githubApi.exception;

public class NoRepositoriesFound extends RuntimeException {
    public NoRepositoriesFound(String message) {
        super(message);
    }

}
