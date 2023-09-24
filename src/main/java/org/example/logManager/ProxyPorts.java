package org.example.logManager;

import org.example.logManager.ProxyContainer;

import java.util.ArrayList;

public class ProxyPorts {

    private ArrayList<ProxyContainer> proxyContainers;

    public ProxyPorts(){
        proxyContainers = new ArrayList<ProxyContainer>();
    }

    public void addProxyContainer(ProxyContainer proxyContainer){
        proxyContainers.add(proxyContainer);
    }
    public ArrayList<ProxyContainer> getProxyPorts(){
        return proxyContainers;
    }
}
