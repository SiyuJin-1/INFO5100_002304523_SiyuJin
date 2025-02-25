public class Circle extends Shape{
    double radius;
    
    // Constructor
    public Circle(double radius) {
        super("Circle"); // Call the Shape Class Constructor
        this.radius = radius;
    }

    // overrides the method in Shape
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}
