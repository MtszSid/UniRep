
public class Kolejka{
    public static void main(String[] args) {
        Queue<Integer> Kol = new Queue<Integer>(10);
        Kol.add(15);
        Kol.add(5);
        Kol.add(7);
        Kol.print();
        System.out.println(Kol.pop());
        Kol.print();
        Kol.pop();
        Kol.pop();
        Kol.pop();
        Kol.pop();
        System.out.println(Kol.pop());
        Kol.add(10);
        Kol.print();
    }
}

class Elem<T extends Comparable<T>>implements Comparable<Elem<T>>{
    public T elem;
    public Elem<T> next;
    
    public int compareTo(Elem<T> O){
        if (O == null) return -1;
        return elem.compareTo(O.elem);
    }

    public Elem(T e){
        this.elem = e;
        this.next = null;
    }
}

class Queue<T extends Comparable<T>> {
    Elem<T> first;

    public Queue(T elem){
        first = new Elem<T>(elem);
    }

    public void add(T el){
        Elem<T> P = new Elem<T>(el);
        Elem<T> Prev = null;
        Elem<T> Next = first;
        while(P.compareTo(Next) > 0){
            Prev = Next;
            Next = Next.next;
        }
        P.next = Next;
        if(Prev == null) first = P;
        else Prev.next = P;
    }
    
    public T pop(){
        if (first == null) return null;
        Elem<T> pom = first;
        first = first.next;
        pom.next = null;
        return pom.elem;
    }

    public void print(){
        Elem<T> pom = first;
        while(pom != null){
            System.out.print(pom.elem);
            System.out.print(", ");
            pom=pom.next;
        }
        System.out.println();
    }
}