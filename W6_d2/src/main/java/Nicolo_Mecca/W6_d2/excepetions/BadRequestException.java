package Nicolo_Mecca.W6_d2.excepetions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
