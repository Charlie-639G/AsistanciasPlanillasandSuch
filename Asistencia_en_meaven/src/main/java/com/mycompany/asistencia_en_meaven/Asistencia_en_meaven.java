/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.asistencia_en_meaven;

import vista.login;
import com.mycompany.asistencia_en_meaven.Vista_de_usuario.usuario; 

/**
 *
 * @author Jared
 */
public class Asistencia_en_meaven {

    public static void main(String[] args) {
        try {
        System.out.println("Hello World!");
        java.awt.EventQueue.invokeLater(() -> new usuario().setVisible(true)); 
            
        } catch (Exception e) {
            System.out.println("Somehow fucked up");
        e.printStackTrace();
        }
//java.awt.EventQueue.invokeLater(() -> new login().setVisible(true));
    }
}
