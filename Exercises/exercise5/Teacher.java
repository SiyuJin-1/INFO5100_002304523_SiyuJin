public class Teacher implements User {
    private String name;
    
    // Constructor
    public Teacher(String name) {
        this.name = name;
    }

    // Update the teacher when a notification is received
    public void update(String message) {
        System.out.println("\n[Observer Design Pattern] Notifying " + name + ": " + message);
    }

    public String getName() {
        return name;
    }
}
