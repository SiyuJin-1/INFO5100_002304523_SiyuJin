import java.util.ArrayList;
import java.util.Random;

public class Main {
    // generate random scores for students，need to be static
    public static ArrayList<Integer> generateScores(int count) {
        ArrayList<Integer> scores = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            scores.add(rand.nextInt(31) + 70);
        }
        return scores;      
    }

    // main method
    public static void main(String[] args) {
        Session session = new Session();
        Random rand = new Random();

        // add 20 students to the session，randomly assign them as FullTimeStudent or PartTimeStudent
        for (int i = 1; i <= 20; i++) {
            Student student;
            if (rand.nextBoolean()) {
                student = new FullTimeStudent("FullTime_S" + i);
                // downcasting，create 2 extra exam scores for FullTime student
                ((FullTimeStudent) student).setExtraScores(generateScores(2));
            }
            else {
                student = new PartTimeStudent("PartTime_S" + i);
            }
            // create 15 quiz scores for each student
            student.setQuizScores(generateScores(15));
                
            // add student to the session
            session.addStudent(student);
        }

        // output original quiz scores for each student
        session.outputOriginalQuizScores();

        // output student average quiz scores
        session.calculateAverageQuizScores();

        // output sorted quiz scores (sort by each student, ascending order)
        session.outputSortedQuizScoresByStudent();

        // output sorted quiz scores (sort by each quiz, ascending order)
        session.outputSortedQuizScoresByQuiz();

        // output all part-time students
        session.outputPartTimeStudents();

        // output all full-time students' extra exam scores
        session.printFullTimeExamScores();
    }
}


/* notes:
   main() is a static method.
   A non-static method belongs to an instance (object), whereas a static method belongs to the class.
   A static method cannot directly access a non-static method unless called through an instance.
   But a non-static method can access a static method directly.
 */



