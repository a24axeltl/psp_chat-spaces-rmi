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
        MainJFrame mainView = new MainJFrame();
        FrontController mainController = new FrontController(mainView);
        
        mainView.setVisible(true);
    }
}
