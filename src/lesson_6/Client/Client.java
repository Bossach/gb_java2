package lesson_6.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 9999;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread receiver;
    private Thread transmitter;

    public static void main(String[] args) {
        new Client();
    }


    public Client() {

        if (connect(HOST, PORT)) {

            startReceiver();

            startTransmitter();
        }
    }

    private void startReceiver() {

        receiver = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Message from server: " + in.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

        });


        System.out.println("Starting receicer");
        receiver.start();
    }

    private void startTransmitter() {
        transmitter = new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            String line;
            while (true) {
                line = scan.nextLine();

                if (line.equalsIgnoreCase("exit")) {
                    closeClient();
                    break;
                }

                try {
                    out.writeUTF(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });


        System.out.println("Startin transmitter");
        transmitter.start();
    }

    private void closeClient() {
        transmitter.interrupt();
        receiver.interrupt();
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
            return false;
        }
        System.out.println("Connected");
        return true;
    }


}
