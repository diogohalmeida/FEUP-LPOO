package com.aor.refactoring.example4;

public abstract class Person {
    protected final String name;
    protected final String phone;

    protected Person(String name, String phone){
        this.name= name;
        this.phone = phone;

    }
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
