import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class Detrasher extends JFrame{

    PanelMenu panelMenu;
    static int licznikMenu=2;

    public static String nick;
    PanelGry panelGry;
    OknoNicku oknoNicku;

    public Detrasher() {
        initUI();
        setLocationRelativeTo(null);
        panelGry.spadekStart();
        setFocusOnMenuOrGamePanel(panelGry,panelMenu);
    }

    public static void setNick(String nick) {
        Detrasher.nick = nick;
    }

    private void setFocusOnMenuOrGamePanel(PanelGry g, PanelMenu m){
        m.restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelGry.restartGry();
            }
        });
        m.menuPrzycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(licznikMenu%2==0)
                {
                    //m.menuPrzycisk.setForeground(Color.red);

                    m.MenuWersjaB();
                    m.repaint();

                    if(g.getCzyPausa()==false){g.pausa();}

                    m.menuPrzycisk.setFocusable(false);
                    licznikMenu++;
                   // if(2 >= licznikMenu) licznikMenu = 0;
                   /*
                    if (1 == licznikMenu)
                        licznikMenu = 0;
                    else
                        licznikMenu = 1;
                   */
                }
                else
                {
                    m.menuPrzycisk.setForeground(Color.BLACK);

                    m.MenuWersjaA();
                    m.repaint();

                    m.menuPrzycisk.setFocusable(false);
                    if(g.getCzyPausa()==false){g.pausa();}

                    licznikMenu++;
                }

            }
        });

    }

    private void initUI() {
        GridBagConstraints gridBagConstraints;
        panelMenu= new PanelMenu(this);
        panelGry = new PanelGry(this);
        oknoNicku=new OknoNicku(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Detrasher");
        setVisible(true);
        setMaximumSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(1000, 800));
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        getContentPane().add(panelGry, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = GridBagConstraints.RELATIVE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 2.0;
        getContentPane().add(panelMenu, gridBagConstraints);

        pack();

        panelGry.requestFocusInWindow();


    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {


                Detrasher gra = new Detrasher();




            }

        });
    }
}
