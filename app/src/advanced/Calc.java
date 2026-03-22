package advanced;

public class Calc<T> {
    public T number;

    public Calc(T number) {
        this.number = number;
    }

    public T getNumber() {
        return number;
    }
}
