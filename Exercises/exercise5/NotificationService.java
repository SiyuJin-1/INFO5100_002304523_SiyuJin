import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    // Store all objects that implement the Observer interface (Student, Teacher)
    private List<Observer> observers = new ArrayList<>();

    // Add an observer to the list
    // When a new observer is added, it will be notified of the current state
    public void addObserver(Observer o) {
        observers.add(o);
        System.out.println("\n[Observer Design Pattern] " + ((User) o).getName() + " has subscribed to notifications.");
    }

    // notify all observers when the state changes
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}
