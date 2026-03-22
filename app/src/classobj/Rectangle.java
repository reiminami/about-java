package classobj;

public class Rectangle {
    private int height;
    private int width;

    public Rectangle(int h, int w) {
        this.height = h;
        this.width = w;
    }

    public int area() {
        return height * width;
    }
}
