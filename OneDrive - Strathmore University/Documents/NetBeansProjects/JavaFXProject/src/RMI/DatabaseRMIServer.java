package RMI;


import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class DatabaseRMIServer {
    public static void main(String[] args){
        try {
            DatabaseIntImplementation dii= new DatabaseIntImplementation(); 
            
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.bind("ApolloHiresReg", (Remote) dii);
            
            System.out.println("Bind successful; Server is running");
        }catch (RemoteException ex){
            ex.getMessage();
        } catch (AlreadyBoundException ex) {
            ex.getMessage();
        }
    
    }
}