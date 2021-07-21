package com.example.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocketClass {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        // 建立client输出管道
        OutputStream ops = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ops);
        dos.writeUTF("Hello!Server, This is Client.");

        //建立client端的输入管道,用于收取服务端返回
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println(dis.readUTF());
        dos.flush();
        dos.close();
        ops.close();
        dis.close();
        socket.close();
    }
}
