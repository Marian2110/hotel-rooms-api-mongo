package ro.fasttrackit.hotelserver.exception.custom;

public class InvalidRoomException extends RuntimeException {
    public InvalidRoomException(String message) {
        super(message);
    }
}
