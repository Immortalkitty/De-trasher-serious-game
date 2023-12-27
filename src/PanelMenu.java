import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.BOLD;


public class PanelMenu extends JPanel {

    private PanelSzans panelSzans;
    Detrasher instancja;
    public JButton menuPrzycisk;
    private JLabel punktacjaLabel;
    public JLabel szanseLabel;
    private Timer timerMenu;
    private int interwałMenu;
    private javax.swing.JButton informacjaButton;

    public javax.swing.JButton restartButton;
    private javax.swing.JButton zakonczButton;
    private javax.swing.JButton zmienNickButton;



    public void setPunktacja()
    {
        this.punktacjaLabel.setText(("Punkty: " + PanelGry.dobrePrzyporzadkowanieLicznik));
    }

    public void initUIMenu(Detrasher parent){
        this.instancja=parent;
        menuPrzycisk = new JButton();
        punktacjaLabel = new JLabel();
        panelSzans = new PanelSzans();

        szanseLabel = new JLabel();

        informacjaButton = new javax.swing.JButton();
        zakonczButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        zmienNickButton = new javax.swing.JButton();


        setBackground(new Color(255, 153, 0));
        setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new Dimension(1000, 100));
        setPreferredSize(new Dimension(1000, 100));
        setLayout(new GridLayout(1, 3, 40, 40));

        menuPrzycisk.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        menuPrzycisk.setText("Menu");
        menuPrzycisk.setAlignmentY(20.0F);
        menuPrzycisk.setContentAreaFilled(false);
        menuPrzycisk.setHorizontalTextPosition(SwingConstants.CENTER);
        menuPrzycisk.setMaximumSize(new Dimension(75, 20));
        menuPrzycisk.setMinimumSize(new Dimension(75, 20));
        menuPrzycisk.setPreferredSize(new Dimension(75, 20));

        add(menuPrzycisk);

        punktacjaLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        punktacjaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        punktacjaLabel.setText("Punkty: 0");
        punktacjaLabel.setAlignmentX(40.0F);
        punktacjaLabel.setAlignmentY(40.0F);
        punktacjaLabel.setAutoscrolls(true);
        punktacjaLabel.setMaximumSize(new Dimension(75, 20));
        punktacjaLabel.setMinimumSize(new Dimension(75, 20));
        punktacjaLabel.setPreferredSize(new Dimension(75, 20));

        add(punktacjaLabel);

        panelSzans.setBackground(new Color(255, 153, 0));

        szanseLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        szanseLabel.setText("");

        szanseLabel.setVerticalAlignment(SwingConstants.TOP);
        szanseLabel.setFocusable(false);

