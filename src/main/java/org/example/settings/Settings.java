package org.example.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1

public class Settings {

    public static String pathToVpnConfigFiles;

    public static int numberOfConfigFilesInArray;

    public static String vpnUsername;
    public static String vpnPassword;

    public static ArrayList<String> vpnConfigFiles = new ArrayList<String>();

    public static void initalizeSettings(){

        initializeSettingsFile();
        initializeArrayWithConfigFiles(pathToVpnConfigFiles);
    }

    public static void runCommand(String command){
        try{
            ProcessBuilder pb = new ProcessBuilder()
                    .command("bash", "-c", command);

            Process p = pb.start();
            p.waitFor();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
        numberOfConfigFilesInArray=vpnConfigFiles.size();

    }
}
