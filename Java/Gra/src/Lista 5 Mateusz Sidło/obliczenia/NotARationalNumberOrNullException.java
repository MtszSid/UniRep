package obliczenia;

public class NotARationalNumberOrNullException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "An attempt to use non rational number object or Null object as Rational.";
    }
}
