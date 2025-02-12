public class Triangle extends Shape {
    double base, height, side1, side2, side3;
    
    // Constructor
    public Triangle(double base, double height, double side1, double side2, double side3) {
        super("Triangle"); // Call the Shape Class Constructor
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // overrides the method in Shape
    @Override 
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }
}


// For remebering:

/* 
    If the superclass has a constructor that requires arguments, the subclass must explicitly call it
*/

/* 
    Use @Override will ensure correct method overriding
    If the method name, parameters, or return type do not match the superclass method, the compiler 
    will show an error
    can prevent accidental mistakes where a method is not actually overriding anything
*/ 