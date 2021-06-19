import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingredientes extends Janela {
    public JButton finalizar;
    public ArrayList<Integer> pedidos;
    public ArrayList<JSpinner> campos = new ArrayList<JSpinner>(
        List.of(new JSpinner(), new JSpinner(), new JSpinner(), new JSpinner(), new JSpinner(), new JSpinner(), new JSpinner())
    );

    private String setor;
    private Integer position = 0; 

    public HashMap<String, ArrayList<String>> options = new HashMap<String, ArrayList<String>>();
    public HashMap<String, ArrayList<Float>> precos = new HashMap<String, ArrayList<Float>>();

    public Ingredientes(JFrame main_frame, Font font_button, JPanel ultima_tela, String setor, ArrayList<Integer> pedidos, JButton finalizar) {
        super(main_frame, font_button, ultima_tela);
        this.setor = setor;
        this.pedidos = pedidos;
        this.finalizar = finalizar;

    }

    public void start(){
        // Iniciando opções de compra e preços
        this.iniciar_opcoes();
        this.iniciar_precos();

        this.ultima_tela.setVisible(false);
		this.configure();

        // Iniciando nova tela
        this.iniciar_nova_tela();
    }

    public void iniciar_opcoes(){
        options.put("Pães", new ArrayList<>(List.of("Pão Francês","Pão Carteira","Pão de Hambúrguer","Pão Árabe")));
        options.put("Queijos", new ArrayList<>(List.of("Coalho","Minas","Muçarela","Cream Cheese","Gorgonzola")));
        options.put("Carnes", new ArrayList<>(List.of("Mortadela", "Apresuntado", "Bacon", "Presunto", "Pepperoni", "Salame")));
        options.put("Verduras", new ArrayList<>(List.of("Cebola", "Pimentão", "Tomate", "Alface")));
        options.put("Molhos", new ArrayList<>(List.of("Maionese", "Ketchup", "Maionese Temperada", "Molho Tártato", "Barbecue")));
        options.put("Outros", new ArrayList<>(List.of("Batata Palha", "Ovo")));
    }

    public void iniciar_precos(){
        precos.put("Pães", new ArrayList<>(List.of(0.25f, 0.30f, 0.70f, 1.30f, 0f, 0f, 0f)));
        precos.put("Queijos", new ArrayList<>(List.of(1.50f, 1.80f, 2.00f, 3.00f, 3.50f, 0f)));
        precos.put("Carnes", new ArrayList<>(List.of(0.50f, 1.00f, 1.30f, 1.60f, 1.80f, 2.00f)));
        precos.put("Verduras", new ArrayList<>(List.of(0.30f, 0.45f, 0.50f, 0.50f, 0f, 0f)));
        precos.put("Molhos", new ArrayList<>(List.of(0.50f, 0.50f, 0.70f, 1.00f, 1.50f, 0f)));
        precos.put("Outros", new ArrayList<>(List.of(1.00f, 1.00f, 0f, 0f, 0f, 0f, 0f, 0f)));
    }

    public void create_buttons(){
        ArrayList<String> new_options_widget = this.options.get(this.setor);
        ArrayList<Float> new_options_preco = this.precos.get(this.setor);

        new_options_widget.forEach((item) -> {
            this.gerar_linha_opcao(item.toString(), new_options_widget, new_options_preco);
        });
    }

    public void gerar_linha_opcao(String item, ArrayList<String> new_options_widget, ArrayList<Float> new_options_preco){
        // Valor da opção
        String preco = new_options_preco.get(this.position).toString();
        Float preco_f = Float.parseFloat(preco);

        // Label da opção
        JLabel item_label = new JLabel(item);
        item_label.setBounds(75, 50 + (this.position * 50), 220, 150);
        item_label.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label);

        JLabel item_label_2 = new JLabel("---------------------------------------------");
        item_label_2.setBounds(275, 50 + (this.position * 50), 250, 150);
        item_label_2.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label_2);

        JLabel item_label_3 = new JLabel("R$ " + String.format("%.02f", preco_f));
        item_label_3.setBounds(525, 50 + (this.position * 50), 220, 150);
        item_label_3.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label_3);

        SpinnerModel model = new SpinnerNumberModel(0, 0, 100000000, 1);

        JSpinner item_quantidade = new JSpinner(model);
        item_quantidade.setValue(Integer.parseInt(pedidos.get(this.position).toString()));
        item_quantidade.setBounds(595, 115 + (this.position * 50), 60, 20);
        item_quantidade.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_quantidade);
        campos.set(position, item_quantidade);

        this.position = this.position + 1;
    }

    public void iniciar_nova_tela(){

        JLabel title = new JLabel(this.setor);
		title.setBounds(290, 0, 300, 100);
		title.setForeground(Color.decode("#ebf1fb"));
		title.setFont(this.font_padrao);
		this.main_container.add(title);

        // Iniciando botões de cada opção
        this.create_buttons();

        this.main_container.add(this.finalizar);
    }
}
