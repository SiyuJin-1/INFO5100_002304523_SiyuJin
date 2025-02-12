public abstract class Shape {
    static String color = "White"; // static field, all shapes have the same color "White"
    String name;

    // Constructor
    public Shape(String name) {
        this.name = name;
    }

    // Abstract methods within an abstract class
    // Abstract methods do not have a method body and must be implemented by subclasses
    public abstract double calculateArea(); 
    public abstract double calculatePerimeter();

    // ouput some information
    public void outputInfo() {
        System.out.println("This is a " + name + " with color " + color);
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
        System.out.println("\n-----------------------------------\n");
    }
}

// For remebering:
/*
    Abstract classes cannot be instantiated(must be extended by concrete classes)
    Subclasses must provide @Override implementations for all abstract methods
    Abstract classes can have normal methods(subclasses can inherit directly)
*/ 
