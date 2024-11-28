/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.DictionaryGUI;
import java.io.IOException;

/**
 *
 * @author dangt
 */
public class main {
    public static void main(String[] args) {
        DictionaryGUI gui = new DictionaryGUI();
        gui.setVisible(true);

        try {
            gui.manager.loadDictionary("dictionary.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
