public class UserFactory {
    // Factory method to create User objects
    // This method can be used without creating a UserFactory instance
    public static User createUser(String type, String name) {
        if (type.equalsIgnoreCase("student")) {
            System.out.println("\n[Factory Design Pattern] Student " + name + " has been created.");
            return new Student(name);
        } 
        else if (type.equalsIgnoreCase("teacher")) {
            System.out.println("\n[Factory Design Pattern] Teacher " + name + " has been created.");
            return new Teacher(name);
        }
        throw new IllegalArgumentException("Unknown user type: " + type);
    }
}

// For remebering:
/*
  1. Encapsulate the object creation process into a specialized class (factory), rather than having
  the object created directly by the consumer (client) new
  2. When you add a new user type in the future, just add a branch here
  3. Implements the design principle of "open to extension, closed to modification" (OCP)
 */