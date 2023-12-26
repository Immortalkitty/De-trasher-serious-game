import javax.swing.*;

public class OknoNicku extends JFrame {
    private javax.swing.JPanel GlownyPanelNick;
    private javax.swing.JTextField NickTextField;
    private javax.swing.JLabel PodajNickLabel;
    private javax.swing.JButton ZatwierdzButton;
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

        GlownyPanelNick = new javax.swing.JPanel();
        NickTextField = new javax.swing.JTextField();
        ZatwierdzButton = new javax.swing.JButton();
        PodajNickLabel = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 102));

        GlownyPanelNick.setBackground(new java.awt.Color(255, 153, 51));

        NickTextField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NickTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        ZatwierdzButton.setText("Zatwierdź");
        ZatwierdzButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nick=NickTextField.getText();
                Detrasher.setNick(nick);
                System.out.println(Detrasher.nick);



            }
        });

        PodajNickLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PodajNickLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PodajNickLabel.setText("Podaj swój nick");

        javax.swing.GroupLayout GlownyPanelNickLayout = new javax.swing.GroupLayout(GlownyPanelNick);
        GlownyPanelNick.setLayout(GlownyPanelNickLayout);
        GlownyPanelNickLayout.setHorizontalGroup(
                GlownyPanelNickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                .addGroup(GlownyPanelNickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PodajNickLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(NickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(ZatwierdzButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        GlownyPanelNickLayout.setVerticalGroup(
                GlownyPanelNickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GlownyPanelNickLayout.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addComponent(PodajNickLabel)
                                .addGap(18, 18, 18)
                                .addComponent(NickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ZatwierdzButton)
                                .addGap(73, 73, 73))
        );

        getContentPane().add(GlownyPanelNick, java.awt.BorderLayout.CENTER);

        pack();
    }}
