package com.safari.task.entity;

public class Task {
    private final long id;
    private final String name;
    private final boolean isDone;

    public Task(long id, String name, boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public long getId() {
        return id;
    }
}
