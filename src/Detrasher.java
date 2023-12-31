import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Gra edukacyjna o segregacji odpadow. Klasa glowna cyklicznie tworzaca okna zawierajace panel gry oraz panel menu.
 * @author Katarzyna Szczerba
 */


public class Detrasher extends JFrame{
    /** Instancja klasy PanelMenu wyswietlana w tym obiekcie gry*/
    private PanelMenu panelMenu;

    /** Wysokosc okna gry w pikselach*/
    private final int wysGry=800;

    /** Szerokosc okna gry w pikselach*/
    private final int szerGry=1000;

    /** Atrybut okreslajacy, ktora z dwoch dostepnych wersji paska menu nalezy aktualnie wyswietlac*/
    private static int licznikMenu;

    /** Ostatnio podany przez gracza nick, ktory wraz z wynikiem punktowym nalezy zapisac w pliku zewnetrznym w przypadku zakonczenia gry.*/
    private static String nick;

    /** Instancja klasy PanelGry wyswietlana w tym obiekcie gry*/
    private PanelGry panelGry;

    /**
     * Glowny konstruktor klasy ustawia wartosci pol na domyslne, buduje GUI i umozliwia rozpoczecie graczowi gry.
     */
    public Detrasher()
    {
        initUI();
        licznikMenu=2;
        setLocationRelativeTo(null);
        panelGry.spadekStart();
        setFocusIWymienInformacjePanele(panelGry,panelMenu);
    }
    /** Getter statycznego pola klasy Detrasher- nick
     * @return nick*/
    public static String getNick()
    {
        return nick;
    }

    /**
     * Metoda ta ustawia podany obiekt klasy String jako nowa wartosc statycznego pola klasy glownej.
     * @param nick na ten String zostanie zmieniona wartosc pola nick klasy Detrasher
     */
    public static void setNick(String nick) {
        Detrasher.nick = nick;
    }

    /**
     * Metoda ta, uzywana przy tworzeniu obiektu Detrasher, przekazuje focus w oknie gry z przyciskow
     * (ktore maja priorytet i przy tworzeniu go przejmuja) na panel gry,
     * tak aby uzytkownik mogl sterowac odpowiednimi obiektami w grze. Ponadto tworzy ActionListenery,
     * do ktorych dzialania potrzebne byly instancje zarowno panela menu i panela gry.
     * @param g instancja klasy PanelGry
     * @param m instancja klasy PanelMenu
     */
    private void setFocusIWymienInformacjePanele(PanelGry g, PanelMenu m)
    {
        m.getRestartButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelGry.restartGry();
            }
        });
        m.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(g.getCzyPausa()==false){g.pausa();} //zatrzymanie gry przy kliknieciu menu

                if(licznikMenu%2==0)
                {
                    m.konwertujNaMenuWersjaB();
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
                    m.konwertujNaMenuWersjaA();
                }

                m.repaint();
                m.getMenuButton().setFocusable(false);//rozwiazanie problemu z brakiem mozliwosci sterowania w panelu gry,
                // tymczasowe zdjecie focusa z przycisku
                licznikMenu++;
            }
        });

        m.getZakonczButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!g.getCzyGraSkonczona()) g.TablicaWynikowZPliku("Detrasher_wyniki.txt",5); //jesli gra nie zostala formalnie ukonczona,
                // dotychczas uzyskany wynik jest zapisywany w pliku
                System.exit(0);
            }
        });

    }

    /**
     * Metoda ta tworzy obiekt okna, w ktore mozna wpisac nick,okno glowne zawierajace panel menu oraz panel gry.
     * Komponenty odpowiednio uklada, ustawia im odpowiednie wlasciowosci.
     */
    private void initUI() {
        panelMenu= new PanelMenu();
        panelGry = new PanelGry();
        OknoNicku oknoNicku = new OknoNicku();

        GridBagConstraints gridBagConstraints;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        setMaximumSize(new Dimension(szerGry, wysGry));
        setMinimumSize(new Dimension(szerGry, wysGry));
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

/** Metoda uruchamia gre. Dzieki niej kiedy spadajacy obiekt dotrze do konca planszy,
 *  caly cykl gry jest powtarzany az do zakonczenia gry.
 *  @param args nieuzywane*/
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
