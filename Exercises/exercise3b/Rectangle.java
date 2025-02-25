public class Rectangle extends Shape {
    double width, height;

    // Constructor
    public Rectangle(double width, double height) {
        super("Rectangle"); // Call the Shape Class Constructor
        this.width = width;
        this.height = height;
    }
    
    // overrides the method in Shape
    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}
