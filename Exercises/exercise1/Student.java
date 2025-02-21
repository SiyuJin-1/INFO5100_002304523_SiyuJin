import java.util.ArrayList;
public abstract class Student {
    String name;
    ArrayList<Integer> quizScores = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }
    
    // output student information，need to be implemented by FullTimeStudent and PartTimeStudent classes
    public abstract void OutputInfo();
    
    // get student quiz scores
    public ArrayList<Integer> getQuizScores(){
        return quizScores;
    }
    
    // set quiz scores for the student
    public void setQuizScores(ArrayList<Integer> quizScores){
        this.quizScores = quizScores;
    }
}
