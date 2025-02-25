import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create object
        Shape triangle = new Triangle(3, 4, 3, 4, 5); // polymorphism
        Shape rectangle = new Rectangle(5, 8); // polymorphism
        Shape square = new Square(2); // polymorphism
        Shape circle = new Circle(5); // polymorphism

        // add objects to a shapes list
        List<Shape> shapes = new ArrayList<>();
        shapes.add(triangle);
        shapes.add(rectangle);
        shapes.add(square);
        shapes.add(circle);

        // output information
        for (Shape shape : shapes) {
            shape.outputInfo();
        }

        // test static fields
        System.out.println("***********************Testing static fields…… Change color to green……*********************\n");
        Shape.color = "Green"; // Change the color of all shapes to green
        for (Shape shape : shapes) {
            shape.outputInfo();
        }

        // serialization
        serializeShapes(shapes, "serialize_shapes.ser");

        // deserialization
        List<Shape> deserialized_Shapes = deserializeShapes("serialize_shapes.ser");

        // output information of deserialized shapes
        if (deserialized_Shapes != null) {
            for (Shape shape : deserialized_Shapes) {
                shape.outputInfo();
            }
        }

    }

    // serialization
    public static void serializeShapes(List<Shape> shapes, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(shapes); // write the shapes list to the file
            System.out.println("************************************** Test serialization **************************************\n");
            System.out.println("Serialized Shapes is saved in " + filename + "\n");
        } 
        // catch exceptions, such as file not found, file not writable...
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // deserialization
    public static List<Shape> deserializeShapes(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            // read from the file, the returned type is Object, so it needs to be cast to List<Shape>
            List<Shape> shapes = (List<Shape>) in.readObject(); 
            System.out.println("************************************** Test deserialization **************************************\n");
            System.out.println("Shapes deserialized from " + filename + "\n");
            return shapes;
        } 
        // catch exceptions, such as file not found, file not readable...
        catch (IOException i) {
            i.printStackTrace();
        } 
        // catch exceptions, such as class not found...
        catch (ClassNotFoundException c) {
            System.out.println("Shape class not found");
            c.printStackTrace();
        }
        return null;
    }
}

// learning material: https://www.tutorialspoint.com/java/java_serialization.htm

// For remebering:
/*
    static field belongs to the class itself, not to any specific object
    All instances of the class share the same static variable, and it is initialized only once when 
    the class is loaded
 */
