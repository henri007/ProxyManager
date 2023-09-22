package org.example;

import org.example.menu.Menu;
import org.example.settings.Settings;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

       Settings.initalizeSettings();

        Menu menu = new Menu();
        //menu.mainMenu();

        System.out.println(Settings.vpnConfigFiles);
    }
}