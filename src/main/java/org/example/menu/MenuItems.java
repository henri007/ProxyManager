package org.example.menu;

import org.example.proxyManager.CreatingProxy;
import org.example.proxyManager.StopAndDeleteContainers;
import org.example.settings.Settings;

import java.util.Scanner;

public class MenuItems {

    public void menuCreateProxy(){

        CreatingProxy createProxy = new CreatingProxy();
        String choise="test";
        while (!choise.equals("back")){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Za povratak u prethodni izbornik upišite \"back\"");
            System.out.println("Unesite koliko proxy containera želite podići: ");
            choise=keyboard.nextLine();
           createProxy.createProxy(Integer.valueOf(choise));
           choise="back";

        }
    }

    public void menuStopAndDeleteAllContainers(){
        StopAndDeleteContainers stop = new StopAndDeleteContainers();
        stop.stopAndDeleteAllContainers();

        System.out.println("Containeri uspješno zaustavljeni i izbrisani");
        pressAnyKeyToContinue();
    }

    public void displayRuningContainers(){
        Settings.runCommand("sudo docker ps");
        pressAnyKeyToContinue();
    }

    public void displayAllContainers(){
        Settings.runCommand("sudo docker ps -a");
        pressAnyKeyToContinue();
    }

    private void pressAnyKeyToContinue(){
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
