/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Client;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class MessengerServiceImpl extends UnicastRemoteObject implements MessengerService{
    private Client client = new Client("", "");
    
    public MessengerServiceImpl() throws RemoteException {}

    @Override
    public void sendMessage(String user, String message) throws RemoteException {
        this.client.setName(user);
        this.client.setMessage(message);
    }

    @Override
    public String getMessage() throws RemoteException {
        while(client.getMessage() == null){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.err.println("Interrupted Exception: " + ex.getMessage());
            }
        }
        return client.toString();
    }

    @Override
    public void setMessage(String message) throws RemoteException {
        this.client.setMessage(message);
    }
    
}
