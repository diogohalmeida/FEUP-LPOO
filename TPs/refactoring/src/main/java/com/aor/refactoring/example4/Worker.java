package com.aor.refactoring.example4;

import java.util.Objects;

public class Worker extends Person{
    private final String username;
    private final String password;

    public Worker(String name, String phone, String username, String password) {
        super(name, phone);
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker))
            return false;
        Worker worker = (Worker) o;
        boolean name_bool = Objects.equals(name, worker.name);
        boolean phone_bool = Objects.equals(phone, worker.phone);
        boolean user_bool = Objects.equals(username, worker.username);
        boolean pass_bool = Objects.equals(password, worker.password);

        return name_bool && phone_bool && user_bool && pass_bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, username, password);
    }
}
