package struktury;

import java.util.Objects;

public class Para implements Cloneable, Comparable<Para>{
    public final String klucz;
    private int wartosc;
    // ...

    public Para( String klucz, int wartosc){
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    @Override
    public String toString() {
        return "(" + klucz + ", " + wartosc + ")";
    }

    public String getKlucz() {
        return klucz;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Para para = (Para) o;

        return Objects.equals(getKlucz(), para.getKlucz());
    }

    @Override
    public int compareTo(Para p) {
        return Integer.compare(wartosc, p.getWartosc());
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
