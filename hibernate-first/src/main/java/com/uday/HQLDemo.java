import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HQLDemo {
    public static void main(String[] args) {
        // 1. Create Configuration and Build SessionFactory
        // This loads hibernate.cfg.xml and prepares the DB connection
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // 2. Open a Session
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // --- SCENARIO A: SELECT ALL (The basic FROM clause) ---
            // Note: Use the Class Name "Employee", not the table name "employees"
            String hqlSelectAll = "FROM Employee";
            Query<Employee> query1 = session.createQuery(hqlSelectAll, Employee.class);
            List<Employee> employeeList = query1.getResultList();
            System.out.println("All Employees: " + employeeList);


            // --- SCENARIO B: WHERE CLAUSE with Named Parameters ---
            // Use ':' followed by a label to prevent SQL Injection  --> also called postional paramaeters
            String hqlWhere = "FROM Employee E WHERE E.department = :dept AND E.salary > :sal";
            Query<Employee> query2 = session.createQuery(hqlWhere, Employee.class);
            query2.setParameter("dept", "IT"); // Setting the :dept parameter
            query2.setParameter("sal", 50000.0); // Setting the :sal parameter
            List<Employee> filteredList = query2.getResultList();


            // --- SCENARIO C: PROJECTIONS (Selecting specific columns) ---
            // This returns a List of Object arrays because it's not a full Entity
            String hqlProjection = "SELECT E.name, E.salary FROM Employee E";
            Query<Object[]> query3 = session.createQuery(hqlProjection, Object[].class);
            List<Object[]> results = query3.getResultList();
            for (Object[] row : results) {
                System.out.println("Name: " + row[0] + ", Salary: " + row[1]);
            }


            // --- SCENARIO D: UPDATE (Bulk Operation) ---
            // MutationQuery is used in Hibernate 6+ for non-SELECT HQL
            String hqlUpdate = "UPDATE Employee SET salary = salary + 1000 WHERE department = 'IT'";
            int rowsAffected = session.createMutationQuery(hqlUpdate).executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);


            // --- SCENARIO E: AGGREGATE FUNCTIONS ---
            String hqlCount = "SELECT COUNT(E.id) FROM Employee E";
            Long count = session.createQuery(hqlCount, Long.class).getSingleResult();
            System.out.println("Total Employees: " + count);

            tx.commit(); // Finalize changes to the DB
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Undo changes if an error occurs
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
