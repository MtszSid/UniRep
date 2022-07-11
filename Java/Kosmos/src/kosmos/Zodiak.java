package kosmos;

/**
 * Znaki zodiaku
 */
public enum Zodiak {
    /**
     * Baran
     */
    BARAN(Energia.SILNA, Krzyz.KARDYNALNY, Zywiol.OGIEN),
    /**
     * Byk
     */
    BYK(Energia.SLABA, Krzyz.STALY, Zywiol.ZIEMIA),
    /**
     * Bliźnięta
     */
    BLIŹNIĘTA(Energia.SILNA, Krzyz.ZMIENNY, Zywiol.POWIETRZE),
    /**
     * Rak
     */
    RAK(Energia.SLABA, Krzyz.KARDYNALNY, Zywiol.WODA),
    /**
     * Lew
     */
    LEW(Energia.SILNA, Krzyz.STALY, Zywiol.OGIEN),
    /**
     * Panna
     */
    PANNA(Energia.SLABA, Krzyz.ZMIENNY, Zywiol.ZIEMIA),
    /**
     * Waga
     */
    WAGA(Energia.SILNA, Krzyz.KARDYNALNY, Zywiol.POWIETRZE),
    /**
     * Skorpion
     */
    SKORPION(Energia.SLABA, Krzyz.STALY, Zywiol.WODA),
    /**
     * Strzelec
     */
    STRZELEC(Energia.SILNA, Krzyz.ZMIENNY, Zywiol.OGIEN),
    /**
     * Koziorożec
     */
    KOZIOROŻEC(Energia.SLABA, Krzyz.KARDYNALNY, Zywiol.ZIEMIA),
    /**
     * Wodnik
     */
    WODNIK(Energia.SILNA, Krzyz.STALY, Zywiol.POWIETRZE),
    /**
     * Ryby
     */
    RYBY(Energia.SLABA, Krzyz.ZMIENNY, Zywiol.WODA);

    /**
     * Krzyż zodiaku
     */
    Krzyz krzyz;
    /**
     * Żywioł zodiaku
     */
    Zywiol zywiol;
    /**
     * Energia zodiaku
     */
    Energia energia;

    Zodiak(Energia energia, Krzyz krzyz, Zywiol zywiol){
        this.energia = energia;
        this.krzyz = krzyz;
        this.zywiol = zywiol;
    }

    /**
     * Zwraca krzyż danego znaku
     * @return Krzyż danego znaku
     */
    public Krzyz getKrzyz(){
        return krzyz;
    }

    /**
     * Zwraca energię danego znaku
     * @return Energia danego znaku
     */
    public Energia getEnergia() {
        return energia;
    }

    /**
     * Zwraca żywioł danego znaku
     * @return Żywioł danego znaku
     */
    public Zywiol getZywiol() {
        return zywiol;
    }
}
