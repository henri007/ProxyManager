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
        Settings.runCommand("docker kill $(docker ps -q)");
    }

    private void deleteAllStoppedContainers(){
        Settings.runCommand("docker container prune -f");
    }
}
