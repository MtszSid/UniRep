import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pojazdy {

	public static void main(String[] args) {
        Pojazd R;
        if(args[1].equals("pojazd")){
            R = Pojazd.czyt(args[0]);
        }
        else if(args[1].equals("samochod")){
            R = Samochod.czyt(args[0]);
        }
        else if(args[1].equals("tramwaj")){
            R = Tramwaj.czyt(args[0]);
        }
        else{
             R = new Pojazd();
            R.file = args[0];
            }
        R.edycja();

	}

}

class Pojazd implements Serializable{
	private static final long serialVersionUID = 3469929059033990345L;
	String rok_produkcji;
	String Model;
    String Marka;
    String file;
	String ToString() {
		return Marka + " " + Model + " " + rok_produkcji;
	}
	
	public Pojazd() {
		rok_produkcji = "2000";
		Model = "A4";
        Marka = "Audi";
        file = "Poj.ser";
	}
    
    static Pojazd czyt(String file){
        Pojazd R;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            R = (Pojazd) in.readObject();
            in.close();
        }catch (IOException i) {
            R = new Pojazd();
            R.file = file;
        } catch (ClassNotFoundException c) {
            R = new Pojazd();
            R.file = file;
        }
        return R;
    }

    void save(){
        try{
            FileOutputStream fileOut =
            new FileOutputStream(this.file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }catch (IOException i) {
            i.printStackTrace();
            return;
        } 
    }

	public void edycja() {
		JFrame frame = new JFrame("Edycja Pojazd");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container kontener = frame.getContentPane();
		GridLayout layout = new GridLayout(4, 2);
		kontener.setLayout(layout);
		JLabel marka_etykieta = new JLabel("Marka");
		kontener.add(marka_etykieta);
		JTextField marka = new JTextField(this.Marka, 40);
        kontener.add(marka);
        JLabel model_etykieta = new JLabel("Model");
		kontener.add(model_etykieta);
		JTextField model = new JTextField(this.Model, 40);
        kontener.add(model);
        JLabel rok_etykieta = new JLabel("Rok produkcji");
		kontener.add(rok_etykieta);
		JTextField rok = new JTextField(this.rok_produkcji.toString(), 4);
        kontener.add(rok);
        JButton b = new JButton("Zapisz");
            b.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt) 
                    {
                        rok_produkcji = rok.getText();
                        Marka = marka.getText();
                        Model = model.getText(); 
                        save();                                           
                    }
                });
        kontener.add(b);
        frame.pack();frame.setVisible(true);
	}
}

class Samochod extends Pojazd{
	private static final long serialVersionUID = -8677716163003758875L;
	String Paliwo;
    String Przebieg;
    String Elektryczny;
    
    Samochod(){
        super();
        Paliwo = "Benzyna";
        Przebieg = "504";
        Elektryczny = "Nie";
        file = "Sam.ser";
    }

