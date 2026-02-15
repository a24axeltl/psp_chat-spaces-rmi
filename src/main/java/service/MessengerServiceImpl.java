/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class MessengerServiceImpl extends UnicastRemoteObject implements MessengerService{
    private String user;
    private String message;
    
    public MessengerServiceImpl() throws RemoteException {
        //super();
    }

    @Override
    public void sendMessage(String user, String message) throws RemoteException {
        this.user = user;
        this.message = message;
    }

    @Override
    public void getMessage() throws RemoteException {
        while(message == null){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.err.println("Interrupted Exception: " + ex.getMessage());
            }
        }
        System.out.println(user + ": " + message);
    }
    
}
