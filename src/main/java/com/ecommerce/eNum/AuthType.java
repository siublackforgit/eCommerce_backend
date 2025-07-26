package com.ecommerce.eNum;

public enum AuthType {
    PASSWORD(0),
    GOOGLE(1);

    private final int code;

    AuthType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    // Optional: Add a method to get enum from code
    public static AuthType fromCode(int code) {
        for (AuthType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AuthType code: " + code);
    }
}
