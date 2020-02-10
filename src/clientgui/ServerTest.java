/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;

/**
 *
 * @author andreapallotta
 */
public class ServerTest {
    private Socket socket; 
    private ServerSocket server;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Timestamp timestamp;
    
    public ServerTest(int port) 
    { 
    
    try
         { 
            
    System.out.println("Creating the socket");
             server = new ServerSocket(port); 
             System.out.println("Server started"); 

             System.out.println("Waiting for a client ..."); 

             socket = server.accept(); 
             System.out.println("Client accepted"); 
             }
             
             
             catch(IOException e) {
             e.printStackTrace();
         }
         
            
         try
         { 
             
             ois = new ObjectInputStream(socket.getInputStream());
             oos = new ObjectOutputStream(socket.getOutputStream());
             Object obj = new Object();
             obj = ois.readObject();
             if (obj instanceof ObjectClass.User) {
                 ObjectClass.User user = (ObjectClass.User)obj;
                 String username = user.getUsername();
                 String password = user.getPassword();

                 if (username.equals("USER1") && password.equals("PW1")) {
                     oos.writeObject(true);
                 }

                 else {
                     oos.writeObject(false);
                 }

             }
             
             else if (obj instanceof ObjectClass.Company) {
                  ObjectClass.Company company = (ObjectClass.Company)obj;
                  System.out.println(company.toString());
              }
              
              else if (obj instanceof ObjectClass.Trades) {
                  ObjectClass.Trades trades = (ObjectClass.Trades)obj;
                  System.out.println(trades.toString());
              }
              
              else if (obj instanceof ObjectClass.Securities) {
                  ObjectClass.Securities sec = (ObjectClass.Securities)obj;
                  System.out.println(sec.toString());
              }
             
             
         }

         catch(IOException e) {
             e.printStackTrace();
         }
         
         catch (ClassNotFoundException cnfe) {}

    } 
    
    public static void main(String args[]) 
    { 
        new ServerTest(5000); 
    } 
}  
