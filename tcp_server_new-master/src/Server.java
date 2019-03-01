import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.time.*;

public class Server {
    public static void main(String[] args) throws IOException {
/*
        LocalDateTime time = LocalDateTime.now();
        String hour = new DecimalFormat("00").format(time.getHour());
        String min = new DecimalFormat("00").format(time.getMinute());
        String sec = new DecimalFormat("00").format(time.getSecond());
        System.out.println(hour+min+sec);
*/
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
                    else if (request.charAt(0) == 'u') {
                      //  getDosageInfo dosage = new getDosageInfo();
                      //  String dosageInfo = dosage.getInfo(serial_ID);
                        System.out.println("Sending dosage info...");
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        out.write("p0t1458n2t1120n3t2045n2p1t1459n1t1510n1t2210n5p2t1508n6t1505n6\n");
                        out.flush();
                        System.out.println("Sent");
                    }
                    else if(request.equals("time")){
                        System.out.println("performed\n");
                        LocalDateTime time = LocalDateTime.now();
                        String hour = new DecimalFormat("00").format(time.getHour());
                        String min = new DecimalFormat("00").format(time.getMinute());
                        String sec = new DecimalFormat("00").format(time.getSecond());
                        String time_message = hour+min+sec+'\n';
                        System.out.println(time_message);

                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        out.write(time_message);
                        out.flush();
                    }
                    else{

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
