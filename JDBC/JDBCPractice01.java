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

        //Inserting an new empployee record
        String query ="INSERT INTO employees (Name,Department,City,Number,Salary) VALUES (?,?,?,?,?)";

        // Modern driver name includes '.cj.'
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection c = DriverManager.getConnection(url,user,password)){    

        //Optimizing the 
        PreparedStatement ps = c.prepareStatement(query);
        //ResultSet rs = s.executeQuery(query);


        // ps.setString(1, "Kiran Mangala");
        // ps.setString(2, "Developer");
        // ps.setString(3, "Hyderabad");   
        // ps.setString(4, "9988776655");
        // ps.setDouble(5, 25000.00);



        //Batch processing can also be done using addBatch() and executeBatch() methods
        //Adding multiple records using batch processing
        ps.setString(1, "Anil Kumar");
        ps.setString(2, "Tester");
        ps.setString(3, "Bangalore");
        ps.setString(4, "8877665544");
        ps.setDouble(5, 22000.00);
        ps.addBatch();

        ps.setString(1, "Sita Reddy");
        ps.setString(2, "Manager");
        ps.setString(3, "Chennai");
        ps.setString(4, "7766554433");
        ps.setDouble(5, 30000.00);
        ps.addBatch();


        // to execute the batch 
        //ps.executeUpdate(): This is where the double-entry happens. This method executes the current state of the PreparedStatement. 
        // Since "Sita Reddy" was the last set of values you bound to the ps object, 
        // executeUpdate() runs that specific insert query one more time. which leads to the duplicate entry error.
        //to avoid this we use executeBatch() method in the below specific way

        int [] rowsInserted = ps.executeBatch();

        if(rowsInserted.length > 0){
            System.out.println("A new employee was inserted successfully!");
        }

        // while(rs.next()){
        //     String Name = rs.getString("Name");
        //     String dep = rs.getString("Department");
        //     String City = rs.getString("City");
        //     double sal = rs.getDouble("Salary");
        //     System.out.println(Name + " - " + dep + " - " + City + " - " + sal);
        // }

        ps.close();
        c.close();
    }
    catch(SQLException e){
        e.printStackTrace();
    }
}
}

