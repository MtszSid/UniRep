//Mateusz Sidło Zad.1 cz.2
public class Wojsko {
    public static void main(String[] args) {
        szeregowy A = new szeregowy("Jan", "Kowalski");
        sierzant B = new sierzant("Witold", "Wach");
        plutonowy C = new plutonowy("Robert", "Snoch");
        szeregowy D = new szeregowy("Szymon", "Wijas");
        if(A.compareTo(D) == 0){
            System.out.println(A.dane() + " " + D.dane());
        }
        if(A.compareTo(B) == -1){
            System.out.println(B.dane());
        }
        else{
            System.out.println(A.dane());
        }
        if(B.compareTo(C) == 1){
            System.out.println(B.dane());
        }
    }
}

class Stopien implements Comparable<Stopien>{
    int ranga;
    String Imie;
    String Nazwisko;
    public Stopien(String Im, String Nazw){
        ranga = 0;
        Imie = Im;
        Nazwisko = Nazw;
    }
    public int compareTo(Stopien K){
        if(ranga < K.ranga) return -1;
        if(ranga > K.ranga) return 1;
        return 0;
    }

    public String dane(){
        return Imie + " " + Nazwisko;
    }
}

class szeregowy extends Stopien{
    public szeregowy(String Im, String Nazw){ 
        super(Im, Nazw);
        ranga = 5;
    }
}

class kapral extends Stopien{
    public kapral(String Im, String Nazw){ 
        super(Im, Nazw);
        ranga = 10;
    }
}

class plutonowy extends Stopien{
    public plutonowy(String Im, String Nazw){ 
        super(Im, Nazw);
        ranga = 15;
    }
}

class sierzant extends Stopien{
    public sierzant(String Im, String Nazw){ 
        super(Im, Nazw);
        ranga = 20;
    }
}


