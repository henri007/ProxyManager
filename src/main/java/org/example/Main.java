package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.menu.Menu;
import org.example.proxyManager.CreatingProxy;
import org.example.settings.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

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