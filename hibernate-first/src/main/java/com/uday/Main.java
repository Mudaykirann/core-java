package com.uday;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * JDBC & Hibernate Demo
 * Purpose: Demonstrates CRUD operations using JPA-compliant methods in Hibernate.
 */
public class Main {
    public static void main(String[] args) {

        // 1. Initialize the Embeddable object (Value Object)
//        EmployeeName name = new EmployeeName();
//        name.setFname("Ramu");
//        name.setLname("Ram");
//        name.setSname("Mangala");


        // ***Showing how the Mapping relations would be in hibernate *** ;;;
        Laptop l = new Laptop();
        l.setLid(101);
        l.setLname("Dell");


        Student s = new Student();
        s.setId(1);
        s.setName("Kiran");
        s.setMarks(90);
        //adding laptops to the student;
        s.getL().add(l); // one student got many laptops --> onetomany

        //adding student into the laptops
        l.getStudent().add(s); // Many laptops for the many students --> manytomany



        // 2. Initialize the Entity object
//        Employee emp = new Employee();
//        emp.setId(2408);
//        emp.setName(name); // Mapping the embedded object to the entity
//        emp.setDesignation("Consultant");

        // 3. Configuration & SessionFactory setup
        // addAnnotatedClass tells Hibernate to scan the Employee class for JPA annotations
        // configure() loads settings from hibernate.cfg.xml
//        SessionFactory factory = new Configuration()
//                .addAnnotatedClass(com.uday.Employee.class)
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();


        SessionFactory factory = new Configuration()
               .addAnnotatedClass(com.uday.Laptop.class)
               .addAnnotatedClass(com.uday.Student.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // 4. Open a Session (the interface to the database)
        Session session = factory.openSession();

        // 5. Transaction Management (Mandatory for DML like Insert/Update/Delete)
        Transaction t = session.beginTransaction();

        try {
            /* --- JPA COMPLIANT CRUD OPERATIONS --- */

            // CREATE: persist() makes the object managed and schedules it for insert
            //session.persist(emp);


            //for lotp and student calss
            session.persist(l);
            session.persist(s);

            // UPDATE: merge() is used to save changes to an existing record
            // session.merge(emp);

            // DELETE: remove() deletes a managed entity from the database
            // session.remove(emp);

            // READ: find() replaces the deprecated get() and load() methods
            // Employee retrievedEmp = session.find(Employee.class, 2408);
            // System.out.println(retrievedEmp);

            // 6. Commit the changes to the database
            t.commit();
            System.out.println("Employee saved successfully!");

        } catch (Exception ex) {
            // Rollback if any error occurs to maintain data integrity
            if (t != null) t.rollback();
            ex.printStackTrace();
        } finally {
            // 7. Resource Cleanup: Close the session and factory to prevent memory leaks
            session.close();
            factory.close();
        }
    }
}
