package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class ControleAdicionar implements ActionListener {

    private final DefaultListModel<Contato> contatos;
    
    public ControleAdicionar (DefaultListModel<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contatos.addElement(new Contato());
    }
}