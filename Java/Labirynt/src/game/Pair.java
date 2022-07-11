package game;

public class Pair <T extends Comparable<T>, E extends Comparable<E>> implements  Comparable<Pair<T,E>>{
    private T key;
    private E value;

    public Pair(T key, E value){
        this.key = key;
        this.value = value;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public T getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!getKey().equals(pair.getKey())) return false;
        return getValue().equals(pair.getValue());
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }

    @Override
    public int compareTo(Pair<T, E> o) {
        int c = key.compareTo(o.getKey());
        if(c == 0){
            return value.compareTo(o.getValue());
        }
        return c;
    }

    @Override
    public String toString() {
        return "<" + key.toString() + ", " + value.toString() + ">";
    }
}
