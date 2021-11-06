package webserver;

import exceptions.WrongPortException;
import exceptions.WrongServerException;
import exceptions.WrongStatusException;
import exceptions.WrongWebsitePathException;

import java.util.ArrayList;

public class Server {

    private int port;
    private String websiteFilesPath;
    private String serverStatus;
    private String request;
    public ArrayList<ArrayList<String>> pagesList;

    public Server(){

    }

    public Server(int port, String websiteFilesPath, String serverStatus) throws WrongServerException {
        validateWebServerInputs(port,websiteFilesPath,serverStatus);
        this.port = port;
        this.websiteFilesPath = websiteFilesPath;
        this.serverStatus = serverStatus;
        this.request = "";
        pagesList = new ArrayList<ArrayList<String>>();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getWebsiteFilesPath() { return websiteFilesPath; }

    public void setWebsiteFilesPath(String websiteFilesPath) { this.websiteFilesPath = websiteFilesPath; }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void validateWebServerInputs(int port, String websiteFilesPath, String serverStatus) throws WrongServerException {

        if(port != 8080){
            throw new WrongPortException();
        }

        if(!serverStatus.equals("Stopped")){
            throw new WrongStatusException();
        }

        if(!websiteFilesPath.equals("src/main/java/site")){
            throw new WrongWebsitePathException();
        }

    }

    public void addPageLevel(ArrayList<String> newLevel){
        pagesList.add(newLevel);
    }
    public void addPageAtLevel(String newPage,int level){
        pagesList.get(level).add(newPage);
    }
}
