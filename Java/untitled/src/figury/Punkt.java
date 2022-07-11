package figury;

public class Punkt {
    protected double x;
    protected double y;

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }

    public Punkt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void przesun(Wektor W){
        this.x += W.dx;
        this.y += W.dy;
    }

    public void obroc(Punkt P, double kat){
        double x_ = (x - P.x) * Math.cos(kat) - (y - P.y) * Math.sin(kat) + P.x;
        double y_ = (x - P.x) * Math.sin(kat) + (y - P.y) * Math.cos(kat) + P.y;
        x = x_;
        y = y_;
    }

    public void odbij(Prosta p){
        Prosta r = new Prosta(-p.B, p.A, p.B * x - p.A * y);
        Punkt P = Prosta.PunktPrzeciecia(p, r);
        Wektor w = new Wektor(P.x - x, P.y - y);
        this.przesun(w);
        this.przesun(w);
    }


}
