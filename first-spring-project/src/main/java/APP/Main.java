package APP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.TshapedSKills;

public class Main {
    public static void main(String[] args) {


        //applicationContext
        //Bean Factory

        ApplicationContext container = new ClassPathXmlApplicationContext("applicationconfig.xml");//activating the spring framework


        TshapedSKills t = container.getBean(TshapedSKills.class); //give me the bean object of TshapedSkills class to collect in t;
        boolean status = t.buyTheCourse(444.44);

        //dependency injection is success by injecting the java class into the TshapedSkills class by the spring

        if (status)
            System.out.println("Course purchased successfully ");
        else
            System.out.println("Failed to get the course");
    }
}
