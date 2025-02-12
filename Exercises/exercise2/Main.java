public class Main {
    public static void main(String[] args) {
        // Create a Triangle object
        Shape triangle = new Triangle(3, 4, 3, 4, 5); // polymorphism
        triangle.outputInfo();
        
        // Create a Rectangle object
        Shape rectangle = new Rectangle(5, 8); // polymorphism
        rectangle.outputInfo();
        
        // Create a Square object
        Shape square = new Square(2); // polymorphism
        square.outputInfo();

        // Create a Circle object
        Shape circle = new Circle(5); // polymorphism
        circle.outputInfo();

        // test static fields
        System.out.println("***********************Testing static fields…… Change color to green……*********************\n");
        Shape.color = "Green"; // Change the color of all shapes to green
        triangle.outputInfo();
        rectangle.outputInfo();
        square.outputInfo();
        circle.outputInfo();
    }
    
}

// For remebering:
/*
    static field belongs to the class itself, not to any specific object
    All instances of the class share the same static variable, and it is initialized only once when 
    the class is loaded
 */
