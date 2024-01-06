import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/** Klasa wyswietlajaca panel menu graficznego gry i umozliwiajaca sterowanie programem przez uzytkownika.*/

public class PanelMenu extends JPanel {
/**Komponent skladowy panelu menu, wyswietlane sa na nim w formie graficznej szanse uzytkownika.*/
    private PanelSzans panelSzans;
    /**Glowny przycisk panelu umozliwijacy zmiane wygladu i funkcjonalnosci menu. */
    private JButton menuButton;

    /** Etykieta ktora wyswietla tekst zalezny od liczby zdobytych w grze punktow. */
    private JLabel punktacjaLabel;

    /** Etykieta wyswietlajaca w formie tekstu liczbe szans, ktore pozostaly graczowi.*/
    private JLabel szanseLabel;

    /** Przycisk w panelu menu powodujacy wyswietlenie informacji o grze. */
    private JButton informacjaButton;

    /** Przycisk w panelu menu odpowiedzialny za zapisanie wyniku i zakonczenie gry.*/
    private JButton zakonczButton;

    /** Przycisk odpowiedzialny za wyswietlenie okna do zmiany nicku gracza w trakcie rozgrywki.*/
    private JButton zmienNickButton;

    /** Przycisk odpowiedzialny za resetowanie wyniku i ustawien gry do poczatkowych.*/
    private JButton restartButton;
    
   /** Konstruktor, ktory tworzy GUI oraz timer panelu menu, ktory odpowiedzialny jest za odswiezanie wyniku punktowego oraz szans w grze.*/
    public PanelMenu(){
        initUIMenu();
        int interwalMenu = 300;
        Timer timerMenu = new Timer(interwalMenu, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setPunktacja();
                setSzanse();

            }
        });
        timerMenu.start();

    }
