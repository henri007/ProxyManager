package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.capchaSolver.CapchaSolver;
import org.example.menu.Menu;
import org.example.settings.Settings;

public class Main {
    public static void main(String[] args) {

        //--------->NE DIRATI!!! <-------------
        WebDriverManager.firefoxdriver().setup();
        //--------->NE DIRATI!!! <-------------

        Settings.initalizeSettings();


        Menu menu = new Menu();
        menu.mainMenu();


    }
}