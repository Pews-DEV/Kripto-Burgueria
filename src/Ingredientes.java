import javax.swing.*;
import java.util.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingredientes {
    public JPanel ultima_tela;
    public Font font_button;
    public JFrame main_frame;
    private String setor;
    private Integer position = 0; 


    public JPanel main_container = new JPanel();

    public HashMap<String, ArrayList<String>> options = new HashMap<String, ArrayList<String>>();
    public HashMap<String, ArrayList<Float>> precos = new HashMap<String, ArrayList<Float>>();

    public void iniciar_opcoes(){
        options.put("Pães", new ArrayList<>(List.of("Pão Francês","Pão Carteira","Pão de Hambúrguer","Pão Árabe")));
        options.put("Queijos", new ArrayList<>(List.of("Coalho","Minas","Muçarela","Cream Cheese","Gorgonzola")));
        options.put("Carnes", new ArrayList<>(List.of("Mortadela", "Apresuntado", "Bacon", "Presunto", "Pepperoni", "Salame")));
        options.put("Verduras", new ArrayList<>(List.of("Cebola", "Pimentão", "Tomate", "Alface")));
        options.put("Molhos", new ArrayList<>(List.of("Maionese", "Ketchup", "Maionese Temperada", "Molho Tártato", "Barbecue")));
        options.put("Outros", new ArrayList<>(List.of("Batata Palha", "Ovo")));
    }

    public void iniciar_precos(){
        precos.put("Pães", new ArrayList<>(List.of(0.25f, 0.30f, 0.70f, 1.30f)));
        precos.put("Queijos", new ArrayList<>(List.of(1.50f, 1.80f, 2.00f, 3.00f, 3.50f)));
        precos.put("Carnes", new ArrayList<>(List.of(0.50f, 1.00f, 1.30f, 1.60f, 1.80f, 2.00f)));
        precos.put("Verduras", new ArrayList<>(List.of(0.30f, 0.45f, 0.50f, 0.50f)));
        precos.put("Molhos", new ArrayList<>(List.of(0.50f, 0.50f, 0.70f, 1.00f, 1.50f)));
        precos.put("Outros", new ArrayList<>(List.of(1.00f, 1.00f)));
    }

    public void create_buttons(){
        ArrayList new_options_widget = this.options.get(this.setor);
        ArrayList new_options_preco = this.precos.get(this.setor);

        new_options_widget.forEach((item) -> {
            // Valor da opção
            String preco = new_options_preco.get(this.position).toString();
            Float preco_f = Float.parseFloat(preco);

            // Label da opção
            JLabel item_label = new JLabel(item.toString());
            item_label.setBounds(50, 50 + (this.position * 50), 220, 150);
            item_label.setForeground(Color.decode("#ebf1fb"));
            this.main_container.add(item_label);

            JLabel item_label_2 = new JLabel("---------------------------------------------");
            item_label_2.setBounds(250, 50 + (this.position * 50), 250, 150);
            item_label_2.setForeground(Color.decode("#ebf1fb"));
            this.main_container.add(item_label_2);

            JLabel item_label_3 = new JLabel("R$ " + String.format("%.02f", preco_f));
            item_label_3.setBounds(500, 50 + (this.position * 50), 220, 150);
            item_label_3.setForeground(Color.decode("#ebf1fb"));
            this.main_container.add(item_label_3);
            

            JTextField value = new JTextField(5);
            value.setBounds(600, 115 + (this.position * 50), 40, 20);
            this.main_container.add(value);
            //Integer preco  = integer.toString(value)
            //System.out.println();
            //String teste = value.getText();
            
            //Double valor = Double.parseDouble(value.getText());
            //System.out.println(valor);
            String valor = value.getSelectedText();
            //Integer teste_i = Integer.valueOf(valor);
            Integer teste_i = Integer.parseInt(valor);
            System.out.println(teste_i);
            this.position = this.position + 1;
        });

    }

    public void iniciar_nova_tela(){
		this.main_frame.add(this.main_container);
		this.main_container.setBackground(Color.decode("#212F4D"));
		this.main_container.setVisible(true);
		this.main_container.setLayout(null);
		this.main_container.setSize(720, 512);

        // Iniciando botões de cada opção
        this.create_buttons();

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(262, 435, 97, 40);
        voltar.setBackground(Color.decode("#FF0000"));
        voltar.setForeground(Color.decode("#FFFFFF"));
        voltar.setFont(this.font_button);
        this.main_container.add(voltar);

        JButton finalizar = new JButton("Finalizar");
        finalizar.setBounds(360, 435, 97, 40);
        finalizar.setBackground(Color.decode("#28a745"));
        finalizar.setForeground(Color.decode("#FFFFFF"));
        finalizar.setFont(this.font_button);
        this.main_container.add(finalizar);

        voltar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                main_container.setVisible(false);
                ultima_tela.setVisible(true);
            }
        });

    }

    
    public Ingredientes(JFrame main_frame, Font font_button, JPanel ultima_tela, String setor) {
        this.ultima_tela = ultima_tela;
        this.main_frame = main_frame;
        this.font_button = font_button;
        this.setor = setor;

        // Iniciando opções de compra e preços
        this.iniciar_opcoes();
        this.iniciar_precos();

        this.ultima_tela.setVisible(false);

        // Iniciando nova tela
        this.iniciar_nova_tela();

    }
}