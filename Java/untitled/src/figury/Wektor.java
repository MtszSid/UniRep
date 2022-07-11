package figury;

public class Wektor {
    public final double dx;
    public final double dy;

    @Override
    public String toString() {
        return "[" + dx + "," + dy + ']';
    }

    private Wektor(){
        dx = 0;
        dy = 0;
    }

    public Wektor(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public static Wektor SkladanieWektorow(Wektor w, Wektor v)
    {
        return new Wektor(w.dx + v.dx, w.dy + v.dy);
    }

}
