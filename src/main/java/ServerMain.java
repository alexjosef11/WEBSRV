import exceptions.WrongServerException;
import webserver.Controller;
import webserver.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerMain implements ActionListener {

    private static Server server;
    int port = 8080; // we set the port here
    String websiteFilesPath = "src/main/java/site"; // we set the path to website files
    String status = "Stopped"; // we set the initial state to be stopped
    private static Controller controller;
    JFrame frame = new JFrame();
    JButton start_button;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    public ServerMain(){


          start_button = new JButton("Star Server");
          start_button.addActionListener(this);


         label1 = new JLabel("Server port : 8080");
         label2 = new JLabel("Server creator : Gurgus Alexandru-Josef ");
         label3 = new JLabel("Server ip host : 192.168.0.142");

         JPanel panel = new JPanel();
         panel.setBorder(BorderFactory.createEmptyBorder(150,150,50,150));
         panel.setLayout(new GridLayout(0,1));

        panel.add(start_button);
         panel.add(label1);
         panel.add(label2);
         panel.add(label3);
       // panel.add(label4);


         frame.add(panel, BorderLayout.CENTER);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setTitle("Web Server GUI");
         frame.pack();
         frame.setVisible(true);

    }
    public static void main(String[] args) throws WrongServerException {
        new ServerMain();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            server = new Server(port,websiteFilesPath, status);
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

             controller = new Controller(server);

            server.setServerStatus("Running");

            while (true) {
                controller.requestHandler();
            }
        } catch (WrongServerException ex) {
            ex.printStackTrace();
        }
    }
}
