package kosmos;

/**
 * Planety Układu słonecznego
 */
public enum Planeta {

    /**
     * Merkury
     */
    MERKURY(0.06, 0.39,  0.39, 0.24, "Szary", Rodzaj.SKALISTA),
    /**
     * Wenus
     */
    WENUS(0.82, 0.95,  0.72, 0.62, "Beż?", Rodzaj.SKALISTA),
    /**
     * Ziemia
     */
    ZIEMIA(1,1,1, 1, "Niebieski", Rodzaj.SKALISTA),
    /**
     * Mars
     */
    MARS(0.11, 0.53, 1.52, 1.88, "Czerwony", Rodzaj.SKALISTA),
    /**
     * Jowisz
     */
    JOWISZ(317.8, 11.2, 5.2, 11.56, "Beż?", Rodzaj.GAZOWA),
    /**
     * Saturn
     */
    SATURN(92.5, 9.41, 9.54, 29.47,"Beż?", Rodzaj.GAZOWA),
    /**
     * Uran
     */
    URAN(14.6, 3.98, 19.22, 84.01, "Niebieski",  Rodzaj.GAZOWA),
    /**
     * Neptun
     */
    NEPTUN(17.2, 3.81, 30.06, 164.8, "Niebieski", Rodzaj.GAZOWA);

    /**
     * Rodzaj planety
     */
    enum Rodzaj{
        /**
         * Skalista
         */
        SKALISTA,
        /**
         * Gazowa
         */
        GAZOWA;
    }

    /**
     * Masa danej planety względna w stosunku do Ziemi.
     */
    double masa;
    /**
     * Średnica danej planety względna w stosunku do Ziemi
     */
    double średnica;
    /**
     * Średnia odległość od słońca [AU]
     */
    double średnia_odległość_od_Słońca;
    /**
     * Czas obiegu wokół słońca w latach.
     */
    double czas_obiegu_dookoła_Słońca;
    /**
     * Kolor planety subiektywnie wybrany przez autora.
     */
    String kolor;
    /**
     * Rodzaj planety
     */
    Rodzaj rodzaj;

    /**
     * Some weird method.
     * @param s parametr
     * @return Concatenated "Hello " and passed argument
     */
    String weirdMethod(String s){
        return "Hello " + s;
    }

    /**
     * @param masa  masa względna w stosunku do Ziemi.
     * @param średnica średnica względna w stosunku do Ziemi.
     * @param średnia_odległość_od_Słońca średnia odległość od słońca [AU]
     * @param czas_obiegu_dookoła_Słońca czas obiegu dookoła Słońca wyrażona w latach
     * @param kolor kolor planety oznaczony rzez autora
     * @param rodzaj rodzaj planety
     */
    Planeta(double masa, double średnica, double średnia_odległość_od_Słońca,
            double czas_obiegu_dookoła_Słońca, String kolor, Rodzaj rodzaj){

        this.masa = masa;
        this.czas_obiegu_dookoła_Słońca = czas_obiegu_dookoła_Słońca;
        this.kolor = kolor;
        this.rodzaj = rodzaj;
        this.średnia_odległość_od_Słońca = średnia_odległość_od_Słońca;
        this.średnica = średnica;
    }
}

