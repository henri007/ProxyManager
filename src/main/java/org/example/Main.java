package org.example;

import org.example.menu.Menu;
import org.example.proxyManager.CreatingProxy;
import org.example.settings.Settings;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

       Settings.initalizeSettings();

        Menu menu = new Menu();
        menu.mainMenu();



    }
}