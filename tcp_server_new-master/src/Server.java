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
        ender.send("001","01n3n5nn",'t');
        System.out.println("Sending take email...");
        //dosage info test
        getDosageInfo dosagetest = new getDosageInfo();
        String dosageInfotest = dosagetest.getInfo("003");
        System.out.println(dosageInfotest);

        String test = "0nnnn5nn89t0010nnnn5nn89f001";
        System.out.println("opcodes: " + test.charAt(10)+" and " + test.charAt(24));


        SendEmail testsender = new SendEmail();
        testsender.send("003","0nnnnnnn",'t');
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


                    if((request.length() > 6 && request.substring(0,6).equals("update")) || //e.g: update001
                            (request.length() > 2 && request.substring(0,2).equals("fu"))) {//force update
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        getDosageInfo dosage = new getDosageInfo();
                        dose = dosage.getInfo(request.substring(request.length() - 3, request.length()))+"\n";
                        //dose = "p0t1246n4t1010n1t1155n5p1t1155n5t2121n2p2t3030n3p3t1134n4t4545n5p4t5555n5t5656n6p5t1255n6t6363n6p6t7777n7t7979n7p7t8888n8t8080n8p8t1155n6t9494n8p9t1100n9t1100n9\n";
                        LocalDateTime time = LocalDateTime.now();
                        String hour = new DecimalFormat("00").format(time.getHour());
                        String min = new DecimalFormat("00").format(time.getMinute());
                        String sec = new DecimalFormat("00").format(time.getSecond());
                        String time_message = hour+min+sec;
                        System.out.println(time_message+dose);
                        out.write("144500"+dose);
                        out.flush();

                    }
                    else if (request.length() >= 20) { //e.g: fnnnnnnnnnnt
                        if ((request.charAt(0) == 'f' && request.charAt(11) == 't') && ( //format is an email request
                                (prev == 0 || System.currentTimeMillis()-prev >= 120000) || //it has been 2 minutes since last duplicate request or
                                (prevrequest == "" || !prevrequest.equals(request)))){ //there is a new request
                            prevrequest=request; prev = System.currentTimeMillis();
                            getEmail mail = new getEmail();
                            serial_ID = request.substring(22, 25);
                            System.out.println("serial ID is: " + serial_ID);
                            String fill_IDs = request.substring(1, 11);

                            String take_IDs = request.substring(12, 22);
                            System.out.println("Segmets require operations are:" + fill_IDs + " and "+take_IDs);

                            String email_address = mail.get(serial_ID);
                            if(!take_IDs.equals("nnnnnnnnnn")){
                                SendEmail sender = new SendEmail();
                                sender.send(serial_ID, take_IDs,'t');
                            }
                            if(!fill_IDs.equals("nnnnnnnnnn")){
                                SendEmail sender = new SendEmail();
                                sender.send(serial_ID, fill_IDs,'f');
                            }
                        }
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
