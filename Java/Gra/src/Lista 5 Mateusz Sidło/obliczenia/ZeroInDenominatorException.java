package obliczenia;

public class ZeroInDenominatorException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        return "Próba utworzenia liczby wymiernej z zerem w mianowniku.";
    }
}
