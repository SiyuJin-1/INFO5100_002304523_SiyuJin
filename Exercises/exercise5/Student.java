public class Student implements User {
    private String name;

    // Constructor
    public Student(String name) {
        this.name = name;
    }

    // Update the student when a notification is received
    public void update(String message) {
        System.out.println("\n[Observer Design Pattern] Notifying " + name + ": " + message);
    }

    public String getName() {
        return name;
    }
}
