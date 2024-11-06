package Motor_Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
        
public abstract class AHConnect {
    public Connection con = getConnection();
    
    public Connection getConnection()
    {
       Connection connection = null;
       
        try {
            //Step 1:Load Driver
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded successfully");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Could not load driver" + cnfe.getMessage());
        }
       
       //Step 2: Establish connection
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ApolloHires","postgres", "Rollingstone@20");
            System.out.println("Connection established");
        } catch (SQLException sqle) {
            System.out.println("Could not establish connection" + sqle.getMessage());
        }
        
        return connection;
       
      
    } 
    
    public abstract String[] selectOperation(String id);
    public abstract boolean insertOperation(String id, String firstname, String surname, int contact);
    public abstract boolean updateOperation(String id, String firstname, String surname, int contact);
    public abstract boolean deleteOperation(String id);
    
}
//String id,String firstname,String surname, int contact
//String id,String firstname,String surname, int contact
//String id