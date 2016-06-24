package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControleCarregar implements ListSelectionListener {

    private final JList<Contato> listaContatos;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextArea campoDetalhes;
    
    public ControleCarregar (JList<Contato> listaContatos, JTextField campoNome, JTextField campoTelefone, JTextArea campoDetalhes) {
        this.listaContatos = listaContatos;
        this.campoNome = campoNome;
        this.campoTelefone = campoTelefone;
        this.campoDetalhes = campoDetalhes;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Contato pessoaSelecionada = listaContatos.getSelectedValue();
        
        if (pessoaSelecionada != null) {
            campoNome.setText(pessoaSelecionada.getNome());
            campoTelefone.setText(pessoaSelecionada.getTelefone());
            campoDetalhes.setText(pessoaSelecionada.getDetalhes());
        } else {
            campoNome.setText("");
            campoTelefone.setText("");
            campoDetalhes.setText("");
        }

        campoNome.setEnabled(pessoaSelecionada != null);
        campoTelefone.setEnabled(pessoaSelecionada != null);
        campoDetalhes.setEnabled(pessoaSelecionada != null);

        listaContatos.repaint();        
    }
}