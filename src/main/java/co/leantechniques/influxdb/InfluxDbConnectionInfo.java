package co.leantechniques.influxdb;

import java.net.URI;

public class InfluxDbConnectionInfo {
    private URI url;
    private String database;
    private String username;
    private String password;

    public void setUrl(URI url) {
        this.url = url;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getConnectionUrl() {
        return url.toString();
    }

    public String getDatabase() {
        return database;
    }
}
