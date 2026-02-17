/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.remotedata.RemoteDataController;
import controller.service.ServiceController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import model.Client;
import view.MainJFrame;
import view.remotedata.RemoteDataJDialog;

/**
 *
 * @author W10-Portable
 */
public class FrontController {
    private MainJFrame view;
    private ServiceController serviceController;
    
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
                        RemoteDataJDialog rdJD = new RemoteDataJDialog(view,true);
                        RemoteDataController rdC = new RemoteDataController(rdJD,false);
                        rdJD.setVisible(true);
                    } else {
                        RemoteDataJDialog rdJD = new RemoteDataJDialog(view,true);
                        RemoteDataController rdC = new RemoteDataController(rdJD,true);
                        rdJD.setVisible(true);
                    }
                    view.showChatMessengerLayeredPane();
                    new Thread(() -> hearMessages()).start();
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
                    System.out.println("Mensaje de Host");
                } else {
                    System.out.println("No hay texto");
                }
            }
        };
        return al;
    }
    
    public void hearMessages() {
        try {
            serviceController.connectClient("MessengerService", "127.0.0.1");
            while (true) {
                System.out.println("Escuchando mensajes...");
                try {
                    view.addItemToHistorialMessagesList(serviceController.getMessage());
                    serviceController.setMessage(null);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(view, "Error al recibir el mensaje: " + ex.getMessage(), "Error mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            try {
                System.err.println(ex.getMessage());
                Thread.sleep(3000);
                hearMessages();
            } catch (InterruptedException ex1) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
