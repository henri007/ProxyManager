package org.example.capchaSolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.logManager.ProxyContainer;
import org.example.proxyCheck.CheckProxies;
import org.example.settings.Settings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

public class CapchaSolver {

    public static ArrayList<Integer> proxiesThatNeedCapchaSolver = new ArrayList<>();


    public void solveCapcha(ProxyContainer proxy){

        CheckProxies checkProxies = new CheckProxies();

        FirefoxDriver driver =    checkProxies.getFirefoxDriver(Settings.ADDRESS_OF_PROXY_SERVER,proxiesThatNeedCapchaSolver.get(0));

        driver.get("https://b.azlyrics.com/?u=/");
    }

    public String getSolvedRecapchaToken(){
                boolean isInProgress = true;
                String taskId=getCapSolverTaskId();
           while (isInProgress){
               isInProgress=isRecapchaInProgress(taskId);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }

           return getToken(taskId);

    }

    private String getToken(String taskId){
        try{
            Connection.Response response = Jsoup.connect("https://api.capsolver.com/getTaskResult")
                    .method(Connection.Method.POST)
                    .header("Content-Type","application/json")
                    .requestBody("{\n" +
                            "  \"clientKey\":\"" + Settings.CAPCHA_SOLVER_API_KEY + "\",\n" +
                            "  \"taskId\":\"" + taskId + "\"\n" +
                            "}")
                    .ignoreContentType(true)
                    .execute();

          return new ObjectMapper().readValue(response.body(), CapSolver.class).solution.gRecaptchaResponse;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    private boolean isRecapchaInProgress(String taskId){
        try{
            Connection.Response response = Jsoup.connect("https://api.capsolver.com/getTaskResult")
                    .method(Connection.Method.POST)
                    .header("Content-Type","application/json")
                    .requestBody("{\n" +
                            "  \"clientKey\":\"" + Settings.CAPCHA_SOLVER_API_KEY + "\",\n" +
                            "  \"taskId\":\"" + taskId + "\"\n" +
                            "}")
                    .ignoreContentType(true)
                    .execute();

            if(response.body().contains("ready")){
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    private String getCapSolverTaskId(){
        try{
            Connection.Response response = Jsoup.connect("https://api.capsolver.com/createTask")
                    .method(Connection.Method.POST)
                    .header("Content-Type","application/json")
                    .requestBody("{\n" +
                            "  \"clientKey\":\"" + Settings.CAPCHA_SOLVER_API_KEY + "\",\n" +
                            "  \"task\": {\n" +
                            "    \"type\": \"ReCaptchaV2TaskProxyLess\",\n" +
                            "    \"websiteURL\": \"" + Settings.AZLYRICS_LINK + "\",\n" +
                            "    \"websiteKey\": \"" + Settings.AZLYRICS_SITE_KEY + "\"\n" +
                            "  }\n" +
                            "}")
                    .ignoreContentType(true)
                    .execute();

            return new ObjectMapper().readValue(response.body(), CapSolver.class).taskId;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    return null;
    }
}
