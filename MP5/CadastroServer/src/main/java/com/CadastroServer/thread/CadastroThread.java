package com.CadastroServer.thread;

import com.CadastroServer.controller.MovimentoController;
import com.CadastroServer.controller.PessoaController;
import com.CadastroServer.model.Movimento;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CadastroThread extends Thread {
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MovimentoController movimentController;
    private PessoaController personController;

    public CadastroThread(Socket socket, MovimentoController movimentController, PessoaController personController) {
        this.clientSocket = socket;
        this.movimentController = movimentController;
        this.personController = personController;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {

                Object responseUser = in.readObject();
                if (responseUser instanceof String) {
                    String command = (String) responseUser;
                    if (command.equals("E")) {

                        processInput();
                    } else if (command.equals("S")) {

                        processOutput();
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processInput() throws IOException, ClassNotFoundException {

        int personId = in.readInt();
        int productId = in.readInt();
        int quantity = in.readInt();
        double price = in.readDouble();

        Movimento newMoviment = new Movimento();
        newMoviment.setPerson(personController.findPessoa(personId));
        Object ctrlProduto;

        newMoviment.setQuantity(quantity);
        newMoviment.setPrice(price);

        movimentController.create(newMoviment);

        movimentController.updateQuantity(productId, quantity);
    }

    private void processOutput() throws IOException, ClassNotFoundException {
    }
}
