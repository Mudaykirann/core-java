package services;

public class Java implements ICourse {

    public Java(){
        System.out.println("Java Bean is Created.");
    }

    public boolean getTheCourse(Double Price) {
        System.out.println("Java course is purchased and fee pais is " + Price);
        return true;
    }
}
