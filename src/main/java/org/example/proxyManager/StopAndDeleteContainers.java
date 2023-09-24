package org.example.proxyManager;

import org.example.logManager.LogManager;
import org.example.settings.Settings;

public class StopAndDeleteContainers {


    public void stopAndDeleteAllContainers(){
        killAllContainers();
        deleteAllStoppedContainers();
        LogManager.deleteAllInProxyPorts_txt();
    }
    private void killAllContainers(){
        Settings.runCommand("sudo docker kill $(sudo docker ps -q)");
    }

    private void deleteAllStoppedContainers(){
        Settings.runCommand("sudo docker container prune -f");
    }
}
