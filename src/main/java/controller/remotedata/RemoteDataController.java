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
    private boolean connectClient;
    private ServiceController serviceController;

    public RemoteDataController(RemoteDataJDialog view, boolean connectClient) {
        this.view = view;
        this.view.setConnectButtonActionListener(this.getConnectButtonActionListener());
        this.serviceController = new ServiceController();
        this.connectClient = connectClient;
        this.view.showremoteIPInfo(connectClient);
    }
    
    private ActionListener getConnectButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String remoteIP = getRemoteIP();
                String rmiBind = getRmiBind();
                
                if(rmiBind == null && remoteIP == null && !connectClient){
                    JOptionPane.showMessageDialog(view, "Los campos deben de estar rellanados para establecer la conexión");
                    System.exit(0);
                } else {
                    rmiServiceManagment(remoteIP, rmiBind);
                }
                view.dispose();
            }
        };
        return al;
    }
    
    private void rmiServiceManagment(String remoteIP, String bindName){
        try {
            System.out.println("RMI Managment");
            if (!connectClient) {
                serviceController.createRmiRegistry(bindName);
            } else {
                serviceController.connectClient(bindName, remoteIP);
            }
        } catch (NotBoundException | RemoteException ex) {
            JOptionPane.showMessageDialog(view, "Error en el servicio RMI: " + ex.getMessage(), "Error en el servicio", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    private String getRemoteIP(){
        if(view.getTextRemoteTextIpTextField() != null){
            return view.getTextRemoteTextIpTextField();
        } else {
            System.out.println("No hay texto");
            return null;
        }
    }
    
    private String getRmiBind(){
        if(view.getTextRmiBindIpTextField() != null){
            return view.getTextRmiBindIpTextField();
        } else {
            System.out.println("No hay texto");
            return null;
        }
    }
}
