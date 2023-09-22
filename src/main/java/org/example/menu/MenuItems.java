package org.example.menu;

import org.example.proxyManager.CreatingProxy;
import org.example.proxyManager.StopAndDeleteContainers;

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
           createProxy.createProxy(5);
           choise="back";

        }
    }

    public void stopAndDeleteAllContainers(){
        StopAndDeleteContainers stop = new StopAndDeleteContainers();
        stop.stopAndDelete();
    }
}
