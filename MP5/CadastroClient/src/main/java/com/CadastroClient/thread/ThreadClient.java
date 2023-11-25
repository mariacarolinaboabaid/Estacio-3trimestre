package com.CadastroClient.thread;

import java.io.ObjectInputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ThreadClient extends Thread {

    private ObjectInputStream input;
    private JTextArea text;

    public ThreadClient(ObjectInputStream input, JTextArea text) {
        this.input = input;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object data = input.readObject();

                SwingUtilities.invokeLater(() -> {
                    text.append(data.toString() + "\n");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
