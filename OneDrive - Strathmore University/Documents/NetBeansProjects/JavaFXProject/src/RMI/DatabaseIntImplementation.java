/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Motor_Vehicle.AHOperations;


public class DatabaseIntImplementation extends UnicastRemoteObject implements DatabaseInt{
     AHOperations aho = null;
   
     
    public DatabaseIntImplementation() throws RemoteException{
        aho = new AHOperations();
    }

    @Override
    public boolean insertOperation(String id, String firstname, String surname, int contact) throws RemoteException {
        aho.insertOperation(id, firstname, surname, contact);
         return true;
    }

    @Override
    public String[] selectOperation(String id) throws RemoteException {
         String[] clientInfo = aho.selectOperation(id);
         return clientInfo;
    }

    @Override
    public boolean updateOperation(String id, String firstname, String surname, int contact) throws RemoteException {
        aho.updateOperation(id, firstname, surname,contact);
         return true;
    }

    @Override
    public boolean deleteOperation(String id) throws RemoteException {
        aho.deleteOperation(id);
         return true;
    }

    
    
}





