package Main.Exceptions;

public class FileCloseException extends RuntimeException {
    public FileCloseException(String message){
        super(message);
    }
}
