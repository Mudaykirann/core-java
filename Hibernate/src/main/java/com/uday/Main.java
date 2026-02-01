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

        a1.setAid(102);
        a1.setAname("Mohan Ranga");
        a1.setAcolor("Red");

        //hibernate with a1 object;
        Configuration con = new Configuration();
        con.addAnnotatedClass(com.uday.Alien.class);
        con.configure("hibernate.cfg.xml");



        SessionFactory factory = con.buildSessionFactory();

        Session session = factory.openSession();

        Transaction t = session.beginTransaction();

        session.persist(a1);

        t.commit();
        session.close();
        factory.close();
    }
}