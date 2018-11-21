package Main.Exceptions;

public class FileAlreadyOpenException extends Exception {
    public FileAlreadyOpenException(String message) {
        super(message);
    }
}
