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


        ps.setString(1, "Kiran Mangala");
        ps.setString(2, "Developer");
        ps.setString(3, "Hyderabad");   
        ps.setString(4, "9988776655");
        ps.setDouble(5, 25000.00);

        int rowsinserted = ps.executeUpdate();

        if(rowsinserted > 0){
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

