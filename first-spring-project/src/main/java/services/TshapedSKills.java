package services;

public class TshapedSKills {


    private ICourse course;

    public TshapedSKills (ICourse c){
        super();
        System.out.println("Dependency injection through constructor.");
        this.course = c;
    }

    public TshapedSKills(){
        System.out.println("Tshaped skills Bean is Created.");
    }

    public void setCourse(ICourse c){
        System.out.println("Setter is called");
        this.course=c;
    }

    public boolean buyTheCourse(Double amount){
        return  course.getTheCourse(amount);
    }
}
