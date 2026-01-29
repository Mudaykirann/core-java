//Java Database Connectivity (JDBC) Practice File

/*
1.import 
2.load and register the driver  --> com.mysql.jdbc.Driver;
3.establish the connection
4.create a statement
5.execute the query
6.process the results
7.close the connection

*/

import java.sql.*;

class JDBCPractice01 {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, JDBC!");

        String url="jdbc:mysql://localhost:3306/employees";
        String user="root";
        String password="uday";
        String query ="select * from employees where Number='555-1234'";

        // Modern driver name includes '.cj.'
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(url,user,password);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(query);

        rs.next();

        String name = rs.getString("Salary");

        System.out.println("Salary of Alice Smith is: " + name);

        s.close();
        c.close();
    }
}

