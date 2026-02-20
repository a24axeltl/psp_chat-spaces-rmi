/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.service;

import java.net.Inet4Address;
import java.net.UnknownHostException;
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
    private final String RMI_BIND = "MessengerATL";
    private final int PORT = 1099;
    
    public void createRmiRegistry() throws RemoteException{
        thisService = new MessengerServiceImpl(); //Nuestro propio servicio;
            
        Registry registryRMI = LocateRegistry.createRegistry(PORT); //Obtener nuestro registro RMI;
        registryRMI.rebind(RMI_BIND, thisService); //Registrado como "MessengerService", ahora se puede acceder usando ese nombre;
            
        System.out.println("Servicio publicado");
    }
    
    public void connectClient(String remoteIP) throws RemoteException, AccessException, NotBoundException{
        Registry remotRegistry = LocateRegistry.getRegistry(remoteIP,PORT); //Obtener el registro remoto del usuario;
        remoteService = (MessengerService) remotRegistry.lookup(RMI_BIND);//Obtener el servicio del usuario remoto a traves de su registro RMI;
        
        System.out.println("Se ha conectado remotamente");
    }
    
    public String getRmiBind(){
        return RMI_BIND;
    }
    
    public String getThisIP(){
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            System.err.println("Error detecting the IP");
            System.exit(0);
            return null;
        }
    }
    
    public MessengerService getRemoteService(){
        return this.remoteService;
    }
    
    public void sendMessage(String user, String message) throws RemoteException{
        remoteService.sendMessage(user, message);
    }
    
    public String getMessage() throws RemoteException{
        return remoteService.getMessage();
    }
    
    public void setMessage(String message) throws RemoteException{
        remoteService.setMessage(message);
    }
    
    public String getRemoteName() throws RemoteException{
        return remoteService.getNameClient();
    }
}
