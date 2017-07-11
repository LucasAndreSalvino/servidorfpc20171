/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpcserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Documentos
 */
public class FPCServer {
//O cliente é o proprio brower no link
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
       try {
             serverSocket = new ServerSocket(55555); 
           } catch (IOException e) 
           {
             System.err.println("Could not listen on port: 55555.");
             System.exit(1);
       }

       Socket clientSocket = null; 
       try {
            clientSocket = serverSocket.accept();

            if(clientSocket != null)                
                System.out.println("Connected");
                System.out.println("200 OK!");
                System.out.println("<!DOCTYPE html>");
                System.out.println("<html>");
                System.out.println("<body>");

                System.out.println("<h1>header</h1>");
                System.out.println("<p>fpc server</p>");

                System.out.println("</body>");
                System.out.println("</html>");
       } catch (IOException e) {
             System.err.println("Accept failed.");
             System.exit(1);
      }

     PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(FPCServer.class.getName()).log(Level.SEVERE, null, ex);
        }

//essa parte daki imprime na saida do broser
//acessa em localhost:55555 - no firefox
    out.println("HTTP/1.1 200 OK");
    out.println("Content-Type: text/html");
    out.println("\r\n");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>servidor fpc </title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>header</h1>");
    out.println("<p>fpc server</p>");

    out.println("</body>");
    out.println("</html>");
    out.flush();

    out.close();

        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(FPCServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(FPCServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
