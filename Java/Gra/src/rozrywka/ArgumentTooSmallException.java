package rozrywka;

public class ArgumentTooSmallException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "An attempt to create game with parameter z < 4";
    }
}
