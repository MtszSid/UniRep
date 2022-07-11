package structures;

public interface OrderedSequence<T extends Comparable<T>> {
    void insert(T el);
    void remove(T el);
    boolean search(T el);
    int index(T el);
    int size();
    T min();
    T max();
    T at(int pos);
}
