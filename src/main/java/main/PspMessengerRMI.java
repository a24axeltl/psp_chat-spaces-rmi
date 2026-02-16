/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controller.FrontController;
import view.MainJFrame;

/**
 *
 * @author dam2_alu13@inf.ald
 */
public class PspMessengerRMI {
    public static void main(String[] args) {
        /*
        String name = "Usuario01";
        
        try {
            sendRmiRegistry();
            connectClient();
            
            //remoteService.sendMessage(name, "Hola esto es un mensaje de prueba!!");
            remoteService.getMessage();
        } catch (RemoteException ex) {
            System.err.println("Remote Exception: " + ex.getMessage());
            System.exit(0);
        } catch (NotBoundException ex) {
            System.err.println("Not bound Exception: " + ex.getMessage());
        }
        */
        MainJFrame mainView = new MainJFrame();
        FrontController mainController = new FrontController(mainView);
        
        mainView.setVisible(true);
    }
}
