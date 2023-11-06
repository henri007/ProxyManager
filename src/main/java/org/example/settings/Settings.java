package org.example.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Settings {

    public static String pathToVpnConfigFiles;

    public static int numberOfConfigFilesInArray;

    public static String vpnUsername;
    public static String vpnPassword;

    public static ArrayList<String> vpnConfigFiles = new ArrayList<String>();

    public static final String ADDRESS_OF_PROXY_SERVER="127.0.0.1";

    public static final String AZLYRICS_SITE_KEY="6Le3KzMUAAAAAILzez0f7PnFiFboGPKt0qpkINXw";

   public static final String AZLYRICS_LINK="https://b.azlyrics.com/?u=/";

    public static final String CAPCHA_SOLVER_API_KEY="CAP-DD3AF9A21FD4242502CDFA0B5995ADB7";
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

    public static String runProxyContainerWithResponse(String command){
        try{
            ProcessBuilder pb = new ProcessBuilder()
                    .command("bash", "-c", command);
            Process p = pb.start();
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                return s;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "null";
    }

    public static String getRecapchaTokenFromKeyboard(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Input recapcha token");
        // Reading data using readLine
        String token =null;
        try{
            token = reader.readLine();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return token;
    }

    public static void solvedRechapchaToken(){

        String token;

        try{
            Connection.Response response = Jsoup.connect("https://api.capsolver.com/createTask")
                    .method(Connection.Method.POST)
                    .header("Content-Type","application/json")
                    .requestBody("{\n" +
                            "  \"clientKey\":\"" + CAPCHA_SOLVER_API_KEY + "\",\n" +
                            "  \"task\": {\n" +
                            "    \"type\": \"ReCaptchaV2TaskProxyLess\",\n" +
                            "    \"websiteURL\": \"" + AZLYRICS_LINK + "\",\n" +
                            "    \"websiteKey\": \"" + AZLYRICS_SITE_KEY + "\"\n" +
                            "  }\n" +
                            "}")
                    .ignoreContentType(true)
                    .execute();

            System.out.println(response.body());
        }catch (Exception e){
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
