public class SchoolSystem {
    private static SchoolSystem instance; // Singleton instance

    private SchoolSystem() {
        System.out.println("\n[Singleton Design Pattern] SchoolSystem instance has been created.");
    }

    public static SchoolSystem getInstance() {
        if (instance == null) {
            instance = new SchoolSystem();
        }
        return instance;
    }

    public void sendMessage(NotificationService service, String message) {
        System.out.println("\n[SchoolSystem] Publishing notification: " + message + " to all observers.");
        service.notifyObservers(message);
    }
}

// For remebering:
/*
  1. The core of Singleton is that there is only one instance of the entire program, 
  and if the constructor is public, other code can arbitrarily new Singleton(), 
  breaking the uniqueness of the singleton.
  2. The private constructor controls the uniqueness of the instance by ensuring that all code 
  must obtain the instance through getInstance().
  3. getInstance() : Must be public, otherwise the instance cannot be obtained externally.
 */
