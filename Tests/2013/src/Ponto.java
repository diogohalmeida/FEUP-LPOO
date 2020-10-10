import java.util.Objects;

public class Ponto implements Comparable{
    private int x,y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return x == ponto.x &&
                y == ponto.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Object o) {
        Ponto that = (Ponto) o;
        if (this.x == that.x) {
            if (this.y > that.y)
                return 1;
            else
                return 0;
        } else if (this.x > that.x) {
            return 1;
        } else if (this.x < that.x) {
            return 0;
        }
        return -1;
    }
}
