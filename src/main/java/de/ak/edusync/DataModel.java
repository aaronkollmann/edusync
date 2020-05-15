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
    private String remoteFolderPath;
    private int syncInterval;

    public NextcloudConnector loginToNextcloud(){
        return new NextcloudConnector(nextCloudUrl, username, password);
    }


    public String getNextCloudUrl() {
        return nextCloudUrl;
    }

    public void setNextCloudUrl(String nextCloudUrl) {
        this.nextCloudUrl = nextCloudUrl;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getRemoteFolderPath() {
        return remoteFolderPath;
    }

    public void setRemoteFolderPath(String remoteFolderPath) {
        this.remoteFolderPath = remoteFolderPath;
    }

    public int getSyncInterval() {
        return syncInterval;
    }

    public void setSyncInterval(int syncInterval) {
        this.syncInterval = syncInterval;
    }





}
