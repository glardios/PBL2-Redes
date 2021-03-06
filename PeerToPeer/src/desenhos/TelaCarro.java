/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenhos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author root
 */
public class TelaCarro {
     private final int width = 400;
    private final int height = 400;
    private JFrame tela;
    private JButton jButton1;
    private JButton jButton2;
    private JComboBox<String> jComboBox1;
    private JComboBox<String> jComboBox2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JTextField jTextField2;
    Container panelOpicoes;

    public TelaCarro() throws Exception {
        inicializeComponentes();
    }

    private void inicializeComponentes() throws Exception {

        tela = new JFrame();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jComboBox1 = new JComboBox<>();
        jComboBox2 = new JComboBox<>();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        Container panelSec = new JPanel();
        panelOpicoes = new JPanel();
        String nomePorta = JOptionPane.showInputDialog("Insira o nome e porta do carro separados por espaço(nome porta)", "weslei 3001");
        String[] result = nomePorta.split(" ");
        tela.setSize(width, height);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setTitle("Carros");
        tela.getContentPane().setBackground(Color.white);

        jLabel1.setText("Origem:");
        jLabel2.setText("Destino:");
        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"NORTE", "SUL", "LESTE", "OESTE"}));
        jComboBox1.addActionListener((ActionEvent evt) -> {
            jComboBox1ActionPerformed(evt);
        });

        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[]{"NORTE", "SUL", "LESTE", "OESTE"}));

        jButton1.setText("OK");
        jButton1.addActionListener((ActionEvent evt) -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener((ActionEvent evt) -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jTextField1.setText("localhost");
        jTextField1.addActionListener((ActionEvent evt) -> {
            jTextField1ActionPerformed(evt);
        });
        jTextField2.setText("3001");
        jTextField2.addActionListener((ActionEvent evt) -> {
            try {
                jTextField1ActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //configura layout
        BoxLayout box = new BoxLayout(panelOpicoes, BoxLayout.LINE_AXIS);
        panelOpicoes.setLayout(new GridBagLayout());
        panelSec.setLayout(new BorderLayout());

        panelOpicoes.setBackground(java.awt.Color.getHSBColor(233, 3, 85));

        // adicionaItem(int Linha, int Coluna, int Largura, int Altura, int EscalaX, int EscalaY, JComponent componente)
        adicionaItem(0, 0, 1, 1, 0, 0, jLabel1);

        adicionaItem(0, 1, 1, 1, 0, 0, jComboBox1);
        adicionaItem(1, 1, 1, 1, 0, 0, new JLabel(" "));

        adicionaItem(2, 0, 1, 1, 0, 0, jLabel2);

        adicionaItem(2, 1, 1, 1, 0, 0, jComboBox2);
        adicionaItem(3, 1, 1, 1, 0, 0, new JLabel(" "));

        adicionaItem(4, 0, 1, 1, 0, 0, new JLabel("IP:PORTA"));
        adicionaItem(4, 1, 1, 1, 0, 0, jTextField1);
        adicionaItem(5, 1, 1, 1, 0, 0, new JLabel(" "));
        //adicionaItem(6, 0, 1, 1, 0, 0, new JLabel("PORTA"));
       // adicionaItem(6, 1, 1, 1, 0, 0, jTextField2);
        adicionaItem(7, 1, 1, 1, 0, 0, new JLabel(" "));

        adicionaItem(8, 0, 1, 1, 0, 0, jButton1);

        adicionaItem(8, 1, 1, 1, 0, 0, jButton2);

        //adiciona componentes a painel secundario
        tela.setContentPane(panelSec);
        panelSec.setBackground(java.awt.Color.white);
        panelSec.add(panelOpicoes);

        /**
         * cria painel de desenho
         */
        //tela.add(painelDesenho);
        tela.setVisible(true);

    }
    
    
     private void jComboBox1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void jButton1ActionPerformed(ActionEvent evt) throws InterruptedException, IOException, Exception {
        // TODO add your handling code here:
        System.out.println(evt.getActionCommand());
        int index = jComboBox1.getSelectedIndex();
        String posicaoOrigem = jComboBox1.getItemAt(index);
        index = jComboBox2.getSelectedIndex();
        String posicaoFim = jComboBox2.getItemAt(index);
        if (evt.getActionCommand().equalsIgnoreCase("OK")) {
            String dados = jTextField1.getText();
          
        } else if (evt.getActionCommand().equalsIgnoreCase("Cancelar")) {
           
        }
    }

    private void jTextField1ActionPerformed(ActionEvent evt) {

    }

    public void adicionaItem(int Linha, int Coluna, int Largura, int Altura, int EscalaX, int EscalaY, JComponent componente) {

        GridBagConstraints ItemGrade = new GridBagConstraints();

        ItemGrade.gridx = Coluna;

        ItemGrade.gridy = Linha;

        ItemGrade.gridwidth = Largura;

        ItemGrade.gridheight = Altura;

        ItemGrade.weightx = EscalaX;

        ItemGrade.weighty = EscalaY;

        ItemGrade.fill = GridBagConstraints.BOTH;

        ItemGrade.anchor = GridBagConstraints.CENTER;

        panelOpicoes.add(componente, ItemGrade);

    }
    
    public static void main(String[] args) throws IOException, Exception {
        new TelaCarro();
    }
}
