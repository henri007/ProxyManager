package org.example.logManager;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LogManager {

    private static String pathToProxyPorts_txt="ProxyPorts.txt";

    public static void saveToProxyPorts(ArrayList<String> proxyPorts){

        try{
            FileWriter fileWriter = new FileWriter(pathToProxyPorts_txt);
            fileWriter.write(proxyPorts.toString());
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void saveToProxyPorts_txt(String whatToSave){
        try{
            FileWriter fileWriter = new FileWriter(pathToProxyPorts_txt);
            fileWriter.write(whatToSave);
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    }
    public static void deleteAllInProxyPorts_txt(){
        try{
            new PrintWriter(pathToProxyPorts_txt).close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
