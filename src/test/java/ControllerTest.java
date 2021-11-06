import exceptions.WrongServerException;
import org.junit.Test;
import webserver.Controller;
import webserver.Server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private int port8080 = 8080;
    private String websiteFilePath = "src/main/java/site";
    private ArrayList<String> status = new ArrayList<String>(Arrays.asList("Stopped","Running","Maintenance"));


    @Test
    public void TestForServerSocketOk() throws WrongServerException {

        Server server = new Server(port8080,websiteFilePath,status.get(0));
        server.setServerStatus(status.get(1));

        try {
            ServerSocket socket = Controller.newServerSocket(port8080);

            assertTrue(socket.isBound());

            socket.close();
        }catch(BindException e) {
            Assertions.fail(e);
        }catch(IOException b) {
            Assertions.fail(b);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestForNewServerSocketPortNotOk() throws WrongServerException, BindException {
        Server server = new Server(port8080, websiteFilePath, status.get(0));
        server.setServerStatus(status.get(1));


        ServerSocket socket = Controller.newServerSocket(65536); // the port must be between 0 and 65535

    }


    @Test(expected = NullPointerException.class)
    public void TestForCloseServerSocketNotOk(){

        Controller.closeServerSocket(null);

    }


    @Test
    public void TestForCloseServerSocketOk(){

        try{
            ServerSocket serverSocket = Controller.newServerSocket(port8080);
            Controller.closeServerSocket(serverSocket);
            assertTrue(serverSocket.isClosed());
        }catch(BindException e){
            Assertions.fail(e);
        }

    }



    @Test(expected = NullPointerException.class)
    public void TestForCloseClientSocketNotOk(){

        Controller.closeClientSocket(null);

    }

    @Test
    public void TestForCloseClientSocketOk() throws BindException {

        try{

            ServerSocket serverSocket = Controller.newServerSocket(port8080);
            Socket clientSocket = Controller.newClientSocket(serverSocket);

            Controller.closeClientSocket(clientSocket);
            assertTrue(clientSocket.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void TestForAcceptSocketOk() {
        try {
            ServerSocket serverSocket = Controller.newServerSocket(port8080);
            Socket clientSocket = Controller.acceptSocket(serverSocket);

            assertTrue(clientSocket.isBound());
            assertTrue(serverSocket.isBound());

            serverSocket.close();
            clientSocket.close();
        }catch(BindException e) {
            Assertions.fail(e);
        }catch(IOException e) {
            Assertions.fail(e);
        }catch(Exception e) {
            Assertions.fail(e);
        }
    }

    @Test(expected = SocketException.class)
    public void TestForAcceptSocketNotOk() throws Exception {
        ServerSocket serverSocket = Controller.newServerSocket(port8080);
        Controller.closeServerSocket(serverSocket);
        Socket clientSocket = Controller.acceptSocket(serverSocket);
    }


    @Test
    public void TestForClientHandlerOk() throws WrongServerException, IOException {
        Server server = new Server(port8080,websiteFilePath,status.get(0));
        Controller controller = new Controller(server);

        ServerSocket serverSocket = new ServerSocket(port8080);

        Socket clientSocket = serverSocket.accept();

        controller.clientHandler(clientSocket);

    }

    @Test
    public void TestForlientHandlerNotOk() throws WrongServerException {
        Server server = new Server(port8080,websiteFilePath,status.get(0));
        Controller controller = new Controller(server);

        controller.clientHandler(null);
    }

    @Test
    public void TestForRequestHandlerServerStopped() throws WrongServerException {

        Server firstServer = new Server(port8080,websiteFilePath,status.get(0));

        Controller firstController = new Controller(firstServer);

        firstController.requestHandler();


    }

    @Test
    public void TestForRequestHandlerServerRunning() throws WrongServerException {

        Server webserver = new Server(port8080,websiteFilePath,status.get(0));
        webserver.setServerStatus(status.get(1));

        Controller firstController = new Controller(webserver);

        firstController.requestHandler();


    }

    @Test
    public void TestForRequestHandlerServerInMaintenance() throws WrongServerException {

        Server webserver = new Server(port8080,websiteFilePath,status.get(0));
        webserver.setServerStatus(status.get(2));

        Controller firstController = new Controller(webserver);

        firstController.requestHandler();

    }



}