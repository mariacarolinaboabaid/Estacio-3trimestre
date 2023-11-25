package com.CadastroClient.ui;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class SaidaFrame extends JDialog {

    public JTextArea text;

    public SaidaFrame() {
        this.setTitle("Resultado");
        this.setSize(400, 300);
        this.setModal(false);

        text = new JTextArea();
        this.add(text);
    }
}
