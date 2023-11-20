package org.example.proxyCheck;

import org.example.capchaSolver.CapchaSolver;
import org.example.logManager.ProxyContainer;
import org.example.logManager.ProxyContainerManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Random;

public class CheckProxies {

    private String userAgents[] = {
            "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36,gzip(gfe)",
            "Mozilla/5.0 (Linux; Android 13; SM-S901B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 13; SM-S908B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 13; Pixel 6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 12; moto g power (2022)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (Linux; Android 13; M2101K6G) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Mobile Safari/537.36",
            "Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/19E241 Safari/602.1",
            "Mozilla/5.0 (iPhone14,3; U; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/19A346 Safari/602.1",
            "Mozilla/5.0 (iPhone13,2; U; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/15E148 Safari/602.1",
            "Mozilla/5.0 (iPhone12,1; U; CPU iPhone OS 13_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/15E148 Safari/602.1",
            "Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; RM-1152) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.15254",
            "Mozilla/5.0 (Linux; Android 12; SM-X906C Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/80.0.3987.119 Mobile Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",
            "Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36",
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1",
            "Mozilla/5.0 (PlayStation; PlayStation 5/2.26) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0 Safari/605.1.15",
            "Mozilla/5.0 (PlayStation 4 3.11) AppleWebKit/537.73 (KHTML, like Gecko)"
    };




    public void testStart(){

        Document document;
        for(ProxyContainer proxyContainer : ProxyContainerManager.proxyContainers){
            Proxy proxy = new Proxy(Proxy.Type.SOCKS,
                    new InetSocketAddress("127.0.0.1", proxyContainer.getPort()));
            String userAgent = getRandomUserAgent();
            try{
                document = Jsoup.connect("https://www.azlyrics.com/").userAgent(userAgent).proxy(proxy).get();
                //System.out.println(document.body());

                if(document.body().text().contains("Our systems have detected unusual activity from your IP address (computer network).")){
                    CapchaSolver.proxiesThatNeedCapchaSolver.add(proxyContainer.getPort());
                    System.out.println(proxyContainer.getPort()+" ne radi");

                        CapchaSolver capchaSolver = new CapchaSolver();

//TEST
                    String url = "https://b.azlyrics.com/?u=/"; // Replace with the URL of the website you want to connect to
                    Connection.Response response = Jsoup.connect(url).userAgent(userAgent).proxy(proxy)
                            .method(Connection.Method.POST).data("g-recaptcha-response",capchaSolver.getSolvedRecapchaToken())
                            .execute();
                    System.out.println("Recapcha za "+proxyContainer.getPort()+" uspješno rješena");




   //TEST
                }else{
                    System.out.println(proxyContainer.getPort()+" radi");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }

    public String getRandomUserAgent(){
        Random random = new Random();
        return  userAgents[random.nextInt(userAgents.length)];
    }

    private boolean isProxyWorkingOnAZLyrics(FirefoxDriver driver){

        try{
            driver.get("https://www.azlyrics.com/");
            driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/h1"));
            driver.close();
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public FirefoxDriver getFirefoxDriver(String proxyAddress, int proxyPort){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1); // Manual proxy configuration
        profile.setPreference("network.proxy.socks", proxyAddress);
        profile.setPreference("network.proxy.socks_port", proxyPort);


        FirefoxOptions options = new FirefoxOptions();
      //  options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addPreference("network.cookie.cookieBehavior", 0); // 0: Accept all cookies

        //Postavlja FIrefox u headless mode
        if(false){
            options.addArguments("--headless");
        }
        options.setProfile(profile);

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogFile(new File("geckolog.txt")).withLogLevel(FirefoxDriverLogLevel.TRACE)
                .build();

        FirefoxDriver firefoxDriver = new FirefoxDriver(service,options);
        return firefoxDriver;

    }



}
