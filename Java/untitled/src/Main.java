import figury.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
	    Punkt A = new Punkt(1, 1);
        Punkt B = new Punkt(-1, 3);
        Punkt C = new Punkt(4, 5);
        Punkt D = new Punkt(0, 0);
        Odcinek o = new Odcinek(A, B);
        try{
           Trojkat T = new Trojkat(new Punkt(0,0), new Punkt(0,1), new Punkt(0, 2));
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        Trojkat Tr_1 = new Trojkat(A, B, C);
        Prosta p = new Prosta(2, -1, 1);
        Wektor W = new Wektor(5, 1);
        Wektor V = new Wektor(-4, 0);

        D.przesun(W);
        System.out.println("D = " + D);
        D.obroc(A, Math.toRadians(90));
        System.out.println("D = " + D);
        A.odbij(p);
        System.out.println("A = " + A);
        Tr_1.obroc(B, Math.toRadians(45));
        Tr_1.odbij(p);
        Tr_1.przesun(W);
        System.out.println(Tr_1);
        System.out.println(Wektor.SkladanieWektorow(W, V));
        System.out.println(Prosta.CzyRownolegla(p, new Prosta(-4, 2, -10)));
        System.out.println(Prosta.CzyProstopadla(p, new Prosta(-0.5, -1, -10)));
        System.out.println(Prosta.PunktPrzeciecia(p, new Prosta(-0.5, -1, -10)));

    }
}
