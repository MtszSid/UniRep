package obliczenia;

public class Wymierna implements Comparable<Wymierna>{
    private int licznik, mianownik = 1;

    public Wymierna(){
        licznik = 0;

    }

    public Wymierna(int licznik) throws ZeroInDenominatorException {
        this(licznik, 1);
    }

    public Wymierna(int licznik, int mianownik) throws ZeroInDenominatorException {
        if (mianownik == 0){
            throw new ZeroInDenominatorException();
        }
        int sign = 1;

        if (licznik < 0){
            sign *= -1;
            licznik *= -1;
        }
        if(mianownik < 0){
            sign *= -1;
            mianownik *= -1;
        }

        int gcd  = EuclideanAlgorithm.GCD(licznik, mianownik);

        this.licznik = sign * (licznik / gcd);
        this.mianownik = mianownik / gcd;
    }

    public void add(Wymierna w){
        int gcd = EuclideanAlgorithm.GCD(this.mianownik, w.mianownik);
        this.licznik = this.licznik * (w.mianownik / gcd) + w.licznik * (this.mianownik / gcd);
        this.mianownik = this.mianownik * w.mianownik / gcd;

        gcd = EuclideanAlgorithm.GCD(this.mianownik, this.licznik);
        this.licznik /= gcd;
        this.mianownik /= gcd;
    }

    public void subtract(Wymierna w){
        int gcd = EuclideanAlgorithm.GCD(this.mianownik, w.mianownik);
        this.licznik = this.licznik * (w.mianownik / gcd) - w.licznik * (this.mianownik / gcd);
        this.mianownik = this.mianownik * w.mianownik / gcd;

        gcd = EuclideanAlgorithm.GCD(this.mianownik, this.licznik);
        this.licznik /= gcd;
        this.mianownik /= gcd;
    }

    public void multiply(Wymierna w){
        this.licznik *= w.licznik;
        this.mianownik *= w.mianownik;
        int gcd = EuclideanAlgorithm.GCD(this.licznik, this.mianownik);
        this.licznik /= gcd;
        this.mianownik /= gcd;
    }

    public void divide(Wymierna w) throws DivisionByZeroException{
        if (w.equals(new Wymierna())){
            throw new DivisionByZeroException();
        }
        this.multiply(new Wymierna(w.mianownik, w.licznik));
    }

    @Override
    public String toString() {
        return licznik + "/" + mianownik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wymierna wymierna = (Wymierna) o;

        assert EuclideanAlgorithm.GCD(this.mianownik, this.licznik ) == 1;
        assert EuclideanAlgorithm.GCD(wymierna.mianownik, wymierna.licznik ) == 1;

        if (licznik != wymierna.licznik) return false;
        return mianownik == wymierna.mianownik;
    }

    @Override
    public int compareTo(Wymierna o) {
        if (this == o) return 0;
        if (o == null) throw new NotARationalNumberOrNullException();

        assert EuclideanAlgorithm.GCD(this.mianownik, this.licznik ) == 1;
        assert EuclideanAlgorithm.GCD(o.mianownik, o.licznik ) == 1;

        int gcd = EuclideanAlgorithm.GCD(this.mianownik, o.mianownik);
        int thisLicznik = this.licznik * (o.mianownik / gcd);
        int wymiernaLicznik = o.licznik * (this.mianownik / gcd);

        return Integer.compare(thisLicznik, wymiernaLicznik);
    }

    public void reverse() {
        int help = this.licznik;
        this.licznik = this.mianownik;
        this.mianownik = help;
    }
}


class EuclideanAlgorithm{

    static int GCD(int a, int b){
        if(a < b){
            return GCD(b, a);
        }
        if (a == 0){
            return b;
        }
        if (b == 0){
            return a;
        }
        else{
            return GCD(b, a % b);
        }
    }
}
