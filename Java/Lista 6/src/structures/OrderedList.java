package structures;

import java.util.Iterator;

public class OrderedList <T extends Comparable<T>>
        implements OrderedSequence<T>, Iterable<T> {

    private class Node <E extends Comparable<E>> {

        private Node<E> next;
        private E data;
        public Node(E el){

            this.data = el;
            this.next = null;
        }

        public Node(E el, Node<E> next){
            this.data = el;
            this.next = next;
        }

        void insert(E el){
            if(el.compareTo(data) < 0){
                E help = this.data;
                this.data = el;
                this.next = new Node<>(help, this.next);
                return;
            }
            Node<E> current = this;
            while(current.next != null && el.compareTo(current.next.data) >= 0){
                current = current.next;
            }
            current.next = new Node<>(el, current.next);
        }

        void remove(E el){
            Node<E> current = this;
            if(el.compareTo(current.data ) == 0){
                current.data = current.next.data;
                current.next = current.next.next;
            }
            while(current.next != null && el.compareTo(current.next.data) != 0){
                current = current.next;
            }
            if(current.next ==  null){
                return;
            }
            current.next = current.next.next;
        }

        boolean search(E el){
            Node<E> current = this;
            while(current != null){
                if(el.compareTo(current.data) == 0){
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        int index(E el){
            Node<E> current = this;
            int ind = 0;
            while(current != null && el.compareTo(current.data) != 0){ //TODO Optymalizacja ?
                current = current.next;
                ind++;
            }
            if(current == null){
                return -1;
            }
            return ind;
        }

        int size(){
            Node<E> current = this;
            int s = 0;
            while(current != null){
               s++;
               current = current.next;
            }
            return s;
        }

        E min(){
           return this.data;
        }

        E max(){
            Node<E> current = this;
            while(current.next != null){
                current = current.next;
            }
            return current.data;
        }

        E at(int pos){
            Node<E> current = this;
            if(pos < 0){
                throw new IndexOutOfBoundsException();
            }
            while(pos != 0){
                pos--;
                if(current == null){
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            if(current == null){
                throw new IndexOutOfBoundsException();
            }
            return current.data;
        }

        public Node<E> getNext(){
            return next;
        }

        public E getData(){
            return data;
        }

    }

    private class CustomIterator<E extends Comparable<E>> implements Iterator<E>{

        Node<E> previous;
        Node<E> current;

        public CustomIterator(Node<E> N){
            previous = null;
            current = N;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if(current == null){
                throw new IllegalStateException();
            }

            previous = current;
            current = current.getNext();

            return previous.getData();
        }

        @Override
        public void remove() {
            if(previous == null){
                throw new IllegalStateException();
            }

            OrderedList.this.remove((T) previous.getData());
            previous = null;

        }
    }

    private Node<T> head;

    @Override
    public void insert(T el) {
        if(el == null){
            throw new NullPointerException();
        }
        if(head == null){
            this.head = new Node<>(el);
            return;
        }
        head.insert(el);
    }

    @Override
    public void remove(T el) {
        if(head == null){
            return;
        }
        if(head.next == null && head.data.compareTo(el) == 0){
            head = null;
            return;
        }
        head.remove(el);
    }

    @Override
    public boolean search(T el) {
        if(head == null){
            return false;
        }
        return head.search(el);
    }

    @Override
    public int index(T el) {
        if(head == null){
            return -1;
        }
        return head.index(el);
    }

    @Override
    public int size() {
        if(head == null) {
            return 0;
        }
        return head.size();
    }

    @Override
    public T min() {
        if (head == null){
            return null;
        }
        return head.min();
    }

    @Override
    public T max() {
        if (head == null){
            return null;
        }
        return head.max();
    }

    @Override
    public T at(int pos) {
        if(head == null){
            throw new IndexOutOfBoundsException();
        }
        return head.at(pos);
    }

    @Override
    public String toString () {
        StringBuilder S = new StringBuilder("[");
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()){
            T obj = iterator.next();
            S.append(obj);
            if(iterator.hasNext()){
                S.append(", ");
            }
        }
        S.append("]");
        return S.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator<>(head);
    }
}
