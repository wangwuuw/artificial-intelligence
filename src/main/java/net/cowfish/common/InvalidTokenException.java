package net.cowfish.common;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
    // 可以添加更多的构造函数和逻辑
}