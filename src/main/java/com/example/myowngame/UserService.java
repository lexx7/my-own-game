package com.example.myowngame;

import java.util.List;

public interface UserService {
    String add(String username);

    String remove(String username);

    List<Person> list();

    void push(String username);

    void clearAll();

    void clearPush();
}
