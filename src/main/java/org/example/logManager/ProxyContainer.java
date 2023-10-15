package org.example.logManager;

public class ProxyContainer {

    private String id;
    private int port;

    public ProxyContainer(String id, int port){
        this.id=id;
        this.port=port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString(){
        return id+" - "+port;
    }
}
