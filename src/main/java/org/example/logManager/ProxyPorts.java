package org.example.logManager;

import java.util.ArrayList;

public class ProxyPorts {

    private ArrayList<ProxyContainer> proxyContainers;

    public ProxyPorts(){
        proxyContainers = new ArrayList<>();
    }

    public void addProxyContainer(ProxyContainer proxyContainer){
        proxyContainers.add(proxyContainer);
    }
    public ArrayList<ProxyContainer> getProxyPorts(){
        return proxyContainers;
    }
}
