package br.uff.ic.agenda.view;

import br.uff.ic.agenda.model.Contato;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Agenda extends JFrame {

    private DefaultListModel<Contato> contatos = new DefaultListModel<Contato>();
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextArea campoDetalhes;

    public Agenda() {
        super("Agenda");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciaComponentes(this.getContentPane());
        this.pack();
    }

    private void iniciaComponentes(Container painelPrincipal) {
        painelPrincipal.setLayout(new BorderLayout());
        JList<Contato> lista = new JList<>(contatos);
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                carregaPessoa(lista.getSelectedValue());
            }
        });
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(80, 300));
        painelPrincipal.add(scroll, BorderLayout.WEST);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BorderLayout());
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(2, 2, 5, 5));
        painelCampos.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelCampos.add(campoNome);
        painelCampos.add(new JLabel("Telefone:"));
        campoTelefone = new JTextField();
        painelCampos.add(campoTelefone);
        painelCentral.add(painelCampos, BorderLayout.NORTH);

        campoDetalhes = new JTextArea();
        scroll = new JScrollPane(campoDetalhes);
        scroll.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        painelCentral.add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 3));

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaPessoa();
            }
        });

        JButton botaoRemover = new JButton("Remover");
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePessoa(lista.getSelectedValue());
                lista.repaint();
            }
        });

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaPessoa(lista.getSelectedValue());
                lista.repaint();
            }
        });

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoSalvar);
        painelCentral.add(painelBotoes, BorderLayout.SOUTH);
    }

    private void adicionaPessoa() {
        Contato novaPessoa = new Contato();
        contatos.addElement(novaPessoa);
    }

    private void removePessoa(Contato pessoaSelecionada) {
        contatos.removeElement(pessoaSelecionada);
    }

    private void salvaPessoa(Contato pessoaSelecionada) {
        pessoaSelecionada.setNome(campoNome.getText());
        pessoaSelecionada.setTelefone(campoTelefone.getText());
        pessoaSelecionada.setDetalhes(campoDetalhes.getText());
    }

    private void carregaPessoa(Contato pessoaSelecionada) {
        if (pessoaSelecionada != null) {
            campoNome.setText(pessoaSelecionada.getNome());
            campoTelefone.setText(pessoaSelecionada.getTelefone());
            campoDetalhes.setText(pessoaSelecionada.getDetalhes());
        }
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
    }
}
