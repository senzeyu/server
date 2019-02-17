package com.ece477.project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TCP_server {
    static public String request;
    public void tcp_server( ) throws IOException {
        System.out.println("tcp server");
        ServerSocket listener = new ServerSocket(9090);
        try{
            while(true){
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    request = in.readLine();
                    System.out.println("Client request: " + request);

                    if (request.equals("send email")){
                        SendEmail sender2 = new SendEmail();
                        System.out.println("Sending email...");
                        sender2.send("safetpill477@gmail.com");
                        System.out.println("Done");
                        //send_email;
                    }
                    /*
                    else request information {
                        send dosage information select from mysql;
                    }
                    else{
                        do nothing;
                    }
                     */
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    System.out.println("Sending Message...");
                    out.write("Done\n");
                    out.flush();
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

}

