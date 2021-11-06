import exceptions.WrongServerException;
import webserver.Controller;
import webserver.Server;

import java.util.ArrayList;

public class ServerMain {

    public static void main(String[] args) throws WrongServerException {

        int port = 8080; // we set the port here
        String websiteFilesPath = "src/main/java/site"; // we set the path to website files
        String status = "Stopped"; // we set the initial state to be stopped

        // Server
        Server server = new Server(port,websiteFilesPath, status);
        ArrayList<String> level1 = new ArrayList<String>();
        ArrayList<String> level2 = new ArrayList<String>();
        ArrayList<String> level3 = new ArrayList<String>();
        server.addPageLevel(level1);
        server.addPageLevel(level2);
        server.addPageLevel(level3);
        server.addPageAtLevel("page1.html",0);
        server.addPageAtLevel("page2.html",0);
        server.addPageAtLevel("page3.html",0);
        server.addPageAtLevel("page4.html",1);
        server.addPageAtLevel("page5.html",2);

        Controller controller = new Controller(server);

        server.setServerStatus("Running");

        while (true) {
            controller.requestHandler();
        }


    }
}
