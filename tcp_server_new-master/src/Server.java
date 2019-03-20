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
        //send email test
        SendEmail sender = new SendEmail();
        //sender.send("001","01n3n5nn",'t');
        System.out.println("Sending take email...");
        //dosage info test
        getDosageInfo dosagetest = new getDosageInfo();
        String dosageInfotest = dosagetest.getInfo("001");
        System.out.println(dosageInfotest);

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
                    if (request.equals("update")) {
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        getDosageInfo dosage = new getDosageInfo();
                        String dosageInfo = dosage.getInfo("001");
                        String prevInfo = "\n";
                        System.out.println(dosageInfo);
                        if(prevInfo.equals(dosageInfo)){//avoid sending duplicate info to increase the efficiency
                            out.write("n");
                            out.flush();
                        }else{
                            out.write("p0t1517n1t1518n2t1519n3p1t1505n4t1506n5t2210n5p2t1508n6t1505n6\n");
                            out.flush();
                            prevInfo = dosageInfo;
                        }
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
                    else if (request.length() >= 10) {
                        if (request.charAt(10) == 't' || request.charAt(10) == 'f'){/*
                        SendEmail sender = new SendEmail();
                        sender.send("senzeyuzhang@gmail.com",'t');
                        System.out.println("Sending take email...");*/
                            getEmail mail = new getEmail();
                            serial_ID = request.substring(11, 14);
                            System.out.println("serial ID is: " + serial_ID);
                            String seg_IDs = request.substring(0, 10);
                            System.out.println("Segmets require operations are:" + seg_IDs);
                            String email_address = mail.get(serial_ID);

                            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            System.out.println("Sending Message to Client...");
                            out.write("5\n");
                            out.flush();
                        }
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
