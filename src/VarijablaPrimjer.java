public class VarijablaPrimjer {
    public static void main(String[] args) {
        String name = "Faris";
        int age = 21;
        double height = 1.91;
        boolean isStudent = true;
        char grade = 'A';
        String city = "Gračanica";
        String faculty = "IPI Akademija";

        System.out.println("Zovem se " + name + ", imam " + age + " godina i visok sam " + height + " m.");
        System.out.println("Živim u " + city + " i studiram na " + faculty + ".");
        System.out.println("Student: " + isStudent + ", ocjena: " + grade);

        // Greške u tipu (makni // ispred linije da vidiš grešku):
        // int test1 = "21";    // String se ne može staviti u int
        // int test2 = 1.91;    // double se ne može staviti u int (gubi se decimala)
        // char test3 = "A";    // char ide u jednostrukim navodnicima 'A', ne "A"
    }
}