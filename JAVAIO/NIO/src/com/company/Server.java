package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server is running");
        for (;;) {
            Socket socket = serverSocket.accept();
            Thread handler = new Handler(socket);
            handler.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;


    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                handle(inputStream, outputStream);
            }
        } catch (IOException e) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        var writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        boolean requestOk = false;
        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        for (;;) {
            String header = reader.readLine();
            if (header.isEmpty()) { // 读取到空行时, HTTP Header读取完毕
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // 发送成功响应:
            String data = "<html><body><h1>Hello, world!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }

    }
}
