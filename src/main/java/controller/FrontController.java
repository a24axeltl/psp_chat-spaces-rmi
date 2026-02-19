/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.remotedata.RemoteDataController;
import controller.service.ServiceController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import view.MainJFrame;
import view.remotedata.RemoteDataJDialog;

/**
 *
 * @author W10-Portable
 */
public class FrontController {
    private MainJFrame view;
    private ServiceController serviceController;
    private String userName= null;
    private String IP = null;
    
    public FrontController(MainJFrame view) {
        this.view = view;
        this.view.setAccessChatButtonActionListener(this.getAccessChatButtonActionListener());
        this.view.setSendMessageButtonActionListener(this.getSendMessageButtonActionListener());
        this.serviceController = new ServiceController();
        this.view.showRegisterLayeredPane();
    }
    
    private ActionListener getAccessChatButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getTextRegisterNameTextField() != null){
                    int inputOptionPane = JOptionPane.showConfirmDialog(view, "Quiere iniciar un chat propio?? (Si selecciona 'No', se iniciara el proceso para conectarse a uno?)");
                    if(inputOptionPane == 0){
                        try {
                            serviceController.createRmiRegistry();
                        } catch (RemoteException ex) {
                            JOptionPane.showMessageDialog(view, "Error al crear el registro RMI: " + ex.getMessage(), "Error del mensaje", JOptionPane.ERROR_MESSAGE);  
                            System.exit(0);
                        }
                    } else {
                        RemoteDataJDialog rdJD = new RemoteDataJDialog(view,true);
                        RemoteDataController rdC = new RemoteDataController(rdJD);
                        rdJD.setVisible(true);
                    }
                    view.showChatMessengerLayeredPane();
                    userName = view.getTextRegisterNameTextField();
                    initThreadHearMessages();
                } else {
                    JOptionPane.showMessageDialog(view, "Los campos deben de estar rellanados");
                }
            }
        };
        return al;
    }
    
    private ActionListener getSendMessageButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getTextUserMessageTextField() != null){
                    try {
                        serviceController.sendMessage(userName, view.getTextUserMessageTextField());
                        view.addItemToHistorialMessagesList(userName + ": " + view.getTextUserMessageTextField());
                    } catch (RemoteException ex) {
                        JOptionPane.showMessageDialog(view, "Error al enviar un mensaje: " + ex.getMessage(), "Error del mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return al;
    }
    
    private void initThreadHearMessages(){
        new Thread(() -> hearMessages()).start();
    }
    
    private void hearMessages() {
        try {
            serviceController.connectClient(serviceController.getThisIP());
            System.out.println(serviceController.getThisIP());
            System.out.println("Escuchando mensajes...");
            while (true) {
                try {
                    String remoteMessage = serviceController.getMessage();
                    String[] partsRemoteMessage = remoteMessage.split(":");
                    if(!partsRemoteMessage[0].equals(userName)){
                        view.addItemToHistorialMessagesList(remoteMessage);
                    }
                    serviceController.setMessage(null);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(view, "Error al recibir el mensaje: " + ex.getMessage(), "Error del mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            initThreadHearMessages();
        }
    }
}
