package com.uday;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Alien a1 = new Alien();

        a1.setAid(104);
        a1.setAname("Lakshmi");
        a1.setAcolor("yellow");




        SessionFactory factory = new Configuration()
                .addAnnotatedClass(com.uday.Alien.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.openSession();

        Transaction t = session.beginTransaction();

        //to update the particular row we need to merge them
        //session.merge(a1);


        // to delete the particular row from the table just pass the object name
        //session.remove(a1);



        // to get the data --> get is depricated
//        Alien a2 = session.find(Alien.class,101); //101 must be primary key
//        System.out.println(a2.toString());


        // persist only use for creating a row newly
        //session.persist(a1);

        t.commit();
        session.close();
        factory.close();
    }
}