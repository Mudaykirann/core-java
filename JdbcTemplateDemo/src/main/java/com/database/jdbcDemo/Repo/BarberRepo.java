package com.database.jdbcDemo.Repo;

import com.database.jdbcDemo.model.Barber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marks this class as a Data Access Object (DAO) and enables component scanning
public class BarberRepo {

    private JdbcTemplate template;

    // Getter for the JdbcTemplate (optional, but sometimes useful for debugging)
    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired // Tells Spring to automatically inject the JdbcTemplate bean here
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // --- CREATE ---
    public void save(Barber b) {
        // SQL query with ? placeholders to prevent SQL Injection
        String sql = "INSERT INTO barber (id, name, city) VALUES (?, ?, ?)";
        // .update() is used for any query that MODIFIES the database (Insert, Update, Delete)
        int rows = template.update(sql, b.getId(), b.getName(), b.getCity());
        System.out.println(rows + " row(s) inserted.");
    }

    // --- READ (All) ---
    public List<Barber> findAll() {
        String sql = "SELECT * FROM barber";

        // Using a Lambda expression instead of an anonymous inner class for the RowMapper
        // (rs, rowNum) represents the current ResultSet row and the row index
        return template.query(sql, (rs, rowNum) -> {
            Barber b = new Barber();
            b.setId(rs.getInt("id"));     // Getting data by column name is safer than index
            b.setName(rs.getString("name"));
            b.setCity(rs.getString("city"));
            return b;
        });
    }

    // --- READ (By ID) ---
    public Barber findById(int id) {
        String sql = "SELECT * FROM barber WHERE id = ?";
        // .queryForObject expects exactly ONE row back; otherwise, it throws an exception
        return template.queryForObject(sql, (rs, rowNum) -> {
            Barber b = new Barber();
            b.setId(rs.getInt("id"));
            b.setName(rs.getString("name"));
            b.setCity(rs.getString("city"));
            return b;
        }, id); // Passing 'id' to fill the ? placeholder
    }

    // --- UPDATE ---
    public void update(Barber b) {
        // SQL to update an existing record based on the ID
        String sql = "UPDATE barber SET name = ?, city = ? WHERE id = ?";
        // .update returns an int representing how many rows were modified
        int rows = template.update(sql, b.getName(), b.getCity(), b.getId());
        System.out.println(rows + " row(s) updated.");
    }

    // --- DELETE ---
    public void deleteById(int id) {
        String sql = "DELETE FROM barber WHERE id = ?";
        // Standard .update call for deletion
        int rows = template.update(sql, id);
        System.out.println(rows + " row(s) deleted.");
    }
}