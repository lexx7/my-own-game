package com.example.myowngame;

import lombok.Getter;

@Getter
public class StatePushMessage {
    private final boolean isEnabled;

    public StatePushMessage(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
