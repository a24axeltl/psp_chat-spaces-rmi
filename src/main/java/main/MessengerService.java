/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public interface MessengerService extends Remote{
    String sendMessage(String clientMessage) throws RemoteException;
}
