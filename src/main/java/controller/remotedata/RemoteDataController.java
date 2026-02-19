/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.remotedata;

import controller.service.ServiceController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import view.remotedata.RemoteDataJDialog;

/**
 *
 * @author W10-Portable
 */
public class RemoteDataController {
    private RemoteDataJDialog view;
    private ServiceController serviceController;

    public RemoteDataController(RemoteDataJDialog view) {
        this.view = view;
        this.view.setConnectButtonActionListener(this.getConnectButtonActionListener());
        this.serviceController = new ServiceController();
    }
    
    private ActionListener getConnectButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rmiBind = serviceController.getRmiBind();
                String remoteIP = getRemoteIP();
                
                if(rmiBind == null && remoteIP == null){
                    JOptionPane.showMessageDialog(view, "Los campos deben de estar rellanados para establecer la conexión");
                    System.exit(0);
                } else {
                    try {
                        serviceController.connectClient(remoteIP);
                    } catch (RemoteException | NotBoundException ex) {
                        JOptionPane.showMessageDialog(view, "Error al conectar el cliente RMI: " + ex.getMessage(), "Error en el servicio", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                    
                }
                view.dispose();
            }
        };
        return al;
    }
    
    private String getRemoteIP(){
        if(view.getTextRemoteTextIpTextField() != null){
            return view.getTextRemoteTextIpTextField();
        } else {
            System.out.println("No hay texto");
            return null;
        }
    }
}
