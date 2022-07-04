package com.robot.asus.ZenboHelloWorld;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.ServerSocket; 
import java.net.Socket;

public class HTTPServer {
    public static String state;

    public static void Get() throws IOException { 
        ServerSocket server = new ServerSocket(3000); 
        System.out.println("Listening for connection on port 3000 ...."); 
        while (true) { 
            Socket clientSocket = server.accept(); 
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream()); 
            BufferedReader reader = new BufferedReader(isr); 
            String line = reader.readLine(); 
            while (!line.isEmpty()) { 
                System.out.println(line); 
                line = reader.readLine(); 
                state = line;
            } 
        }
    }
}
