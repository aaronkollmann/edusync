package de.ak.edusync;

import java.io.Serializable;

public class DiscordAccountModel implements Serializable {
    public DiscordAccountModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

}
