package obliczenia;

public class DivisionByZeroException extends ArithmeticException {
    @Override
    public String getMessage() {

        return "An attempt to divide by zero.";
    }
}
