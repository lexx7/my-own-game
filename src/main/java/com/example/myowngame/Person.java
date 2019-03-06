package com.example.myowngame;

import lombok.Getter;

@Getter
public class Person {
    private final String username;
    private final String dateTime;

    public Person(String username, String dateTime) {
        this.username = username;
        this.dateTime = dateTime;
    }
}
