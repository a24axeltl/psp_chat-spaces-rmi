/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.MessengerService;
import service.MessengerServiceImpl;

/**
 *
 * @author W10-Portable
 */
public class ServiceController {
    private MessengerService thisService; //Nuestro propio servicio;
    private MessengerService remoteService; //Servicio del otro usuario;
    private int PORT = 1099;
    
    private void sendRmiRegistry() throws RemoteException{
        thisService = new MessengerServiceImpl(); //Nuestro propio servicio;
            
        Registry registryRMI = LocateRegistry.createRegistry(PORT); //Obtener nuestro registro RMI;
        registryRMI.rebind("MessengerService", thisService); //Registrado como "MessengerService", ahora se puede acceder usando ese nombre;
            
        System.out.println("Servicio publicado");
    }
    
    private void connectClient() throws RemoteException, AccessException, NotBoundException{
        Registry remotRegistry = LocateRegistry.getRegistry("127.0.0.1",PORT); //Obtener el registro remoto del usuario;
        remoteService = (MessengerService) remotRegistry.lookup("MessengerService");//Obtener el servicio del usuario remoto a traves de su registro RMI;
        
        System.out.println("Se ha conectado remotamente");
    }
}
