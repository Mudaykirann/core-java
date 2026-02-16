package APP;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.TshapedSKills;

public class BeanImplementation {
    public static void main(String[] args) {



        //Implementation of Bean Factory

        //example of an lazy intialization as the output show nohtinh means until we are goinf tp use bean , spring wil not create that
        // only usable bean is created and shows you an output of usd bean in this BeanFactory

        DefaultListableBeanFactory container = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader read = new XmlBeanDefinitionReader(container);
        read.loadBeanDefinitions("applicationconfig.xml");

        //we can load many xml files used read - one of the feature of beanFactory





        TshapedSKills t = container.getBean(TshapedSKills.class); //give me the bean object of TshapedSkills class to collect in t;
        boolean status = t.buyTheCourse(444.44);

        //dependency injection is success by injecting the java class into the TshapedSkills class by the spring

        if (status)
            System.out.println("Course purchased successfully ");
        else
            System.out.println("Failed to get the course");


    }
}
