package org.example.menu;

import org.example.logManager.ProxyContainer;
import org.example.logManager.ProxyContainerManager;
import org.example.proxyCheck.CheckProxies;
import org.example.proxyManager.CreatingProxy;
import org.example.proxyManager.MonitorDockers;
import org.example.proxyManager.StopAndDeleteContainers;
import org.example.settings.Settings;

import java.util.Scanner;

public class MenuItems {

    //Menu Izbor broj 1
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

    //Menu Izbor broj 2
    public void checkRunningProxy(){
        CheckProxies checkProxies = new CheckProxies();
        checkProxies.testStart1();
    }
    //Menu izbor broj 3
    public void menuStopAndDeleteAllContainers(){
        StopAndDeleteContainers stop = new StopAndDeleteContainers();
        stop.stopAndDeleteAllContainers();

        System.out.println("Containeri uspješno zaustavljeni i izbrisani");
        pressAnyKeyToContinue();
    }

    //Menu izbor broj 4
    public void displayRuningContainers(){
        Settings.runCommand("docker ps");
        pressAnyKeyToContinue();
    }

    //Menu izbor broj 5
    public void displayAllContainers(){
        Settings.runCommand("docker ps -a");
        pressAnyKeyToContinue();
    }

    //Meni izbor broj 6
    public void printAllContainers(){
        for(ProxyContainer proxyContainer : ProxyContainerManager.proxyContainers){
            System.out.println(proxyContainer.getPort());
        }
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
