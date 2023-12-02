import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Detrasher extends JFrame{

    PanelMenu panelMenu;
    PanelGry panelGry;
    public Detrasher() {
        initUI();
        panelGry.spadekStart();
        setFocusOnMenuOrGamePanel(panelGry,panelMenu);
    }

    private void setFocusOnMenuOrGamePanel(PanelGry g,PanelMenu m){
        m.menuPrzycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(m.menuPrzycisk.getForeground()==Color.black)
                {
                    m.menuPrzycisk.setForeground(Color.red);
                    if(g.getCzyPausa()==false){g.pausa();}

                    m.menuPrzycisk.setFocusable(false);
                }
                else
                {
                    m.menuPrzycisk.setForeground(Color.black);
                    m.menuPrzycisk.setFocusable(false);
                    if(g.getCzyPausa()==false){g.pausa();}
                }

            }
        });
    }

    private void initUI() {
        GridBagConstraints gridBagConstraints;
        panelMenu= new PanelMenu();
        panelGry = new PanelGry(this);

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
