import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela {
    public JFrame main_frame;
    public JPanel ultima_tela;
    public Font font_button;

    public JPanel main_container = new JPanel();
    public Font font_padrao = new Font("Contrail One", Font.PLAIN, 52);
    

    public Janela(JFrame main_frame, Font font_button, JPanel ultima_tela){
        this.ultima_tela = ultima_tela;
        this.main_frame = main_frame;
        this.font_button = font_button;
    }

    public void configure(){

		this.main_frame.add(this.main_container);
		this.main_container.setBackground(Color.decode("#212F4D"));
		this.main_container.setVisible(true);
		this.main_container.setLayout(null);
		this.main_container.setSize(720, 512);


        JButton voltar = new JButton("Voltar");
        voltar.setBounds(262, 435, 97, 40);
        voltar.setBackground(Color.decode("#FF0000"));
        voltar.setForeground(Color.decode("#FFFFFF"));
        voltar.setFont(this.font_button);
        this.main_container.add(voltar);

        voltar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                
                main_container.setVisible(false);
                ultima_tela.setVisible(true);
            }
        });
    }
}
