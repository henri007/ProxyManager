package org.example.menu;

import java.util.Scanner;

public class Menu {



    public void mainMenu(){

        MenuItems menuItems = new MenuItems();
       String choise="test";

        while(!choise.equals("exit")){
            Scanner keyboard = new Scanner(System.in);
            displayMenuItems();
            choise=keyboard.nextLine();


            if(choise.equals("1")){
                menuItems.menuCreateProxy();
            }
            else if(choise.equals("2")){
                System.out.println("Izabrali ste nadzor nad proxyima");
            } else if (choise.equals("3")) {
                menuItems.stopAndDeleteAllContainers();
            }
            else if (choise.equals("exit")){

            }
            else{
                System.out.println("Nepoznat izbor, odaberite ispravni redni broj ");
            }

        }
    }

    private void displayMenuItems(){
        System.out.println("Izaberite radnju: ");
        System.out.println("1. Pokrenite proxy containere");
        System.out.println("2. Pokrenite nazdor nad containerima");
        System.out.println("3. Zaustavite i izbrišite sve containere");
        System.out.println("Za izlazak unesite \"exit\"");
        System.out.print("Odabir: ");
    }
}