package org.example.settings;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1

public class Settings {

    private static String pathToVpnConfigFiles;

    private static String vpnUsername;
    private static String vpnPassword;

    public static ArrayList<String> vpnConfigFiles;

    public static void initalizeSettings(){

        initializeSettingsFile();
        initializeArrayWithConfigFiles(pathToVpnConfigFiles);
    }

    private static void initializeSettingsFile(){
        File myFile = new File("settings.txt");
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            SettingsClass settings = objectMapper.readValue(myFile, SettingsClass.class);
            pathToVpnConfigFiles= settings.path_to_Vpn_config_files;
            vpnUsername= settings.vpn_username;
            vpnPassword=settings.vpn_password;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void initializeArrayWithConfigFiles(String pathToFiles){
        File file = new File(pathToFiles);
        for(File oneFile : file.listFiles()){
           if(oneFile.getName().contains("tcp")){
               vpnConfigFiles.add(oneFile.getName());
           }
        }

    }
}