/** Metoda zwraca obiekt restartButton bedacy atrybutem obiektu klasy PanelMenu.
 * @return restartButton*/
    public JButton getRestartButton() {
        return restartButton;
    }
    /** Metoda zwraca obiekt menuButton bedacy atrybutem obiektu klasy PanelMenu.
     * @return menuButton*/
    public JButton getMenuButton() {
        return menuButton;
    }
    /** Metoda zwraca obiekt zakonczButton bedacy atrybutem obiektu klasy PanelMenu.
     * @return zakonczButton*/
    public JButton getZakonczButton() {
        return zakonczButton;
    }
    /** Metoda zmienia tekst pola punktacjaLabel na zgodny z aktualnym dorobkiem punktowym uzytkownika.*/
    public void setPunktacja()
    {
        this.punktacjaLabel.setText(("Punkty: " + PanelGry.getDobrePrzyporzadkowanieLicznik()));
    }

    /** Tworzy nowe komponenty z klasy Swing, przypisuje je do odpowiednich atrybutow obiektu klasy PanelMenu.
     * Ustawia odpowiedni rozklad i wyglad panelu metoda setMenuAWyglad. Dodaje funkcjonalnosc przyciskom informacjaButton, zmienNickButton.*/
    public void initUIMenu()
    {
        menuButton = new JButton();
        punktacjaLabel = new JLabel();
        panelSzans = new PanelSzans();
        szanseLabel = new JLabel();
        informacjaButton = new JButton();
        zakonczButton = new JButton();
        restartButton = new JButton();
        zmienNickButton = new JButton();

        setMenuAWyglad(menuButton,punktacjaLabel,panelSzans,szanseLabel);
        informacjaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wyswietlInfoBox("Jak grać? \n\n"+"Steruj strzałkami klawiatury aby odpady trafiły do właściwych pojemników.\n\n" +
                        "Wznowisz grę ENTEREM, a zatrzymasz klawiszem P.\n\n" +
                        "Możesz zmienić nick pod jakim w pliku zostanie zapisany twój wynik na początku gry \n" +
                        "lub także w trakcie z poziomu menu.\n\nMiłej zabawy! ","O grze");
                informacjaButton.setFocusable(false);
            }

        });

        zmienNickButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              new OknoNicku();

            }
        });
    }

    /**Metoda odpowiada za zdefiniowanie odpowiedniego rozkladu komponentow w sposob zaprojektowany dla panelu menu w wersji A.
     *  Ustawia odpowiednio wlasciwosci tych komponentow.
     *  Przyjmuje:
     *  @param menuPrzycisk glowny przycisk zmieniajacy wersje menu i zatrzymujacy gre
     *  @param panelSzans panel odwzorowujacy sznase gracza
     *  @param punktacjaLabel etykieta wyswietlajaca pole klasy PanelGry dobrePrzyporzadkowaniaLicznik
     *  @param szanseLabel etykieta bedaca alternatywa dla graficznego wyswietlania szans */
    public void setMenuAWyglad(JButton menuPrzycisk,JLabel punktacjaLabel,JPanel panelSzans,JLabel szanseLabel)
    {
        setBackground(new Color(255, 153, 0));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setMinimumSize(new Dimension(1000, 100));
        setPreferredSize(new Dimension(1000, 100));
        setLayout(new GridLayout(1, 3, 40, 40));

        menuPrzycisk.setFont(new Font("Tw Cen MT", Font.PLAIN, 28));
        menuPrzycisk.setText("Menu");
        menuPrzycisk.setForeground(new Color(168, 52, 235));
        menuPrzycisk.setAlignmentY(20.0F);
        menuPrzycisk.setContentAreaFilled(false);
        menuPrzycisk.setHorizontalTextPosition(SwingConstants.CENTER);
        menuPrzycisk.setMaximumSize(new Dimension(75, 20));
        menuPrzycisk.setMinimumSize(new Dimension(75, 20));
        menuPrzycisk.setPreferredSize(new Dimension(75, 20));

        add(menuPrzycisk);

        punktacjaLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 28));
        punktacjaLabel.setForeground(new Color(168, 52, 235));
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

        szanseLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
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

    /** Metoda wyswietla MessageDialog z JOptionPane z odpowiednimi komponentami klasy Swing i tekstem przekazanym jako parametr.
     * Przyjmuje:
     * @param wiadomosc tekstowa tresc wiadomosci ktora zostanie wyswietlona na JTextArea
     * @param tytul tytul ktorym podpisana zostanie belka okna z wiadomoscia*/
    public void wyswietlInfoBox(String wiadomosc, String tytul)
    {
        JTextArea text=new JTextArea(wiadomosc);
        text.setFont(new Font("Tw Cen MT", Font.BOLD, 24));

        text.setForeground(new Color(168, 52, 235));
        text.setBackground(new Color(255, 153, 0, 179));
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text,tytul, JOptionPane.PLAIN_MESSAGE);

    }

    /** Metoda ta przeksztalca obiekt klasy PanelMenu z wyswietlajacego menu w wersji A na menu w wersji B.
     *  Od panelu odpina niepotrzebne poprzednie komponenty i dodaje inne w innym rozkladzie (pozostaje przycisk menuButton o
     *  zmienionych wlasciwosciach), tj. przycisk informacjaButton, zakonczButton, restartButton, zmienNickButton.*/
    public void konwertujNaMenuWersjaB()
    {
        remove(panelSzans);
        remove(szanseLabel);
        remove(punktacjaLabel);

        setBackground(new Color(255, 153, 0));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setMinimumSize(new Dimension(1000, 200));
        setPreferredSize(new Dimension(1000, 200));
        setLayout(new GridLayout(1, 3, 40, 40));

        menuButton.setBackground(new Color(255, 153, 0));
        menuButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        menuButton.setForeground(new Color(168, 52, 235));
        menuButton.setText("Powrót");
        menuButton.setAlignmentY(20.0F);
        menuButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        menuButton.setContentAreaFilled(false);
        menuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        menuButton.setMaximumSize(new Dimension(75, 20));
        menuButton.setMinimumSize(new Dimension(75, 20));
        menuButton.setPreferredSize(new Dimension(75, 20));
        add(menuButton);

        informacjaButton.setBackground(new Color(255, 153, 0));
        informacjaButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        informacjaButton.setForeground(new Color(168, 52, 235));
        informacjaButton.setText("O grze");
        informacjaButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(informacjaButton);

        zakonczButton.setBackground(new Color(255, 153, 0));
        zakonczButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        zakonczButton.setForeground(new Color(168, 52, 235));
        zakonczButton.setText("Zapisz i wyjdź");
        zakonczButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        add(zakonczButton);

        restartButton.setBackground(new Color(255, 153, 0));
        restartButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        restartButton.setText("Restart gry");
        restartButton.setForeground(new Color(168, 52, 235));
        restartButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(restartButton);

        zmienNickButton.setBackground(new Color(255, 153, 0));
        zmienNickButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
        zmienNickButton.setText("Zmień swój nick");
        zmienNickButton.setForeground(new Color(168, 52, 235));
        zmienNickButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(zmienNickButton);
    }

    /** Metoda wywoluje ponowne namalowanie panelu szans.*/
    public void setSzanse()
    {
        panelSzans.repaint();
    }

    /** Metoda ta przeksztalca obiekt klasy PanelMenu z wyswietlajacego menu w wersji B na menu w wersji A.
     *  Od panelu odpina niepotrzebne poprzednie komponenty i dodaje inne w innym rozkladzie,
     *  tj. panelSzans, punktacjaLabel, menuButton zmienia wlasciwosci.*/
    public void konwertujNaMenuWersjaA()
    {
        remove(zmienNickButton);
        remove(restartButton);
        remove(informacjaButton);
        remove(zakonczButton);

        setMenuAWyglad(menuButton,punktacjaLabel,panelSzans,szanseLabel);
    }
}
