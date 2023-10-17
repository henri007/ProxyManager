package org.example.proxyCheck;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.example.logManager.ProxyContainer;
import org.example.logManager.ProxyContainerManager;
import org.example.settings.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.*;

import java.io.File;
import java.nio.file.Path;

public class CheckProxies {



    public void testStart(){



        for(ProxyContainer proxyContainer : ProxyContainerManager.proxyContainers){
            FirefoxDriver driver = getFirefoxDriver(Settings.ADDRESS_OF_PROXY_SERVER,proxyContainer.getPort());
            Cookie azlyricsCookie= driver.manage().getCookieNamed("_GRECAPTCHA");
            System.out.println(azlyricsCookie.toString());
            if(isProxyWorkingOnAZLyrics(driver)){
                System.out.println(proxyContainer.getPort()+" radi");
            }else{
                System.out.println(proxyContainer.getPort()+" ne radi");
            }
        }
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

    private FirefoxDriver getFirefoxDriver(String proxyAddress, int proxyPort){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1); // Manual proxy configuration
        profile.setPreference("network.proxy.socks", proxyAddress);
        profile.setPreference("network.proxy.socks_port", proxyPort);

        profile.addExtension(new File("foxyproxy.xpi"));
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        //Postavlja FIrefox u headless mode
        if(false){
            options.addArguments("--headless");
        }
        options.setProfile(profile);

        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogFile(new File("geckologTEST.txt")).withLogLevel(FirefoxDriverLogLevel.TRACE)
                .build();

        FirefoxDriver firefoxDriver = new FirefoxDriver(service,options);
        return firefoxDriver;

    }



}
