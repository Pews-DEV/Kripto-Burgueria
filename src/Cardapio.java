import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Cardapio {
    public String[] setores = {"Pão", "Queijo", "Carne de Porco", "Verdura", "Molho", "Outros"};
    public JFrame main_frame;
    public Font font_button;
    public JPanel ultima_tela;

    public JPanel main_container = new JPanel();

    public void buttonAction(String i){
        System.out.println(i);
    }

    public Cardapio(JFrame main_frame, Font font_button, JPanel ultima_tela) {
        this.main_frame = main_frame;
        this.font_button = font_button;
        this.ultima_tela = ultima_tela;
    }


    public void gerar_botoes(){

		this.main_frame.add(this.main_container);
		this.main_container.setBackground(Color.decode("#212F4D"));
		this.main_container.setVisible(true);
		this.main_container.setLayout(null);
		this.main_container.setSize(720, 512);

        for (int i = 0; i < 6; i++){
            JButton button = new JButton(setores[i]);
            button.setBounds(262, 15 + (70 * i), 195, 50);
            button.setBackground(Color.decode("#3A8AD3"));
            button.setForeground(Color.decode("#FFFFFF"));
            button.setFont(this.font_button);
            this.main_container.add(button);

            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                    buttonAction(arg0.getActionCommand());
                }
            });
        }

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

        finalizar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                System.out.println("Teste Finalizar");
            }
        });
    }
}