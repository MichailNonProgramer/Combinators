public class Pair<T, A> {
    private T x;
    private A y;

    public Pair(T x, A y)
    {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public A getY() {
        return y;
    }

    public void setY(A y) {
        this.y = y;
    }


}