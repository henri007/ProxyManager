package org.example.logManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
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


    public static void saveToProxyPorts_txt(ProxyContainerManager proxyContainerManager){
        ObjectMapper objectMapper = new ObjectMapper();


        try{
            objectMapper.writeValue(new File(pathToProxyPorts_txt), proxyContainerManager);
            System.out.println("Uspješno zapisano u "+pathToProxyPorts_txt);
        }catch (Exception e){
            System.out.println(e.getMessage());
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
