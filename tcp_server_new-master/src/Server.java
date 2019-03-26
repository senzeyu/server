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

        //send email test
        SendEmail sender = new SendEmail();
        //sender.send("001","01n3n5nn",'t');
        System.out.println("Sending take email...");
        //dosage info test
        getDosageInfo dosagetest = new getDosageInfo();
        String dosageInfotest = dosagetest.getInfo("001");
        System.out.println(dosageInfotest);
*/
        String serial_ID = "123";
        String dose = "";
        String prevrequest = "";
        long current = System.currentTimeMillis();
        long prev = 0;

        ServerSocket listener = new ServerSocket(9090);
        try{
            String prevInfo = "\n";
            while(true){
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String request = in.readLine();
                    System.out.println("Client request: " + request);

                    if(request.equals("time")){
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
                    else if(request.length() > 6 && request.substring(0,6).equals("length")){
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        getDosageInfo dosage = new getDosageInfo();
                        //dose = dosage.getInfo(request.substring(request.length() - 3, request.length()))+"\n";
                        dose = "p0t1111n1t1010n1t1212n1p1t2020n2t2121n2p2t3030n3p3t1131n4t4545n5p4t5555n5t5656n6p5t6666n6t6363n6p6t7777n7t7979n7p7t8888n8t8080n8p8t9999n9t9494n9p9t0100n9t1808n9\n";
                        String length = new DecimalFormat("000").format(dose.length())+"\n";
                        System.out.println(length);
                        out.write(length);
                        out.flush();
                    }
                    else if((request.length() > 6 && request.substring(0,6).equals("update")) ||
                            (request.length() > 2 && request.substring(0,2).equals("fu"))) {//force update
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        getDosageInfo dosage = new getDosageInfo();
                        dose = dosage.getInfo(request.substring(request.length() - 3, request.length()))+"\n";
                        dose = "p0t1111n1t1010n1t1137n5p1t1135n2t2121n2p2t3030n3p3t1134n4t4545n5p4t5555n5t5656n6p5t1138n6t6363n6p6t7777n7t7979n7p7t8888n8t8080n8p8t9999n9t9494n9p9t1155n9t1147n9\n";
                        //String length = new DecimalFormat("000").format(dose.length());
                        //String dosageInfo = length + dose;
                        //String dosageInfo = "p1t1111n1t1010n1t1212n1p2t2020n2t2121n2\n";
                        //System.out.println("ID is " + request.substring(request.length() - 3, request.length()));
                        //System.out.println("prev: "+prevInfo);
                        //System.out.println("current: "+dosageInfo);
                       // if(request.substring(0,2).equals("fu")){//force update
                            System.out.println(dose);
                            out.write(dose);
                            out.flush();
                        /*}
                        else if(prevInfo.equals(dosageInfo)){//avoid sending duplicate info to increase the efficiency
                            out.write("n");
                            out.flush();
                        }else{
                            out.write(dosageInfo);
                            out.flush();
                            prevInfo = dosageInfo;
                        }*/
                    }
                    else if (request.length() >= 10) {
                        if ((request.charAt(10) == 't' || request.charAt(10) == 'f') && ( //format is an email request
                                (prev == 0 || System.currentTimeMillis()-prev >= 60000) || //it has been 1 minutes since last request or
                                (prevrequest == "" || !prevrequest.equals(request)))){ //there is a new request
                            prevrequest=request; prev = System.currentTimeMillis();
                            getEmail mail = new getEmail();
                            serial_ID = request.substring(11, 14);
                            System.out.println("serial ID is: " + serial_ID);
                            String seg_IDs = request.substring(0, 10);
                            System.out.println("Segmets require operations are:" + seg_IDs);
                            String email_address = mail.get(serial_ID);
                            if(!seg_IDs.equals("nnnnnnnnnn")){
                                SendEmail sender = new SendEmail();
                                sender.send(serial_ID,seg_IDs,request.charAt(10));
                            }
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
