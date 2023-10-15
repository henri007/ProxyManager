package org.example.logManager;

import java.util.ArrayList;

public class ProxyContainerManager {

    public static ArrayList<ProxyContainer> proxyContainers;

    public ProxyContainerManager(){
        proxyContainers = new ArrayList<ProxyContainer>();
    }

    public void addProxyContainer(ProxyContainer proxyContainer){
        proxyContainers.add(proxyContainer);
    }
    public ArrayList<ProxyContainer> getProxyPorts(){
        return proxyContainers;
    }
}
