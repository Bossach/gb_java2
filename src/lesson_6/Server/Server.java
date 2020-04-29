package lesson_6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private static final int PORT = 9999;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread receiver;
    private Thread transmitter;

    public static void main(String[] args) {

            new Server(PORT);

    }


    public Server(int port) {

        startServer(port);

        if (!socket.isClosed()){
            startReceiver();
            startTransmiter();
        }
    }

    private void startServer(int port) {
        Thread waitAnim = new LoadAnim();

        try {
            serverSocket = new ServerSocket(port);
            System.out.print("Waiting for client ");
            waitAnim.start();
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            waitAnim.interrupt();
        }
        System.out.println("Client connected.");
    }

    private void startTransmiter() {
        transmitter = new Thread(() -> {
            Scanner scan = new Scanner(System.in);
            String line;
            while (true) {
                line = scan.nextLine();

                if (line.equalsIgnoreCase("close")) break;

                try {
                    out.writeUTF(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            new Thread(this::closeServer).start();
        });

        transmitter.start();
    }

    public void startReceiver() {
        receiver = new Thread(() -> {
            String msg;
            while (true) {
                try {
                    msg = in.readUTF();
                } catch (IOException e) {
                    System.out.println("Что-то пошло не так");
                    break;
                }

                if (msg.startsWith("/")) {
                    parseCommand(msg);
                } else {
                    System.out.println("Message from client: " + msg);
                }
            }
            new Thread(this::closeServer).start();
        });

        System.out.println("Starting receiver");
        receiver.start();
    }

    private void closeServer() {

        transmitter.interrupt();
        receiver.interrupt();

        if (!socket.isClosed())
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        if (!serverSocket.isClosed())
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("Server closed");
        System.exit(0);
    }

    private void parseCommand(String sourceLine) {
        String[] splicedCommand = sourceLine.split(" ", 2);
        String command = splicedCommand[0];
        String argument = "";
        if (splicedCommand.length > 1) argument = splicedCommand[1];

        switch (command) {
            case "/close":
                new Thread(this::closeServer).start();
                break;
            case "/echo":
                try {
                    out.writeUTF(argument);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    out.writeUTF("Unknown command, try '/echo' or '/close'");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

}
