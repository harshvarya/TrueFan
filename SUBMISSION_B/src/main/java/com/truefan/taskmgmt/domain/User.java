package com.truefan.taskmgmt.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private Integer id;
    private String email;
    private String name;

    private static final AtomicInteger KEY_GENERATOR = new AtomicInteger(0);

    public User() {
        id = KEY_GENERATOR.incrementAndGet();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
