package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;

import java.io.Serializable;

public class NextCloudAccountModel implements Serializable {

    private String url;
    private String username;
    private String password;

    NextcloudConnector nextcloud = new NextcloudConnector(url, username, password);

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NextCloudAccountModel() {
    }

    public void loginToNextcloud(){
        NextcloudConnector nextcloud = new NextcloudConnector(url, username, password);
    }


}