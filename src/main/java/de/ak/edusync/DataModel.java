package de.ak.edusync;

import org.aarboard.nextcloud.api.NextcloudConnector;

import java.io.Serializable;

public class DataModel implements Serializable {



    public DataModel() {
    }

    private String nextCloudUrl;
    private String username;
    private String password;
    private String fileUrl;

    //DONT TOUCH GETTERS, IT WILL BREAK THYMELEAF!!!

    public String getNextCloudUrl() {
        return nextCloudUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setNextCloudUrl(String nextCloudUrl) {
        this.nextCloudUrl = nextCloudUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public NextcloudConnector loginToNextcloud(){
        return new NextcloudConnector(nextCloudUrl, username, password);
    }

    public String getFileName() {
        String[] linkParts = fileUrl.split("/");
        return linkParts[linkParts.length-1];
    }



}
