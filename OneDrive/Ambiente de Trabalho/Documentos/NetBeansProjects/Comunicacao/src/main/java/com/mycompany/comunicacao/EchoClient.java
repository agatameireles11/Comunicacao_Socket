/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comunicacao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author agata
 */
public class EchoClient {

    public static void main(String[] args) throws Exception {
        String screenName = args[0];
        String host = args[1];
        int port = 4444;
// conecta ao servidor e abre os streams
        Socket socket = new Socket(host, port);
        //BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        Scanner stdin = new Scanner(System.in);
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        System.err.println("Connected to " + host + " on port " + port);
// lê da entrada padrão stdin, envia, escreve resposta
        while (!stdin.hasNextLine()) {
// leitura
            String s = stdin.nextLine();
// envio pelo socket
            out.writeUTF("[" + screenName + "]: " + s);
// pega resposta
            System.out.println(in.readLine());
        }
// encerra os sockets
        System.err.println("Closing connection to " + host);
        out.close();
        in.close();
        socket.close();
    }

}