    static Samochod czyt(String file){
        Samochod R;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            R = (Samochod) in.readObject();
            in.close();
        }catch (IOException i) {
            R = new Samochod();
            R.file = file;
        } catch (ClassNotFoundException c) {
            R = new Samochod();
            R.file = file;
        }
        return R;
    }

	String ToString() {
		return super.ToString() + " " + Przebieg + " tys km" ;
    }


    public void edycja() {
		JFrame frame = new JFrame("Edycja_Samchodu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container kontener = frame.getContentPane();
		GridLayout layout = new GridLayout(7, 2);
		kontener.setLayout(layout);
		JLabel marka_etykieta = new JLabel("Marka");
		kontener.add(marka_etykieta);
		JTextField marka = new JTextField(this.Marka, 40);
        kontener.add(marka);
        JLabel model_etykieta = new JLabel("Model");
		kontener.add(model_etykieta);
		JTextField model = new JTextField(this.Model, 40);
        kontener.add(model);
        JLabel rok_etykieta = new JLabel("Rok produkcji");
		kontener.add(rok_etykieta);
		JTextField rok = new JTextField(this.rok_produkcji.toString(), 4);
        kontener.add(rok);
        JLabel Paliwo_etykieta = new JLabel("Rodzaj paliwa");
		kontener.add(Paliwo_etykieta);
		JTextField paliwo = new JTextField(this.Paliwo, 4);
        kontener.add(paliwo);
        JLabel przebieg_etykieta = new JLabel("Przebieg");
		kontener.add(przebieg_etykieta);
		JTextField przebieg = new JTextField(this.Przebieg, 5);
        kontener.add(przebieg);
        JLabel Elektryczny_etykieta = new JLabel("Samochód elektryczny");
        kontener.add(Elektryczny_etykieta);
        JTextField elektryczny = new JTextField(this.Elektryczny, 4);
        kontener.add(elektryczny);
        JButton b = new JButton("Zapisz");
        b.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent evt) 
                {
                    rok_produkcji = rok.getText();
                    Marka = marka.getText();
                    Model = model.getText();
                    Paliwo = paliwo.getText();
                    Elektryczny = elektryczny.getText();
                    Przebieg = przebieg.getText();
                    save();
                }
            });
        kontener.add(b);
        frame.pack();frame.setVisible(true);
    
    }
}

    
    
    class Tramwaj extends Pojazd{
        private static final long serialVersionUID = -1111121163003758875L;
        String Nr_lini;
        String Liczba_miejsc;
        String Kolor;
        
        Tramwaj(){
            Model = "Pesa Twist";
            Marka = "Pesa";
            rok_produkcji = "2012";
            Nr_lini = "2";
            Liczba_miejsc = "36";
            Kolor = "niebieski";
            file = "tram.ser";
        }
    
        static Tramwaj czyt(String file){
            Tramwaj R;
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                R = (Tramwaj) in.readObject();
                in.close();
            }catch (IOException i) {
                R = new Tramwaj();
                R.file = file;
            } catch (ClassNotFoundException c) {
                R = new Tramwaj();
                R.file = file;
            }
            return R;
        }

        String ToString() {
            return super.ToString() + " " + "Nr" + Nr_lini ;
        }
    
    
        public void edycja() {
            JFrame frame = new JFrame("Edycja_Tramwaju");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container kontener = frame.getContentPane();
            JLabel Paliwo_etykieta = new JLabel("Nr lini");
            kontener.add(Paliwo_etykieta);
            JTextField nr = new JTextField(this.Nr_lini, 4);
            kontener.add(nr);
            GridLayout layout = new GridLayout(7, 2);
            kontener.setLayout(layout);
            JLabel marka_etykieta = new JLabel("Marka");
            kontener.add(marka_etykieta);
            JTextField marka = new JTextField(this.Marka, 40);
            kontener.add(marka);
            JLabel model_etykieta = new JLabel("Model");
            kontener.add(model_etykieta);
            JTextField model = new JTextField(this.Model, 40);
            kontener.add(model);
            JLabel rok_etykieta = new JLabel("Rok produkcji");
            kontener.add(rok_etykieta);
            JTextField rok = new JTextField(this.rok_produkcji.toString(), 4);
            kontener.add(rok);
            JLabel miejsca_etykieta = new JLabel("Liczba Miejsc");
            kontener.add(miejsca_etykieta);
            JTextField miejsca = new JTextField(this.Liczba_miejsc, 4);
            kontener.add(miejsca);
            JLabel kol_etykieta = new JLabel("Kolor");
            kontener.add(kol_etykieta);
            JTextField kol = new JTextField(this.Kolor, 3);
            kontener.add(kol);
            JButton b = new JButton("Zapisz");
            b.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt) 
                    {
                        rok_produkcji = rok.getText();
                        Marka = marka.getText();
                        Model = model.getText();
                        Nr_lini = nr.getText();
                        Kolor = kol.getText();
                        Liczba_miejsc = miejsca.getText();
                        save();
                    }
                });
            kontener.add(b);
            frame.pack();frame.setVisible(true);
        }
}


