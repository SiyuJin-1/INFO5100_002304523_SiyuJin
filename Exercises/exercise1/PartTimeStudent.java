public class PartTimeStudent extends Student {

    public PartTimeStudent(String name) {
        super(name);
    }

    // output student information
    @Override
    public void OutputInfo(){
        System.out.println("Part-Time Student: " + name);
    }
}
