package rozrywka;

import obliczenia.Wymierna;

public class Gra {
    private int zakres;
    private Wymierna liczba;
    private int maksIlośćPrób;
    private int licznikPrób;
    private boolean odgadnięto;

    public void start(int z) {
        if (z < 4) throw new ArgumentTooSmallException();

        zakres = z;
        int licz = (int) (Math.random() * zakres) + 1;
        int mian = (int) (Math.random() * zakres) + 1;
        liczba = new Wymierna(licz, mian);

        // inicjalizacja: maksIlośćPrób, licznikPrób, odgadnięto

        licznikPrób = 0;
        odgadnięto = false;
        maksIlośćPrób = (int) Math.ceil(3 * Math.log(z));
        assert czyPoprawnaLiczba(liczba); // czy 0 < liczb < 1
    }

    private boolean czyPoprawnaLiczba(Wymierna w){
        if (w.compareTo(new Wymierna()) > 0 && w.compareTo(new Wymierna(1)) < 0){
            return true;
        }
        w.reverse();
        if (w.equals(new Wymierna(1))){
            w.subtract(new Wymierna(1, 2));
        }
        return true;
    }

    public boolean czyOdgadnięto(){
        return odgadnięto;
    }

    public boolean czyPrzekroczonoLiczbęPrób(){
        return licznikPrób >= maksIlośćPrób;
    }

    public String zgadnij(Wymierna w){
        licznikPrób++;
        int comp = liczba.compareTo(w);
        if(comp > 0){
            return "Za mało";
        }
        else if(comp < 0){
            return "Za dużo";
        }
        else{
            this.odgadnięto = true;
            return "Zgadłeś";
        }
    }

    public void wypiszPodsumowanie(){
        System.out.println("Prawidłowa odpowiedź: " + liczba);
        System.out.println("Wykorzystano " + licznikPrób + " prób na " + maksIlośćPrób + " możliwych");
    }
}

