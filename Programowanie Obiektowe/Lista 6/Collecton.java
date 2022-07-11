import java.util.*;
public class Collecton {
    public static void main(){
        Lista<Integer> T = new Lista<Integer>();
        for (Integer i = 0; i < 15 ; i++) T.add(i);
        T.remove((Integer)5);
    }
    class Node<T> {
        public T val;
        public Node<T> next;
        public Node<T> previous;
        public Node(T elem)
        {
            this.val = elem;
        }
    }
    
    class IT<E> implements Iterator<E>{
        public Node<E> POM;

        public void setbegining(Node<E> R){
            POM = R;
        }

        public boolean hasNext(){
            if (POM.next != null) return true;
            return false;
        }

        public E next(){
            if (POM.next == null) return null;
            E po = POM.val;
            POM = POM.next;
            return po;
        }

    }

    class Kolejka<T> implements Collection<T>{
        Node<T> begining;
        Node<T> end;
        
         private T val;
         Node<T> pom;
    
         public boolean isEmpty()
         {
             if (begining == null && end == null) return true;
             return false;
         }
         
         public boolean retainAll(Collection<?> A){
             for (T i : this){
                 if(A.contains(i)) continue;
                 else remove(i);
             }
             return true;
         }

         public boolean add(T el)
         {
             pom = new Node<T>(el);
             if (this.isEmpty())
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
             return true;
         }
    
         public boolean addAll(Collection<? extends T> c){
            for(T i : c){
                this.add(i);
            };
            return true;
         }

         
         public T pop()
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

         public boolean contains (Object O){
             Node<T> R = begining;
             while (R != null){
                 if(R.val == O) return true;
                 R = R.next;
             }
             return false;
         }

         public boolean containsAll(Collection<?> c){
             for(Object i : c){
                 if (this.contains(i)) return false;
             }
             return true;
         }

         public boolean remove(Object o){
             Node <T> Pom = begining;
             if(o == begining.val){
                if (begining == end)
                {
                    begining = null;
                    end = null;
                    return true;
                }
                begining = begining.next;
                begining.previous = null;
                return true;
             }
             if(o == end.val){
                if (begining == end)
                {
                    begining = null;
                    end = null;
                    return true;
                }
                end = end.previous;
                end.next = null;
                return true;
             }
             while(Pom != null && Pom.val != o){
                 pom = Pom.next;
             }

             if (Pom == null) return false;
             Pom.previous.next = Pom.next;
             Pom.next.previous = Pom.previous;
             return true;
         }

         public boolean removeAll(Collection<?> c){
             for(Object o :c) remove(o);
             return true;
         }

         public void clear(){
             begining = null;
             end = null;
         }
         
         public int size(){
             int i = 0;
             Node <T> Pom = begining;
             while(Pom != null){
                 i++;
                 Pom = Pom.next;
             }
             return i;
         }

         public Object[] toArray(){
             Object[] R = new Object[size()]; 
             Node<T> Pom = begining;
             for(int i = 0; i < size(); i++){
                 R[i] = Pom.val;
                 Pom = Pom.next;
             }
             return R;
         }

         public Iterator<T> iterator(){
            IT<T> P = new IT<T>();
            P.setbegining(begining);
            return P;
         }

         public <E> E[] toArray(E[] a){
            Node<T> Pom = begining;
             for(int i = 0; i < size(); i++){
                 a[i] = (E)Pom.val;
                 Pom = Pom.next;
             }
             return a;
         }
    }

    
}