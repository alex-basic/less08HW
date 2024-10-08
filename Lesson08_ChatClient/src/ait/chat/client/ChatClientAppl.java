package ait.chat.client;

import ait.chat.client.task.MessageReceiver;
import ait.chat.client.task.MessageSender;

import java.io.IOException;
import java.net.Socket;

public class ChatClientAppl {
    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        int port = 9000;

        Socket socket = null;
        try {
            socket = new Socket(serverHost, port);
            Thread taskMessageSender = new Thread(new MessageSender(socket));
            taskMessageSender.setDaemon(true);

            Thread taskMessageReceiver = new Thread(new MessageReceiver(socket));
            taskMessageReceiver.setDaemon(true);

            taskMessageReceiver.start();
            taskMessageSender.start();
            taskMessageSender.join();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
