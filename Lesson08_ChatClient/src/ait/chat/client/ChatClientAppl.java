package ait.chat.client;

import ait.chat.client.task.MessageReceiver;
import ait.chat.client.task.MessageSender;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChatClientAppl {
    public static void main(String[] args) throws InterruptedException {
        String serverHost = "127.0.0.1";
        int port = 9000;
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            Socket socket = new Socket(serverHost, port);
             MessageSender messageSender = new MessageSender(socket);
             MessageReceiver messageReceive = new MessageReceiver(socket);

            executorService.execute(messageReceive);
            executorService.execute(messageSender);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        }


    }
}
