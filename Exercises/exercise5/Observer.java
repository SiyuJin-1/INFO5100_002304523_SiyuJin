public interface Observer {
    void update(String message);
}

// For remebering:
/*
  1. When an object(subject) changes state, it notifies multiple dependent objects (Observers).
  2. Decoupled observers and observed are not directly dependent on each other and communicate only
  through interfaces
  3. Automatic notification When the observer's state changes, the notification is automatically 
  triggered, and the observer does not need to constantly query it
  4. In this case, the observer is the student and teacher, and the observer must implement the
  Observer interface. The update method is called when the observed object (NotificationService)
  changes state.
 */