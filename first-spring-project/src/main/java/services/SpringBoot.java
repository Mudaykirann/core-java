package services;

public class SpringBoot implements ICourse {


    public SpringBoot(){
        System.out.println("Spring boot Bean is Created.");
    }
    public boolean getTheCourse(Double Price){
        System.out.println("Spring course is purchased and fee pais is "+ Price);
        return  true;
    }
}
