package org.example.proxyManager;

import org.example.logManager.LogManager;
import org.example.logManager.ProxyContainer;
import org.example.logManager.ProxyContainerManager;
import org.example.settings.Settings;

import java.util.HashSet;
import java.util.Random;

public class CreatingProxy {



    //govorimo koliko proxya želimo napraviti
    public void createProxy(int numberOfProxies){
        //Broj proxya ne smije biti veći od 10
        if(numberOfProxies>10){
            numberOfProxies=10;
        }
        //Stvara onoliko portova koliko je proslijeđeno u funkciju (numberOfProxies), počevši od 10000
        int[] ports = generatePorts(numberOfProxies);

        ProxyContainerManager proxyContainerManager = new ProxyContainerManager();



        for(int i=0; i<numberOfProxies;i++){
           // Settings.runCommand(runContainerString(Settings.vpnConfigFiles.get(i),ports[i]));
            String configFile=randomConfigVpnFile();
            String idOfStartedContainer=Settings.runProxyContainerWithResponse(runContainerString(configFile,ports[i]));
            System.out.println(idOfStartedContainer+" : "+configFile);
            proxyContainerManager.addProxyContainer(new ProxyContainer(idOfStartedContainer,ports[i]));
        }

        LogManager.saveToProxyPorts_txt(proxyContainerManager);


    }

    //Dohvaća listu imena config fajlova
    private String randomConfigVpnFile(){

        Random rand = new Random();

            //OD svih učitanih config fajlova, uzima random broj od 1 do numberOfProxies

        return Settings.vpnConfigFiles.get(rand.nextInt(((Settings.numberOfConfigFilesInArray-1) - 0) + 1) + 0);
    }

    private void startProxyContainer(){
        Settings.runCommand("docker run --restart=always --name proxy_10010 -v /home/noname/Downloads/openvpnconfigfiles:/openvpn --device=/dev/net/tun -p 10000:1080 --cap-add=NET_ADMIN -e \"OPENVPN_CONFIG_FILE=/openvpn/ad-leu.prod.surfshark.com_tcp.ovpn\" -e \"OPENVPN_USERNAME=YD4Vw5gKQfvdwa4q2jByWZN7\" -e \"OPENVPN_PASSWORD=6tKPGdcHsqTAjyVNLtK3P6A8\" -d jonoh/openvpn-socks");
    }

    private String runContainerString(String nameOfConfigFile,int port){
        String run;
        run = String.format("docker run --restart always --name proxy_%d " +
                "-v %s:/openvpn " +
                "--device=/dev/net/tun -p %d:1080 --cap-add=NET_ADMIN " +
                "-e \"OPENVPN_CONFIG_FILE=/openvpn/%s\" " +
                "-e \"OPENVPN_USERNAME=%s\" -e \"OPENVPN_PASSWORD=%s\" " +
                "-d jonoh/openvpn-socks",port,Settings.pathToVpnConfigFiles,port,nameOfConfigFile, Settings.vpnUsername,Settings.vpnPassword);

        return run;
    }

    private int[] generatePorts(int howManyPorts){
        int[] ports=new int[howManyPorts];
        for(int i=0;i<howManyPorts;i++){
            ports[i]=10000+i;
        }
        return ports;
    }

}


