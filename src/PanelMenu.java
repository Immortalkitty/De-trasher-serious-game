import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PanelMenu extends JPanel {

    private JPanel PanelSzans;
    public JButton menuPrzycisk;
    private JLabel punktacjaLabel;
    public JLabel szanseLabel;
    private Timer timerMenu;
    private int interwałMenu;


    public void setPunktacja()
    {
        this.punktacjaLabel.setText(("Points:" + PanelGry.dobrePrzyporzadkowanieLicznik));
    }

    private void initUIMenu(){
        menuPrzycisk = new JButton();
        punktacjaLabel = new JLabel();
        PanelSzans = new JPanel();
        szanseLabel = new JLabel();

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

        PanelSzans.setBackground(new Color(255, 153, 0));

        szanseLabel.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        szanseLabel.setText("Szanse: 3");
        szanseLabel.setVerticalAlignment(SwingConstants.TOP);
        szanseLabel.setFocusable(false);

        GroupLayout PanelSzansLayout = new GroupLayout(PanelSzans);
        PanelSzans.setLayout(PanelSzansLayout);
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

        add(PanelSzans);
    }
    public PanelMenu(){

        initUIMenu();
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
        this.szanseLabel.setText(("Szanse:" + PanelGry.szanse));
    }







}
