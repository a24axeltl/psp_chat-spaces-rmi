/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainJFrame;

/**
 *
 * @author W10-Portable
 */
public class FrontController {
    private MainJFrame view;

    public FrontController(MainJFrame view) {
        this.view = view;
        this.view.setAccessChatButtonActionListener(this.getAccessChatButtonActionListener());
        this.view.setSendMessageButtonActionListener(this.getSendMessageButtonActionListener());
        this.view.showRegisterLayeredPane();
    }
    
    private ActionListener getAccessChatButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getTextRegisterNameTextField() != null){
                    view.showChatMessengerLayeredPane();
                } else {
                    System.out.println("No hay texto");
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
                    view.addItemToHistorialMessagesList(view.getTextUserMessageTextField());
                } else {
                    System.out.println("No hay texto");
                }
            }
        };
        return al;
    }
}
