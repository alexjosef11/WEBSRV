import exceptions.WrongPortException;
import exceptions.WrongServerException;
import exceptions.WrongStatusException;
import exceptions.WrongWebsitePathException;
import org.junit.BeforeClass;
import org.junit.Test;
import webserver.Server;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServerTest {

    static Server server;

    @BeforeClass
    public static void before() throws WrongServerException {
        try{
            server = new Server(8080,"src/main/java/site","Stopped");
        }catch(WrongServerException e){
            System.out.println("Wrong Server Inputs");
        }
    }

    @Test
    public void CheckForNullWebServer(){
        assertNotNull(server);
    }

    @Test
    public void TestForPorOk() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Stopped");
    }

    @Test(expected=WrongPortException.class)
    public void TestForPortNotOk() throws WrongServerException {
        server = new Server(8081,"src/main/java/site","Stopped");
    }

    @Test
    public void TestForSitePathOk() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Stopped");
    }

    @Test(expected=WrongWebsitePathException.class)
    public void TestForSitePathNotOk() throws WrongServerException {
        server = new Server(8080,"src/main/java/erver","Stopped");
    }

    @Test
    public void TestForStatusOk() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Stopped");
    }

    @Test(expected=WrongStatusException.class)
    public void TestforStatusNotOk() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Running");
    }

    @Test
    public void TestForLevelOK() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Stopped");
        assertEquals(0, server.pagesList.size());

        ArrayList<String> testLevel = new ArrayList<String>();
        server.addPageLevel(testLevel);
        assertEquals(1, server.pagesList.size());
    }

    @Test
    public void TestForPagelOK() throws WrongServerException {
        server = new Server(8080,"src/main/java/site","Stopped");
        ArrayList<String> testLevel = new ArrayList<String>();
        server.addPageLevel(testLevel);
        assertEquals(0, server.pagesList.get(0).size());
        server.addPageAtLevel("testPage",0);
        assertEquals(1, server.pagesList.get(0).size());
    }


    @Test
    public void TestForServerStatus(){
        server = new Server();
        server.setServerStatus("test server status");
        assertEquals("test server status", server.getServerStatus());
    }


    @Test
    public void TestForWebsiteFilesPath(){
        server = new Server();
        server.setWebsiteFilesPath("test website path");
        assertEquals("test website path", server.getWebsiteFilesPath());
    }

    @Test
    public void TestForRequest(){
        server = new Server();
        server.setRequest("test request");
        assertEquals("test request", server.getRequest());
    }



}
