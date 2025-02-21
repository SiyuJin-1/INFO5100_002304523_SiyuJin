import java.util.ArrayList;
public class FullTimeStudent extends Student {
    ArrayList<Integer> examScores;

    public FullTimeStudent(String name) {
        super(name); 
        examScores = new ArrayList<>(); // an extra field to hold two Exam scores
    }

    // set extra exam scores for the student
    public void setExtraScores(ArrayList<Integer> examScore){
        this.examScores = examScore;
    }

    // get extra exam scores for the student
    public ArrayList<Integer> getExtraScores(){
        return examScores;
    }

    // output student information
    @Override
    public void OutputInfo(){
        System.out.println("Full-Time Student: " + name);
    }
}
