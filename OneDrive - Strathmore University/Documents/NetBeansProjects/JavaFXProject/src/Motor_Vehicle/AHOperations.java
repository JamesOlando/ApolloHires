package Motor_Vehicle;

import java.sql.*;

public class AHOperations extends AHConnect {

  @Override
public String[] selectOperation(String id) {
    String[] clientInfo = new String[4]; // Array to hold client information
    try {
        // Step 3: Create a Statement    
        String query = "SELECT * FROM clients WHERE client_id=?"; 
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, id);

        // Step 4: Execute Statement
        ResultSet rs = pst.executeQuery();

        // Step 5: Process ResultSet
        if (rs.next()) { // Check if a record is found
            clientInfo[0] = rs.getString("client_id"); // Client ID
            clientInfo[1] = rs.getString("client_firstname"); // First Name
            clientInfo[2] = rs.getString("client_surname"); // Surname
            clientInfo[3] = String.valueOf(rs.getInt("client_contact")); // Contact as String
        } else {
            return null; // Return null if no client is found
        }

        // Step 6: Close Connection
        //con.close();
    } catch (SQLException sqle) {
        System.out.println("Select Operation failed: " + sqle.getMessage());
        return null; // Return null on error
    }
    
    return clientInfo; // Return the array with client information
}


    @Override
    public boolean insertOperation(String id, String firstname, String surname, int contact) {
    boolean isInserted = false; // Flag to track insertion success

    try {
        // Step 3: Create a Statement
        String query = "INSERT INTO clients(client_id, client_firstname, client_surname, client_contact) VALUES(?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, id);
        pst.setString(2, firstname);
        pst.setString(3, surname);
        pst.setInt(4, contact);

        // Step 4: Execute Statement
        int rowsAffected = pst.executeUpdate();
        System.out.println("Insert successful, rows affected: " + rowsAffected);
        
        // Check if the insertion was successful
        if (rowsAffected > 0) {
            isInserted = true; // Set flag to true if insertion was successful
        }
    } catch (SQLException sqle) {
        System.out.println("Operation failed: " + sqle.getMessage());
    } finally {
        // Step 6: Close Connection
        try {
            if (con != null && !con.isClosed()) {
                con.close(); // Close the connection if it's not null and not already closed
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
    
    return isInserted; // Return true or false based on insertion success
}


    
   @Override
public boolean updateOperation(String id, String firstname, String surname, int contact) {
    // Step 3: Create a Statement
    try {
        String query = "UPDATE clients SET client_firstname = ?, client_surname = ?, client_contact = ? WHERE client_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, firstname);
        pst.setString(2, surname);
        pst.setInt(3, contact);
        pst.setString(4, id);

        // Step 4: Execute Statement
        int rowsAffected = pst.executeUpdate();
        
        // Step 6: Close Connection
        con.close();
        
        // Return true if at least one row was updated
        return rowsAffected > 0;

    } catch (SQLException sqle) {
        System.out.println("Update Operation failed: " + sqle.getMessage());
        return false; // Return false in case of an exception
    }
}


    
    @Override
public boolean deleteOperation(String id) {
    // Step 3: Create a Statement
    String query = "DELETE FROM clients WHERE client_id = ?"; // Define the query

    try (PreparedStatement pst = con.prepareStatement(query)) { // Use try-with-resources to automatically close the PreparedStatement
        pst.setString(1, id); // Set the client ID

        // Step 4: Execute Statement
        int rowsAffected = pst.executeUpdate(); // Execute the delete statement
        
        // Step 5: Check if the deletion was successful
        if (rowsAffected > 0) {
            System.out.println("Delete successful, rows affected: " + rowsAffected);
            return true; // Return true if a record was deleted
        } else {
            System.out.println("No record found with the given ID.");
            return false; // Return false if no record was deleted
        }
    } catch (SQLException sqle) {
        System.out.println("Operation failed: " + sqle.getMessage());
        return false; // Return false in case of an SQL exception
    } finally {
        try {
            if (con != null && !con.isClosed()) {
                con.close(); // Close the connection
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}


    

   
    
}
