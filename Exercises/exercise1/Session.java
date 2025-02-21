import java.util.ArrayList;
import java.util.Collections;

public class Session {
    ArrayList<Student> students;

    public Session() {
        this.students = new ArrayList<>();
    }

    // add student to the session
    public void addStudent(Student student) {
        students.add(student);
    }

    // calculate average quiz scores per student for the whole class
    public void calculateAverageQuizScores() {
        System.out.println("\n----------------Average Quiz Scores Per Student------------------\n");
        for (Student student : students) {
            // calculate sum of the quiz scores
            int sum = 0;
            for (int i: student.getQuizScores()) {
                sum += i;
            }
            // calculate average quiz score
            double AverageQuizScore = sum / student.getQuizScores().size();
            student.OutputInfo();
            System.out.println("Average Quiz Scores: " + AverageQuizScore + "\n");
        }
    }

    // output original quiz scores for each student
    public void outputOriginalQuizScores() {
        System.out.println("\n-------------------Original Quiz Scores for each student-------------------\n");
        for (Student student : students) {
            student.OutputInfo();
            System.out.println("original scores: " + student.getQuizScores() + "\n");
        }
    }

    /*
       I have some problems to this part of the assignment requirement:
       "Create a public method to print the list of quiz scores in ascending order for one session."

       I'm not sure whether it means:
       1. Sorting the 15 quiz scores for each student individually 
       (i.e., sorting Student 1’s 15 quiz scores, Student 2’s 15 quiz scores, etc.).
       
       2. Sorting the scores of each quiz across all students 
       (i.e., sorting the scores for Quiz 1, then for Quiz 2, and so on).
        
       Therefore, I created both sorting methods:
       1. outputSortedQuizScoresByStudent(): sort each student's 15 quiz scores individually
       2. outputSortedQuizScoresByQuiz(): sort the scores for each quiz across all students
     */

    // sort quiz scores for each student
    public void outputSortedQuizScoresByStudent() {
        System.out.println("\n-------------------Sorted Quiz Scores for each student-------------------\n");
        for (Student student : students) {
            // copy the quiz scores, in case of changing the original list
            ArrayList<Integer> copyQuizScores = new ArrayList<>(student.getQuizScores()); 
            Collections.sort(copyQuizScores); // sort the quiz scores, ascending order
            student.OutputInfo();
            System.out.println("sorted scores: " + copyQuizScores + "\n");
        }
    }

    // sort quiz scores for each quiz
    public void outputSortedQuizScoresByQuiz() {
        System.out.println("\n-------------------Sorted Quiz Scores for each quiz-------------------\n");
        for (int i = 0; i < 15; i++) {
            // create a list to store the quiz scores for each quiz
            ArrayList<Integer> scores = new ArrayList<>();
            for (Student student : students) {
                scores.add(student.getQuizScores().get(i));
            }
            Collections.sort(scores); // sort the quiz scores, ascending order
            System.out.println("Quiz " + (i + 1));
            System.out.println("sorted scores: " + scores + "\n");
        }
    }

    // output all part-time students
    public void outputPartTimeStudents() {
        System.out.println("\n------------------------Part-Time Students------------------------\n");
        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                student.OutputInfo();
                System.out.println();
            }
        }
    }

    // output all full-time students' extra exam scores
    public void printFullTimeExamScores() {
        System.out.println("\n----------------------Full-Time Students Extra Exam Scores------------------\n");
        for (Student student : students) {
            // check if student is an instance of FullTimeStudent
            if (student instanceof FullTimeStudent) {
                // change student to FullTimeStudent
                FullTimeStudent ftStudent = (FullTimeStudent) student;
                ftStudent.OutputInfo();
                System.out.println("Extra Exam Scores: " + ftStudent.getExtraScores() + "\n");
            }
        }
    }
}

