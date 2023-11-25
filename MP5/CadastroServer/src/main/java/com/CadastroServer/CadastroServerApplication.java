package com.CadastroServer;

import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.CadastroServer.controller.MovimentoController;
import com.CadastroServer.controller.PessoaController;
import com.CadastroServer.thread.CadastroThread;

@SpringBootApplication
@EnableJdbcRepositories
public class CadastroServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CadastroServerApplication.class, args);

        MovimentoController ctrlMov = new MovimentoController();
        PessoaController ctrlPessoa = new PessoaController();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                CadastroThread thread = new CadastroThread(clientSocket, ctrlMov, ctrlPessoa);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

