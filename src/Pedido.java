import javax.swing.*;
import java.util.*;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pedido extends Janela {
    private Float valor_total;
    public Integer paginte_by = 5;
    public Integer pagina = 1;
    private Banco banco = new Banco();
    private ArrayList<ArrayList<String>> ingredientes; 
    private Integer ordem = 0;

    public Pedido(JFrame main_frame, Font font_button, JPanel ultima_tela, Integer pedido){
        super(main_frame, font_button, ultima_tela);
        this.valor_total = banco.getValorPedido(pedido);
        this.ingredientes = banco.getIngredientes(pedido);
    }

    public void start(){
        this.main_container.setVisible(true);
        this.gerar_botoes();
    }

    public void gerar_botoes(){
        this.configure();

        JLabel titulo = new JLabel("Pedido");
        titulo.setBounds(290, 0, 300, 100);
        titulo.setForeground(Color.decode("#ebf1fb"));
        titulo.setFont(this.font_padrao);
        this.main_container.add(titulo);

        JLabel total = new JLabel("Total R$ " + String.format("%.02f", valor_total));
        total.setBounds(580, 0, 150, 50);
        total.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(total);

        this.ordem = 0;
        ingredientes.forEach((item) -> {
            if(this.ordem >= this.paginte_by * (this.pagina -1) && this.ordem < this.paginte_by * this.pagina){
                this.gera_ingredientes(item, this.ordem, pagina);
            };
            this.ordem = this.ordem + 1;
        });

        Integer quantidade = (ingredientes.size() + (this.paginte_by * 1) -1 ) / (this.paginte_by * 1);
        this.gerar_paginacao(quantidade);
        
    }
    public void gera_ingredientes(ArrayList<String> item, Integer position, Integer pagina){
        Integer i = -1 * (((pagina * paginte_by) + 5) - (position + 10));
        
        String nome = item.get(0).toString();
        Integer quantidade = Integer.parseInt(item.get(1).toString());
        Float valor = Float.parseFloat(item.get(2).toString());
        
        JLabel ingredienteNome = new JLabel(nome + " (" + quantidade + ")");
        ingredienteNome.setBounds(75, 50 + (i * 50), 220, 150);
        ingredienteNome.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(ingredienteNome);

        JLabel ingredienteEspace1 = new JLabel("-------------------------------------------");
        ingredienteEspace1.setBounds(285, 50 + (i * 50), 220, 150);
        ingredienteEspace1.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(ingredienteEspace1);

        JLabel ingredienteValor = new JLabel("R$ " + String.format("%.02f", valor * quantidade));
        ingredienteValor.setBounds(575, 50 + (i * 50), 220, 150);
        ingredienteValor.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(ingredienteValor);
    }

    private void gerar_paginacao(Integer quantidade){
        for (int i=1; i <= quantidade; i++){
            
            JButton paginacao_bt = new JButton("" + i);
            paginacao_bt.setBounds(50 + ((i - 1) * 60), 380, 50, 40);
            if (i == this.pagina){
                paginacao_bt.setBackground(Color.decode("#3b6892"));
            } else {
                paginacao_bt.setBackground(Color.decode("#3A8AD3"));
            }
            paginacao_bt.setForeground(Color.decode("#FFFFFF"));
            this.main_container.add(paginacao_bt);

            // Criando função que destroi e constroi a tela reativamente
            paginacao_bt.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    main_container.removeAll();
                    main_container.revalidate();
                    main_container.repaint();
                    pagina = Integer.parseInt(e.getActionCommand().toString());
                    gerar_botoes();
                }
            });
        }
    }
}
