package br.uff.ic.agenda.view;

import br.uff.ic.agenda.controller.ControleCarregar;
import br.uff.ic.agenda.controller.ControleSalvar;
import br.uff.ic.agenda.model.Contato;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Agenda extends JFrame {
    
    private final DefaultListModel<Contato> contatos = new DefaultListModel<>();
    private final JList<Contato> listaContatos = new JList<>(contatos);
    private final JTextField campoNome = new JTextField();
    private final JTextField campoTelefone = new JTextField();
    private final JTextArea campoDetalhes = new JTextArea();
    
    public Agenda() {
        super("Agenda");
        montaJanela();
    }
    
    private void montaJanela() {        
        // Criando um painel com a lista de contatos
        JPanel painelLista = new JPanel(new BorderLayout());
        listaContatos.addListSelectionListener(new ControleCarregar(listaContatos, campoNome, campoTelefone, campoDetalhes));
        painelLista.setBorder(BorderFactory.createTitledBorder("Contatos"));
        painelLista.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        
        // Criando um painel com os botões sob a lista
        JButton botaoAdicionar;
        URL addURL = getClass().getResource("/toolbarButtonGraphics/general/Add24.gif");
        if (addURL != null) {
            botaoAdicionar = new JButton(new ImageIcon(addURL));
        } else {
            botaoAdicionar = new JButton("Adicionar");
        }
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaPessoa();
            }
        }); 
        JButton botaoRemover;
        URL deleteURL = getClass().getResource("/toolbarButtonGraphics/general/Delete24.gif");
        if (deleteURL != null) {
            botaoRemover = new JButton(new ImageIcon(deleteURL));
        } else {
            botaoRemover = new JButton("Remover");
        }
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePessoa(listaContatos.getSelectedValue());
                listaContatos.repaint();
            }
        });
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2));
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelLista.add(painelBotoes, BorderLayout.SOUTH);
                        
        // Criando um painel com o nome
        JPanel painelNome = new JPanel(new BorderLayout());
        painelNome.add(new JLabel("Nome:"), BorderLayout.WEST); 
        campoNome.setEnabled(false);
        painelNome.add(campoNome, BorderLayout.CENTER);
        
        // Criando um painel com o telefone
        JPanel painelTelefone = new JPanel(new BorderLayout());
        painelTelefone.add(new JLabel("Telefone:"), BorderLayout.WEST);
        campoTelefone.setEnabled(false);
        painelTelefone.add(campoTelefone, BorderLayout.CENTER);
        
        // Criando um painel que contem tanto o nome quanto o telefone
        JPanel painelCampos = new JPanel(new GridLayout(2, 1));
        painelCampos.add(painelNome);
        painelCampos.add(painelTelefone);
        
        // Criando um painel com os detalhes
        JPanel painelDetalhes = new JPanel(new BorderLayout());
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        campoDetalhes.setEnabled(false);
        painelDetalhes.add(new JScrollPane(campoDetalhes), BorderLayout.CENTER);

        // Criando um painel central que combina os campos de texto, a área de texto e os botões
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelCampos, BorderLayout.NORTH);
        painelCentral.add(painelDetalhes, BorderLayout.CENTER);
        
        // Criando um painel do tipo split, que combina a lista com os demais componentes
        JSplitPane painelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, painelLista, painelCentral);
        painelPrincipal.setDividerLocation(200);
        this.setContentPane(painelPrincipal);

        // Configurando os listeners
        ControleSalvar salvar = new ControleSalvar(listaContatos, campoNome, campoTelefone, campoDetalhes);
        campoNome.addKeyListener(salvar);
        campoTelefone.addKeyListener(salvar);
        campoDetalhes.addKeyListener(salvar);

        // Configuration a janela
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
    
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
    }
}