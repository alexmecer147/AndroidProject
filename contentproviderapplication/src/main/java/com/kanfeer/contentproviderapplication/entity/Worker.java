package com.kanfeer.contentproviderapplication.entity;

public class Worker {
    private int id;
    private String name;
    private int age;
    private int tall;

    public Worker() {
    }

    public Worker(int id, String name, int age, int tall) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.tall = tall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }
}
