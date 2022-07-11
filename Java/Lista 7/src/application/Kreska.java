package application;

import java.awt.*;

public class Kreska {
    protected Point początek, koniec;
    protected Color kolor;
    public Kreska(Point pocz, Point kon, Color kol) {
        this.początek = pocz;
        this.koniec = kon;
        this.kolor = kol;
    }

    public Color getKolor() {
        return kolor;
    }

    public Point getKoniec() {
        return koniec;
    }

    public Point getPoczątek() {
        return początek;
    }
}
