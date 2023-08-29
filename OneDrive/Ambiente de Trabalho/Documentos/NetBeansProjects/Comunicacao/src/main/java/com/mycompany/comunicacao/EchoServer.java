/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comunicacao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author agata
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        while (true) {
// espera blocante até alguma requisição de conexão

            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

// Cria as “streams” para o socket (buffer)
            DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

// espera a leitura do dado (até terminar conexão)
            String s;

            while ((s = in.readLine()) != null) {
                out.writeUTF(s);
            }

// Fecha a conexão (e o socket)
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();

        }
    }

}
