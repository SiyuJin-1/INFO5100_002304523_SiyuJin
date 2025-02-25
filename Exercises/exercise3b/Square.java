public class Square extends Shape{
    double side;
    
    // Constructor
    public Square(double side) {
        super("Square"); // Call the Shape Class Constructor
        this.side = side;
    }

    // overrides the method in Shape
    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
    
}
