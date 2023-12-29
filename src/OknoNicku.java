

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoNicku extends JFrame {
    private JPanel GlownyPanelNick;
    private JTextField NickTextField;
    private JLabel PodajNickLabel;
    private JButton ZatwierdzButton;
    String nick;
    public Detrasher parent;
    public OknoNicku(Detrasher parent){
        this.parent=parent;
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void initComponents() {

        GlownyPanelNick = new JPanel();
        NickTextField = new JTextField();
        ZatwierdzButton = new JButton();
        PodajNickLabel = new JLabel();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(102, 255, 102));

        GlownyPanelNick.setBackground(new Color(255, 153, 51));

        NickTextField.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        NickTextField.setHorizontalAlignment(JTextField.CENTER);

        ZatwierdzButton.setText("Zatwierdź");
        ZatwierdzButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nick=NickTextField.getText();
                Detrasher.setNick(nick);




            }
        });

        PodajNickLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        PodajNickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PodajNickLabel.setText("Podaj swój nick");

        GroupLayout GlownyPanelNickLayout = new GroupLayout(GlownyPanelNick);
        GlownyPanelNick.setLayout(GlownyPanelNickLayout);
        GlownyPanelNickLayout.setHorizontalGroup(
                GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                .addGroup(GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(PodajNickLabel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(NickTextField, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(ZatwierdzButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        GlownyPanelNickLayout.setVerticalGroup(
                GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, GlownyPanelNickLayout.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addComponent(PodajNickLabel)
                                .addGap(18, 18, 18)
                                .addComponent(NickTextField, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ZatwierdzButton)
                                .addGap(73, 73, 73))
        );

        getContentPane().add(GlownyPanelNick, BorderLayout.CENTER);

        pack();
    }}
