/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.rmi.RemoteException;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class MessengerServiceImpl implements MessengerService{

    @Override
    public String sendMessage(String clientMessage) throws RemoteException {
        return clientMessage;
    }
}
