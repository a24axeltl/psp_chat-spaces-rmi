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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ChatData;
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
    private ArrayList<ChatData> chatsList = new ArrayList<>();
    
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
                        //getChatSpace(serviceController.getRemoteName()).addMessageToChat(userName + ": " + view.getTextUserMessageTextField());
                        System.out.println("Mensaje Enviado");
                        view.clearTextUserMessageTextField();
                    } catch (RemoteException ex) {
                        JOptionPane.showMessageDialog(view, "Error al enviar un mensaje: " + ex.getMessage(), "Error del mensaje", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return al;
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
                        //getChatSpace(serviceController.getRemoteName()).addMessageToChat(remoteMessage);
                        System.out.println("Mensaje Enviado");
                    }
                    serviceController.setMessage(null);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(view, "Error al recibir el mensaje: " + ex.getMessage(), "Error del mensaje", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                Thread.sleep(300);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            initThreadHearMessages();
        }
    }
    /*
    private void addChatsToList(ChatData chatData){
        view.addItemToUsersList(chatData.getNameClient());
    }
    
    private ChatData getChatSpace(String name){
        for(ChatData chatData : chatsList){
            if(chatData.getNameClient().equals(name)){
                return chatData;
            }
        }
        return null;
    }
    
    private void createChat(){
        try {
            String remoteName = serviceController.getRemoteName();
            ChatData existingChatData = getChatSpace(remoteName);

            if (existingChatData != null) {
                JOptionPane.showMessageDialog(view, "Se ha intentado crear un chat duplicado", "Error del chat", JOptionPane.ERROR_MESSAGE);
            } else {
                ChatData newChat = new ChatData(remoteName, serviceController.getRemoteService());
                chatsList.add(newChat);
                addChatsToList(newChat);
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(view, "Error al recoger el nombre del cliente: " + ex.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }
    */
    private void initThreadHearMessages(){
        //createChat();
        new Thread(() -> hearMessages()).start();
    }
}
