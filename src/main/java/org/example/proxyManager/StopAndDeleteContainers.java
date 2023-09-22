package org.example.proxyManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StopAndDeleteContainers {


    public void stopAndDelete(){
        try{
           // Process p = Runtime.getRuntime().exec("sudo docker stop $(docker ps -a -q)");
            Process p = Runtime.getRuntime().exec("sudo docker ps -a");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
