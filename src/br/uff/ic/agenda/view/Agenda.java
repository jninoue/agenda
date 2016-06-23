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
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Agenda extends JFrame {
    
    private DefaultListModel<Contato> contatos = new DefaultListModel<Contato>();
    JList<Contato> listaContatos;
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JTextArea campoDetalhes;
    
    public Agenda() {
        super("Agenda");
        montaJanela();
    }
    
    private void montaJanela() {        
        // Criando um painel com a lista de contatos
        JPanel painelLista = new JPanel(new BorderLayout());
        listaContatos = new JList<>(contatos);
        listaContatos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                carregaPessoa(listaContatos.getSelectedValue());
            }
        });
        painelLista.setBorder(BorderFactory.createTitledBorder("Contatos"));
        painelLista.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
                
        // Criando um painel com o nome
        JPanel painelNome = new JPanel(new BorderLayout());
        campoNome = new JTextField();
        painelNome.add(new JLabel("Nome:"), BorderLayout.WEST);        
        painelNome.add(campoNome, BorderLayout.CENTER);
        
        // Criando um painel com o telefone
        JPanel painelTelefone = new JPanel(new BorderLayout());
        campoTelefone = new JTextField();
        painelTelefone.add(new JLabel("Telefone:"), BorderLayout.WEST);
        painelTelefone.add(campoTelefone, BorderLayout.CENTER);
        
        // Criando um painel que contem tanto o nome quanto o telefone
        JPanel painelCampos = new JPanel(new GridLayout(2, 1));
        painelCampos.add(painelNome);
        painelCampos.add(painelTelefone);
        
        // Criando um painel com os detalhes
        JPanel painelDetalhes = new JPanel(new BorderLayout());
        campoDetalhes = new JTextArea();
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        painelDetalhes.add(new JScrollPane(campoDetalhes), BorderLayout.CENTER);
        
        // Criando um painel com os botões
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
                removePessoa(listaContatos.getSelectedValue());
                listaContatos.repaint();
            }
        });
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaPessoa(listaContatos.getSelectedValue());
                listaContatos.repaint();
            }
        });
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoSalvar);
        
        // Criando um painel central que combina os campos de texto, a área de texto e os botões
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelCampos, BorderLayout.NORTH);
        painelCentral.add(painelDetalhes, BorderLayout.CENTER);
        painelCentral.add(painelBotoes, BorderLayout.SOUTH);
        
        // Criando um painel do tipo split, que combina a lista com os demais componentes
        JSplitPane painelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelLista, painelCentral);
        painelPrincipal.setDividerLocation(200);
        this.setContentPane(painelPrincipal);
        this.pack();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
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
