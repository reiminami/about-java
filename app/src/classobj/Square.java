package classobj;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public int area() {
        IO.println("*Square Area");
        return super.area();
    }
}
