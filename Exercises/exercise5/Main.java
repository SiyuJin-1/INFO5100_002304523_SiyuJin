public class Main {
    public static void main(String[] args) {
        System.out.println("\n------------------------Exercise-5-----------------------");

        // Create system instance (Singleton)
        SchoolSystem system = SchoolSystem.getInstance();

        // Create users using Factory
        User student = UserFactory.createUser("student", "Siyu Jin");
        User teacher = UserFactory.createUser("teacher", "Taral Oza");

        // Subscribe users to notifications (Observer)
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(student);
        notificationService.addObserver(teacher);

        // Publish a notification
        system.sendMessage(notificationService, "Northeastern Silicon Valley campus will remain closed on Monday, March 31, 2025.");

        System.out.println("\n------------------------End-----------------------\n");
    }
}
