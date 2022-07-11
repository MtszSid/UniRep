import java.io.*;


public class Serial {
    public static void main(String[] args){
        Lista<Integer> T = new Lista <Integer>();
        Lista<Integer> R = null;
        T.Add_end(1);
        T.Add_end(2);
        T.Add_end(3);
        T.Add_end(4);
        T.Add_end(5);
        
        try {
            FileOutputStream fileOut =
            new FileOutputStream("Serial.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(T);
            out.close();
            fileOut.close();
            FileInputStream fileIn = new FileInputStream("Serial.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            R = (Lista<Integer>) in.readObject();
            in.close();
            fileIn.close();
         } catch (IOException i) {
            i.printStackTrace();
            return;
         } catch (ClassNotFoundException c) {
            System.out.println("Serial class not found");
            c.printStackTrace();
            return;
         }
        System.out.println(R.Pop_begining());
        System.out.println(R.Pop_begining());
        System.out.println(R.Pop_begining());
        System.out.println(R.Pop_begining());
        System.out.println(R.Pop_begining());
    }
}

class Node<T> implements Serializable{
	private static final long serialVersionUID = 6261632645804739653L;
	public T val;
    public Node<T> next;
    public Node<T> previous;
    public Node(T elem)
    {
        this.val = elem;
    }
}

class Lista<T> implements Serializable{
	private static final long serialVersionUID = -241997126621131502L;
	Node<T> begining;
	Node<T> end;
	
	 private T val;
     Node<T> pom;

     public Boolean Pusta()
     {
         if (begining == null && end == null) return true;
         return false;
     }

     public void Add_end(T el)
     {
         pom = new Node<T>(el);
         if (this.Pusta())
         {
             this.begining = pom;
             this.end = pom;
         }
         else
         {
             pom.previous = this.end;
             this.end.next = pom;
             this.end = pom;
         }
     }

     public void Add_begining(T el)
     {
         pom = new Node<T>(el);
         if (this.Pusta())
         {
             this.begining = pom;
             this.end = pom;
         }
         else
         {
             pom.next = this.begining;
             this.begining.previous = pom;
             this.begining = pom;
         }
     }

     public T Pop_end()
     {
         if (end == null)
         {
             System.out.println("Lista pusta");
             return null;
         }
         val = end.val;
         if (begining == end)
         {
             begining = null;
             end = null;
             return val;
         }
         end = end.previous;
         end.next = null;
         return val;
     }

     public T Pop_begining()
     {
         if (begining == null)
         {
        	 System.out.println("Lista pusta");
             return null;
         }
         val = begining.val;
         if (begining == end)
         {
             begining = null;
             end = null;
             return val;
         }
         begining = begining.next;
         begining.previous = null;
         return val;
     }
 
}

