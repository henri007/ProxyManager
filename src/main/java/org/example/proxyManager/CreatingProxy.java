package org.example.proxyManager;

import org.example.settings.Settings;

import java.util.HashSet;
import java.util.Random;


public class CreatingProxy {



    //govorimo koliko proxya želimo napraviti
    public void createProxy(int numberOfProxies){
        int[] ports = generatePorts(numberOfProxies);

        for(int i=0; i<numberOfProxies;i++){
            System.out.println(runContainerString(Settings.vpnConfigFiles.get(i),ports[i]));
            System.out.println("-----------------------------------");
            Settings.runCommand(runContainerString(Settings.vpnConfigFiles.get(i),ports[i]));
        }



    }

    //Dohvaća listu imena config fajlova
    private HashSet<String> randomConfigVpnFile(int numberOfProxies){
        HashSet<String> proxyConfigFiles=new HashSet<String>();
        Random rand = new Random();
        while (proxyConfigFiles.size()<numberOfProxies){
            //OD svih učitanih config fajlova, uzima random broj od 1 do numberOfProxies
          proxyConfigFiles.add(Settings.vpnConfigFiles.get(rand.nextInt(((Settings.numberOfConfigFilesInArray-1) - 0) + 1) + 0));

        }
        return proxyConfigFiles;
    }

    private void startProxyContainer(){
        Settings.runCommand("sudo docker run --name proxy_10010 -v /home/noname/Downloads/openvpnconfigfiles:/openvpn --device=/dev/net/tun -p 10000:1080 --cap-add=NET_ADMIN -e \"OPENVPN_CONFIG_FILE=/openvpn/ad-leu.prod.surfshark.com_tcp.ovpn\" -e \"OPENVPN_USERNAME=YD4Vw5gKQfvdwa4q2jByWZN7\" -e \"OPENVPN_PASSWORD=6tKPGdcHsqTAjyVNLtK3P6A8\" -d jonoh/openvpn-socks");
    }

    private String runContainerString(String nameOfConfigFile,int port){
        String run;
        run = String.format("sudo docker run --name proxy_%d " +
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


