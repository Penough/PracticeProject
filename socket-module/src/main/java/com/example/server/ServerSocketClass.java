package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketClass {

    public static void main(String[] args) throws IOException {
        // 建立服务端socket
        ServerSocket ss = new ServerSocket(8080);
        for (;;){
            Socket s = ss.accept();// accept堵塞方法，等待客户端socket连接
            System.err.println("A client connected");
            // 建立socket输入流
            DataInputStream dis = new DataInputStream(s.getInputStream());
            // 建立socket输出流
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String req = null;
            if((req=dis.readUTF())!=null){
                System.err.println(req);
                //readUTF为阻塞式方法，通信过程中需要等待client端
                System.out.println("from " + s.getInetAddress() +
                        ", port #" + s.getPort());
            }
            dos.writeUTF("Hello, " + s.getInetAddress() + ", port#" + s.getPort());
            dis.close();
            dos.close();
            s.close();
        }
    }
}
