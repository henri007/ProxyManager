package org.example.proxyManager;

import org.example.settings.Settings;

public class StopAndDeleteContainers {


    public void stopAndDeleteAllContainers(){
        killAllContainers();
        deleteAllStoppedContainers();
    }
    private void killAllContainers(){
        Settings.runCommand("sudo docker kill $(sudo docker ps -q)");
    }

    private void deleteAllStoppedContainers(){
        Settings.runCommand("sudo docker container prune -f");
    }
}
