import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        String serial_ID = "123";
        ServerSocket listener = new ServerSocket(9090);
        try{
            while(true){
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String request = in.readLine();
                    System.out.println("Client request: " + request);

                    if (request.charAt(1)=='t'){/*
                        SendEmail sender = new SendEmail();
                        sender.send("senzeyuzhang@gmail.com",'t');
                        System.out.println("Sending take email...");*/
                        getEmail mail = new getEmail();
                        String email_address = mail.get(serial_ID);
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        System.out.println("Sending Message to Client...");
                        out.write("5\n");
                        out.flush();
                    }
                    else if(request.charAt(1)=='f'){/*
                        SendEmail sender = new SendEmail();
                        sender.send("senzeyuzhang@gmail.com",'f');
                        System.out.println("Sending take email...");*/
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        System.out.println("Sending Message to Client...");
                        out.write("3\n");
                        out.flush();
                    }
                    else if (request.charAt(1) == 'u') {
                        getDosageInfo dosage = new getDosageInfo();
                        String dosageInfo = dosage.getInfo(serial_ID);
                    }
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

}
