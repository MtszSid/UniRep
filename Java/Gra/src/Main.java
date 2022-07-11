import obliczenia.Wymierna;
import obliczenia.ZeroInDenominatorException;
import rozrywka.Gra;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    private static final Logger log = Logger.getLogger("Logger");

    static{
        final InputStream inputStream = Main.class.getResourceAsStream("/logging.properties");
        try
        {
            LogManager.getLogManager().readConfiguration(inputStream);
        }
        catch (final IOException  e)
        {
            Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
            Logger.getAnonymousLogger().severe(e.getMessage());
        }
    }

    final static int MaxLos = 10;

    public static void main(String[] args) {
	    Gra gra = new Gra();

        gra.start(MaxLos);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Podaj imię:");

        String imie = myObj.nextLine();  // Read user input
        log.info("Imie: " + imie);
        System.out.println("Krótkie wytłumaczenie zasad.");

        while(!(gra.czyOdgadnięto() || gra.czyPrzekroczonoLiczbęPrób())){
            System.out.println("Zgadnij:");

            try{
                int licznik = myObj.nextInt();
                int mianownik = myObj.nextInt();
                Wymierna w = new Wymierna(licznik, mianownik);
                String odpowiedź = gra.zgadnij(w);
                log.info("Strzał: " + w  + " (" + odpowiedź + ")");
                System.out.println(odpowiedź);
            }
            catch (ZeroInDenominatorException e){
                System.out.println(e.getMessage());
                log.warning(e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println("Próba podania argumentu nie będącego liczbą");
                log.warning("Próba podania argumentu nie będącego liczbą");
            }

        }
        gra.wypiszPodsumowanie();
        log.info("Wygrał: " + (gra.czyOdgadnięto() ? imie : "automat"));
    }
}
