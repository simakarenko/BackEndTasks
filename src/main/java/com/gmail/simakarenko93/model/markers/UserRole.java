package com.gmail.simakarenko93.model.markers;

public enum UserRole {
    USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
