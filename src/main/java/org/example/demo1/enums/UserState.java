package org.example.demo1.enums;

public enum UserState {
    ONLINE(""), OFFLINE(""), IN_A_MATCH("đang trong trận");
    private String value;
    UserState(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }
}
