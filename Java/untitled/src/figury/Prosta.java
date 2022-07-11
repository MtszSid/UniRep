package figury;

public class Prosta {
    public final double A;
    public final double B;
    public final double C;

    @Override
    public String toString() {
        return  A + "x +"+ B + "y +" + C + " = 0";
    }

    private Prosta(){
        A = 1;
        B = 0;
        C = 0;
    }

    public Prosta(double a, double b, double c){
        A = a;
        B = b;
        C = c;
    }

    static public boolean CzyProstopadla(Prosta p, Prosta r){
        double help = r.A * p.A + r.B * p.B;
        return help <= 0.000000001 && help >= -0.000000001 ;
    }

    static public boolean CzyRownolegla(Prosta p, Prosta r){
        double help = r.A * p.B - r.B * p.A;
        return help <= 0.000000001 && help >= -0.000000001 ;
    }

    static public Prosta Przesun(Prosta p, Wektor W){
        return new Prosta(p.A, p.B, p.C + p.B * W.dy - p.A * W.dx);
    }

    static public Punkt PunktPrzeciecia(Prosta p, Prosta r){
        double W_AB = p.A * r.B - r.A * p.B;
        double W_BC = p.B * r.C - r.B * p.C;
        double W_CA = p.C * r.A - r.C * p.A;
        return new Punkt(W_BC / W_AB, W_CA / W_AB);
    }
}
