/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import service.MessengerServiceImpl;
import service.MessengerService;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspMessengerRMI {
    private static int PORT = 1099;
    private static MessengerService remoteService; //Servicio del otro usuario;
    
    public static void main(String[] args) {
        String name = "Usuario01";
        
        try {
            MessengerService thisService = new MessengerServiceImpl(); //Nuestro propio servicio;
            
            Registry registryRMI = LocateRegistry.createRegistry(PORT); //Obtener nuestro registro RMI;
            registryRMI.rebind("MessengerService", thisService); //Registrado como "MessengerService", ahora se puede acceder usando ese nombre;
            
            System.out.println("Servicio publicado");
            
            connectClient();
            
            //remoteService.sendMessage(name, "Hola esto es un mensaje de prueba!!");
            remoteService.getMessage();
        } catch (RemoteException ex) {
            System.err.println("Remote Exception: " + ex.getMessage());
            System.exit(0);
        } catch (NotBoundException ex) {
            System.err.println("Not bound Exception: " + ex.getMessage());
        }
    }
    
    private static void connectClient() throws RemoteException, AccessException, NotBoundException{
        Registry remotRegistry = LocateRegistry.getRegistry("127.0.0.1",PORT); //Obtener el registro remoto del usuario;
        remoteService = (MessengerService) remotRegistry.lookup("MessengerService");//Obtener el servicio del usuario remoto a traves de su registro RMI;
        
        System.out.println("Se ha conectado remotamente");
    }
}
