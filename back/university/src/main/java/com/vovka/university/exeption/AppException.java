package com.vovka.university.exeption;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppException extends RuntimeException {
    private final EventType event;

    public AppException(EventType event, String message) {
        super(message);
        this.event = event;
    }
}
