import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
//jdbc

public class Server {
    public static void main(String[] args) throws IOException {
  //     SendEmail sender = new SendEmail();
//       sender.send("senzeyuzhang@gmail.com");
        JDBC opreation1 = new JDBC();
        opreation1.selectfromDB("1");
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
                    SendEmail sender2 = new SendEmail();
                    if (request.charAt(1)=='t'){
                        sender2.send("senzeyuzhang@gmail.com");
                        System.out.println("Sending take email...");
                    }
                    else if(request.charAt(1)=='f'){
                        sender2.send("senzeyuzhang@gmail.com");
                        System.out.println("Sending fill email...");
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
                    out.write("Hello from Java!\n");
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
