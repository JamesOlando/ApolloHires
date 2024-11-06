package RMI;
import java.rmi.Remote;
import java.rmi.RemoteException;;


public interface DatabaseInt extends Remote {
    public boolean insertOperation(String id, String firstname, String surname, int contact) throws RemoteException;
    public String[] selectOperation(String id) throws RemoteException;
    public boolean updateOperation(String id, String firstname, String surname, int contact) throws RemoteException;
    public boolean deleteOperation(String id) throws RemoteException;
    
}