        GroupLayout PanelSzansLayout = new GroupLayout(panelSzans);
        panelSzans.setLayout(PanelSzansLayout);
        PanelSzansLayout.setHorizontalGroup(
                PanelSzansLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, PanelSzansLayout.createSequentialGroup()
                                .addContainerGap(127, Short.MAX_VALUE)
                                .addComponent(szanseLabel)
                                .addGap(125, 125, 125))
        );
        PanelSzansLayout.setVerticalGroup(
                PanelSzansLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(PanelSzansLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(szanseLabel)
                                .addContainerGap(152, Short.MAX_VALUE))
        );

        add(panelSzans);




    }


        public void infoBox(String infoMessage, String titleBar)
    {
        JTextArea text=new JTextArea("Jak grać? \n\n"+infoMessage);
        text.setFont(new Font("Segoe UI", BOLD, 18));
        text.setForeground(new Color(168, 52, 235));
        text.setBackground(new Color(255, 153, 0, 179));
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text,titleBar, JOptionPane.PLAIN_MESSAGE);

    }
    public void MenuWersjaB(){
        //ta metoda rysuje drugą wersję menu z innymi przyciskami
        //powrotButton = new javax.swing.JButton();
        remove(panelSzans);
        remove(szanseLabel);

        remove(punktacjaLabel);

        this.setBackground(new java.awt.Color(255, 153, 0));
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setMinimumSize(new java.awt.Dimension(1000, 200));
        this.setPreferredSize(new java.awt.Dimension(1000, 200));
        this.setLayout(new java.awt.GridLayout(1, 3, 40, 40));

        menuPrzycisk.setBackground(new java.awt.Color(255, 153, 0));
        menuPrzycisk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuPrzycisk.setText("Powrót");
        menuPrzycisk.setAlignmentY(20.0F);
        menuPrzycisk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuPrzycisk.setContentAreaFilled(false);
        menuPrzycisk.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuPrzycisk.setMaximumSize(new java.awt.Dimension(75, 20));
        menuPrzycisk.setMinimumSize(new java.awt.Dimension(75, 20));
        menuPrzycisk.setPreferredSize(new java.awt.Dimension(75, 20));
        add(menuPrzycisk);

        informacjaButton.setBackground(new java.awt.Color(255, 153, 0));
        informacjaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        informacjaButton.setText("O grze");
        informacjaButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(informacjaButton);
        informacjaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                infoBox("Steruj strzałkami klawiatury aby odpady trafiły do właściwych pojemników.\n\nWznowisz grę ENTEREM, a zatrzymasz klawiszem P.\n\nMożesz zmienić nick pod jakim w pliku zostanie zapisany twój wynik na początku gry \nlub także w trakcie z poziomu menu.\n\nMiłej zabawy! ","O grze");
                informacjaButton.setFocusable(false);
            }

        });

        zakonczButton.setBackground(new java.awt.Color(255, 153, 0));
        zakonczButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        zakonczButton.setText("Zakończ Grę");
        zakonczButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(zakonczButton);

        zakonczButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);


            }
        });

        restartButton.setBackground(new java.awt.Color(255, 153, 0));
        restartButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        restartButton.setText("Restart gry");
        restartButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(restartButton);



        zmienNickButton.setBackground(new java.awt.Color(255, 153, 0));
        zmienNickButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        zmienNickButton.setText("Zmień swój nick");
        zmienNickButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(zmienNickButton);

        zmienNickButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OknoNicku oknoNicku=new OknoNicku(instancja);



            }
        });







    }
    public PanelMenu(Detrasher detrasher){
        initUIMenu(instancja);
       interwałMenu=300;
        timerMenu = new Timer(interwałMenu, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                setPunktacja();
                setSzanse();

            }
        });

        timerMenu.start();

    }

    public void setSzanse()
    {
        //this.szanseLabel.setText(("Szanse:" + PanelGry.szanse));
        panelSzans.repaint();
    }

public void MenuWersjaA(){
    remove(zmienNickButton);
    remove(restartButton);
    remove(informacjaButton);
    remove(zakonczButton);

    setBackground(new Color(255, 153, 0));
    setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    setMinimumSize(new Dimension(1000, 100));
    setPreferredSize(new Dimension(1000, 100));
    setLayout(new GridLayout(1, 3, 40, 40));

    menuPrzycisk.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
    menuPrzycisk.setText("Menu");
    menuPrzycisk.setAlignmentY(20.0F);
    menuPrzycisk.setContentAreaFilled(false);
    menuPrzycisk.setHorizontalTextPosition(SwingConstants.CENTER);
    menuPrzycisk.setMaximumSize(new Dimension(75, 20));
    menuPrzycisk.setMinimumSize(new Dimension(75, 20));
    menuPrzycisk.setPreferredSize(new Dimension(75, 20));

    add(menuPrzycisk);

    punktacjaLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
    punktacjaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    punktacjaLabel.setText("Punkty: 0");
    punktacjaLabel.setAlignmentX(40.0F);
    punktacjaLabel.setAlignmentY(40.0F);
    punktacjaLabel.setAutoscrolls(true);
    punktacjaLabel.setMaximumSize(new Dimension(75, 20));
    punktacjaLabel.setMinimumSize(new Dimension(75, 20));
    punktacjaLabel.setPreferredSize(new Dimension(75, 20));

    add(punktacjaLabel);

    panelSzans.setBackground(new Color(255, 153, 0));

    szanseLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
    szanseLabel.setText("");
    szanseLabel.setVerticalAlignment(SwingConstants.TOP);
    szanseLabel.setFocusable(false);

    GroupLayout PanelSzansLayout = new GroupLayout(panelSzans);
    panelSzans.setLayout(PanelSzansLayout);
    PanelSzansLayout.setHorizontalGroup(
            PanelSzansLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, PanelSzansLayout.createSequentialGroup()
                            .addContainerGap(127, Short.MAX_VALUE)
                            .addComponent(szanseLabel)
                            .addGap(125, 125, 125))
    );
    PanelSzansLayout.setVerticalGroup(
            PanelSzansLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSzansLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(szanseLabel)
                            .addContainerGap(152, Short.MAX_VALUE))
    );

    add(panelSzans);
}








}
