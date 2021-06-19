import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Historico extends Janela {
    public Banco banco = new Banco();
    public ArrayList<Integer> pedidos;
    private Integer position;
    public Integer paginte_by = 5;
    public Integer pagina = 1;
	

    public void atualizarPedidos(){
        this.pedidos = banco.getPedidos();
        main_container.removeAll();
        main_container.revalidate();
        main_container.repaint();
        this.start();
    }


    public Historico (JFrame main_frame, Font font_button, JPanel ultima_tela) {
        super(main_frame, font_button, ultima_tela);
    }

    public void start(){
        this.pedidos = banco.getPedidos();

        this.ultima_tela.setVisible(false);

        this.iniciar_widgets();
        
    }

    public void iniciar_widgets(){
        this.configure();

        JLabel title = new JLabel("Histórico");
		title.setBounds(290, 0, 300, 100);
		title.setForeground(Color.decode("#ebf1fb"));
		title.setFont(this.font_padrao);
		this.main_container.add(title);

        position = 0;
        pedidos.forEach((item) -> {
            if (position >= this.paginte_by * (this.pagina -1) && position < this.paginte_by * this.pagina){
                this.criar_botoes(item, position, pagina);
            }
            this.position = position + 1;
        });


        // Criando botoões de paginação
        Integer quantidade = (pedidos.size() + (this.paginte_by * 1) -1 ) / (this.paginte_by * 1);
        gerar_paginacao(quantidade);
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
                    iniciar_widgets();
                }
            });
        }
    }

    private void criar_botoes(Integer item, Integer position, Integer pagina){
        Integer ordem = -1 * (((pagina * paginte_by) + 5) - (position + 10));

        JLabel item_label = new JLabel("Pedido " + (position + 1));
        item_label.setBounds(50, 50 + (ordem * 50), 220, 150);
        item_label.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label);

        JLabel item_label_2 = new JLabel("-------------------------------------------------------");
        item_label_2.setBounds(180, 50 + (ordem * 50), 300, 150);
        item_label_2.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label_2);

        Float valor_total = this.banco.getValorPedido(item);

        JLabel item_label_3 = new JLabel("R$ " + String.format("%.02f", valor_total));
        item_label_3.setBounds(500, 50 + (ordem * 50), 220, 150);
        item_label_3.setForeground(Color.decode("#ebf1fb"));
        this.main_container.add(item_label_3);

        JButton detalhes = new JButton("Ver mais");
        detalhes.setBounds(600, 110 + (ordem * 50), 100, 30);
        detalhes.setForeground(Color.decode("#FFFFFF"));
        detalhes.setBackground(Color.decode("#212F4D"));
        this.main_container.add(detalhes);

        detalhes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                main_container.setVisible(false);
                Pedido pedido = new Pedido(main_frame, font_button, main_container, item);
                pedido.start();
            }
        });
    }
}

