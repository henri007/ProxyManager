package org.example.settings;

public class SettingsClass {
    public String path_to_Vpn_config_files;
    public String vpn_username;
    public String vpn_password;

    public String getPath_to_Vpn_config_files() {
        return path_to_Vpn_config_files;
    }

    public void setPath_to_Vpn_config_files(String path_to_Vpn_config_files) {
        this.path_to_Vpn_config_files = path_to_Vpn_config_files;
    }

    public String getVpn_username() {
        return vpn_username;
    }

    public void setVpn_username(String vpn_username) {
        this.vpn_username = vpn_username;
    }

    public String getVpn_password() {
        return vpn_password;
    }

    public void setVpn_password(String vpn_password) {
        this.vpn_password = vpn_password;
    }
}
