/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspRmiClient {

    public static void main(String[] args) {
        try {
            //Localizar bind del servidor
            Registry registry = LocateRegistry.getRegistry();
            MessengerService server = (MessengerService) registry.lookup("MessageServer");
            
            //Obtener Mensaje
            String responseMessage = server.sendMessage("Mensaje Cliente");
            System.out.println(responseMessage);
            
        } catch (RemoteException ex) {
            System.err.println("Error al localizar el registro: " + ex.getMessage());
        } catch (NotBoundException ex) {
            System.err.println("Error al conectar con el servicio RMI: " + ex.getMessage());
        }
    }
}
