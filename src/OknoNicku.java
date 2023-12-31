import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Klasa okna, w ktorym podczas gry mozna wpisac swoj nick.
 */
public class OknoNicku extends JFrame {
    /** Komponent do ktorego uzytkownik wprowadza tekst. */
    private JTextField NickTextField;
    /** Odczytany przez program nick uzytkownika.*/
    private String nick;

    /** Konstruktor obiektu klasy OknoNicku. Tworzy GUI.*/
    public OknoNicku()
    {
        initUI();
        this.setLocationRelativeTo(null);
    }

    /**
     * Metoda ta tworzy komponenty i odpowiednio je pozycjonuje w oknie, ustawia im odpowiednie wlasciowosci. Dodaje komponentowi klasy JButton
     * ActionListener.W przypadku wykonania na nim akcji, w polu nick obiektu OknoNicku zapisywana jest zawartosc NickTextField.
     * Jesli NickTextField bylo niewypelnione, ustawiana jest zdefiniowana lokalnie wartosc tego pola.
     * Metoda nastepnie ustawia klasie Detrasher nick na ten podany przez uzytkownika w tym obiekcie i zamyka okno.
     * Ustawia obiektowi klasy OknoNicku dziedziczacemu po JFrame odpowiednie wartosci wlasciwosci,tj.
     * DefaultCloseOperation, AlwaysOnTop, Undecorated, Visible, Background.
     */
    private void initUI() {

        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        //setLocationRelativeTo(null);
        setVisible(true);
        setBackground(new Color(102, 255, 102));

        JPanel glownyPanelNick = new JPanel();
        NickTextField = new JTextField();
        JButton zatwierdzButton = new JButton();
        JLabel podajNickLabel = new JLabel();

        glownyPanelNick.setBackground(new Color(255, 153, 51));

        NickTextField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        NickTextField.setHorizontalAlignment(JTextField.CENTER);

        zatwierdzButton.setText("Zatwierdź");
        zatwierdzButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nick=NickTextField.getText();
                if(nick.isEmpty()){nick="Bezimienny";}
                Detrasher.setNick(nick);
                setVisible(false);
                dispose();
            }
        });

        podajNickLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        podajNickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        podajNickLabel.setText("Podaj swój nick");

        GroupLayout GlownyPanelNickLayout = new GroupLayout(glownyPanelNick);
        glownyPanelNick.setLayout(GlownyPanelNickLayout);
        GlownyPanelNickLayout.setHorizontalGroup(
                GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                .addGroup(GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(podajNickLabel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(NickTextField, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GlownyPanelNickLayout.createSequentialGroup()
                                                .addGap(136, 136, 136)
                                                .addComponent(zatwierdzButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        GlownyPanelNickLayout.setVerticalGroup(
                GlownyPanelNickLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, GlownyPanelNickLayout.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addComponent(podajNickLabel)
                                .addGap(18, 18, 18)
                                .addComponent(NickTextField, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(zatwierdzButton)
                                .addGap(73, 73, 73))
        );

        getContentPane().add(glownyPanelNick, BorderLayout.CENTER);
        pack();
    }}
