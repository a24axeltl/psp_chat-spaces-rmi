/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspRmiServer {

    public static void main(String[] args) {
        int port = 1099;
        String bindName = "MessageServer";
        
        try {
            //-Crear instancia de la implementacion de la interfaz
            MessengerService service = new MessengerServiceImpl();
            MessengerService stub = (MessengerService) UnicastRemoteObject.exportObject(service, 0);
            
            //-Registro del bind
            Registry registry = LocateRegistry.createRegistry(port);
            
            registry.bind(bindName, stub);
            
            System.out.println("RMI Server listo");
            System.out.println("Registry en puerto: " + port);
            System.out.println("Servicio publicado como: " + bindName);
            
        } catch (RemoteException ex) {
            System.err.println("Error al arrancar el servidor RMI: " + ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.err.println("Error al crear el registro RMI: " + ex.getMessage());
        }
        
    }
}
