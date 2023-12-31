import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;
import static java.awt.Font.BOLD;

/**Klasa odpowiedzialna za wyswietlanie grywalnego pola. Daje mozliwosc sterowania spadajacym odpadem, zdobywanie punktow, tracenie szans.*/
public class PanelGry extends JPanel {

    /**Opoznienie timera taktujacego cykle odswiezania gry. */
    private int interwalGry;

    /**Timer taktujacy spadanie obiektu, odmalowywanie panelu gry.*/
    private Timer timer;

    /**Okresla czy gra jest w stanie pauzy.*/
    private boolean czyPausa;

    /**Liczy dobre przyporzadkowania odpadow do kosza. Stanowi wynik punktowy gracza.*/
    private static int dobrePrzyporzadkowanieLicznik ;

    /**Przechowuje obiekt ktory aktualnie znajduje sie w panelu gry, mozna nim sterowac, spada.*/
    private Odpad curOdpad;

    /**Pole liczace pomylki gracza w przyporzadkowywaniu odpadow do odpowiednich pojemnikow.*/
    private static int szanse;

    /**Pole do przechowywania obrazu aktualnego odpadu wystepujacego w panelu gry.*/
    private BufferedImage smiecImage=null;

    /**Pole do przechowywania obrazu tla panelu gry.*/
    private BufferedImage tloImage=null;

    /**Jaki byl poprzedni stan pola dobrePrzyporzadkowanieLicznik, w ktorym dokonano przyspieszenia gry.
     *  Zapobiega wielokrotnemu przyspieszeniu gry w przypadku gdy gracz nie zdobedzie punktu,a  straci szanse.*/
    private int poprzedniIndeksPrzyspieszenia;

    /**Pole okreslajace czy gra zostala zakonczona.*/
    private boolean czyGraSkonczona;

    /**Ile pikseli odpowiada jednemu ruchowi w poziomie*/
    private int ruchPoziomy;

    /**Jaka jest wspolrzedna y linii w pikselach liczonych od lewego gornego rogu panelu, po ktorej przekorczeniu nastepuje sprawdzenie dopasowania odpadu do odpowiedniego kosza.*/
    private int liniaSprawdzenia;

    /**Ile pikseli odpowiada jednemu ruchowi w pionie.*/
    private int ruchPion;

    /**Szerokosc pozioma kosza w piskselach.*/
    private int szerKosza;

   /**Co ile poprawnych przyporzadkowan odpadow gra ma zostac przyspieszona.*/
    private int coIlePrzyspiesz;

    /**Konstruktor obiektu klasy PanelGry. Definiuje wartosc pol szanse, dobrePrzyporzadkowanieLicznik, poprzedniIndeksPrzyspieszenia,
     *  ruchPoziomy, liniaSprawdzenia, ruchPion, szerKosza, coIlePrzyspiesz, czyPausa, interwalGry. Wykonuje inicjalizacje komponentow panelu,
     *  ustawia stan czyGraSkonczona na false, tworzy nowy timer, ktorego listenerem jest obiekt wewnetrznej klasy pomocnicznej CyklGry,
     *  a opoznieniem wartosc pola interwalGry. Dodaje panelowi sluchacz przyciskow klawiatury. Te wykryte odpowiednio wydarzenia wznawiaja,
     *  zatrzymuja gre lub wykonuja probe poruszenia spadajacego obiektu w prawo lub lewo. Wczytuje obrazek tla gry do pola tloImage.  */
    public PanelGry() {

        szanse=3;
        dobrePrzyporzadkowanieLicznik=0;
        poprzedniIndeksPrzyspieszenia=0;
        ruchPoziomy=25;
        liniaSprawdzenia=550;
        ruchPion=25;
        szerKosza=200;
        coIlePrzyspiesz=5;

        czyPausa = true;
        interwalGry = 300;

        initUIPoleGry();
        setCzyGraSkonczona(false);

        timer = new Timer(interwalGry,new CyklGry());
        timer.start();
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {

                int keycode = e.getKeyCode();

                switch (keycode)
                {
                    case KeyEvent.VK_P -> pausa();
                    case KeyEvent.VK_ENTER -> wznowienie();
                    case KeyEvent.VK_LEFT -> sprobujRuszyc( curOdpad.getLgX() - 1*ruchPoziomy, curOdpad.getLgY());
                    case KeyEvent.VK_RIGHT -> sprobujRuszyc(curOdpad.getLgX()+1*ruchPoziomy, curOdpad.getLgY());
                }
            }

        });

        try
        {
            tloImage = ImageIO.read(new File("res/Tło-detrasher.jpg"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /** @return wartosc statycznego pola szanse*/
    public static int getSzanse() {
        return szanse;
    }

    /** @return wartosc statycznego pola dobrePrzyporzadkowanieLicznik*/
    public static int getDobrePrzyporzadkowanieLicznik(){
        return dobrePrzyporzadkowanieLicznik;

    }

    /** @return wartosc pola czyGraSkonczona*/
    public boolean getCzyGraSkonczona() {
        return czyGraSkonczona;
    }

    /**Metoda ustawia wartosc pola czyGraSkonczona na zadana.
     * @param czyGraSkonczona zadany stan */
    public void setCzyGraSkonczona(boolean czyGraSkonczona){
        this.czyGraSkonczona=czyGraSkonczona;
    }

    /**Metoda ustawia odpowiedni wyglad, rozklad panelu gry. */
    private void initUIPoleGry()
    {
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setMaximumSize(new Dimension(1000, 700));
        setMinimumSize(new Dimension(1000, 700));
        setPreferredSize(new Dimension(1000, 700));
        setFocusable(true);
        GroupLayout PanelGryLayout = new GroupLayout(this);
        setLayout(PanelGryLayout);
        PanelGryLayout.setHorizontalGroup(
                PanelGryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 998, Short.MAX_VALUE)
        );
        PanelGryLayout.setVerticalGroup(
                PanelGryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 598, Short.MAX_VALUE)
        );


    }

    /**Metoda wczytuje nowy obiekt odpadu, restartuje timer dyktujacy predkosc spadku obiektow.*/
    public void spadekStart()
    {
        wczytajNowyOdpad();
        timer.restart();
    }

  /**Metoda zatrzymujaca gre, jesli nie jest w stanie zatrzymania. Odmalowuje panel. */
   public void pausa()
   {
        if (!getCzyPausa())
        {
            czyPausa=true;
            repaint();
        }

    }
    /**Metoda sprawdzajaca czy gra jest w stanie pauzy.
     * @return true jesli pole czyPausa jest true, false w przeciwnym wypadku.*/
    public boolean getCzyPausa()
    {
        if (czyPausa==true) return true;
        else return false;
    }
    /**Metoda wywolujaca wznowienie gry. Jesli byla w stanie pauzy, ustawia pole czyPausa=false i odmalowuje panel gry.*/
    public void wznowienie()
    {
        if (getCzyPausa())
        {
            czyPausa=false;
            repaint();
        }
    }
    /**
     * Nadpisuje metode odpowiedzialna za odrysowanie panelu - wlasne wypelnienie
     * pola graficznego.
     * @param g obiekt graficzny
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        rysuj(g);
    }
/**Metoda rysujaca tlo panelu gry, aktualny obrazek obiektu curOdpad w odpowiednim jego polozeniu.
 * W przypadku konca gry, rysuje odpowiedni tekst na panelu.
 * W przypadku pauzy, rysuje odpowiedni tekst na panelu.
 * @param g obiekt graficzny*/
    private void rysuj(Graphics g)
    {
        g.drawImage(tloImage, 0, 0, null);

        g.drawImage(smiecImage, curOdpad.getLgX(),curOdpad.getLgY(), null);

        if (getCzyGraSkonczona())
        {
            String s="Koniec Gry";
            g.setFont(new Font("serif", BOLD, 60));
            g.setColor(Color.RED);
            g.drawString(s, getWidth() / 2 - g.getFontMetrics().stringWidth(s) / 2,
                    getHeight() / 2 - g.getFontMetrics().getHeight() / 2);
        }
        if(czyPausa&(!getCzyGraSkonczona()))
        {
            String s="Pauza";
            g.setFont(new Font("serif", BOLD, 60));
            g.setColor(new Color(53, 128, 60, 254));
            g.drawString(s, getWidth() / 2 - g.getFontMetrics().stringWidth(s) / 2,
                    getHeight() / 2 - g.getFontMetrics().getHeight() / 2);
        }


    }
/**Metoda zmieniajaca polozenie obiektu curOdpad w przypadku gdy nie przekracza on dozwolonych barier planszy,
 *  tj. prawego, lewego kranca okna i linii sprawdzenia.
 *  Przyjmuje:
 *  @param newY wspolrzedna y, jaka jesli to mozliwe ma byc ustawiona jako wspolrzedna lewego gornego naroznika obrazka obiektu odpadku.
 *  @param newX wspolrzedna x, jaka jesli to mozliwe ma byc ustawiona jako wspolrzedna lewego gornego naroznika obrazka obiektu odpadku
 *  @return true jesli udalo sie ustawic zadane wspolrzedne,
 *  false jesli nie ustawiono zadanych wspolrzednych a jakies inne zdefiniownae wewnatrz warunkow metody*/
    public boolean sprobujRuszyc(int newX, int newY)
    {
        if (czyPausa==true)
        {
            return false;
        }
        int x = newX;
        int y = newY;

        if(x <= 0)
        {
            curOdpad.setLgX(0);
            return false;
        }
        if(x+ curOdpad.getcX()*2 >= 1000)
        {
            curOdpad.setLgX(1000-(curOdpad.getcX()*2));
            return false;
        }

        if(y <= 0)
        {
            return false;
        }

        if(y+curOdpad.getcY() >= (liniaSprawdzenia))
        {
            curOdpad.setLgY(liniaSprawdzenia- curOdpad.getcY());
            return false;
        }

        curOdpad.setLgX(newX);
        curOdpad.setLgY(newY);
        repaint();
        return true;
    }

/** Klasa pomocnicza sluzaca do implementacji petli gry. Wykonuje akcje graWToku().*/
    private class CyklGry implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            graWToku();
        }
    }
    /**Metoda wyswietla zcustomizowany OptionDialog z JOptionPane, ktory zawiera JTextArea, zaprojektowany do wyswietlania tablicy najwyzszych wynikow.
     * Przyjmuje:
     * @param info tekst ktory zostanie wyswietlony w JTextArea oprocz predefiniowanego tekstu.
     * @param tytul tytul na belce komunikatu
     * .*/
    public void wyswietlTablicaWynikow(String info, String tytul)
    {
        JTextArea text=new JTextArea("Oto najwyższe z uzyskanych wyników! \n\n"+info);
        text.setFont(new Font("Segoe UI", BOLD, 18));
        text.setForeground(new Color(168, 52, 235));
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text,tytul, JOptionPane.PLAIN_MESSAGE);
    }

    /**Metoda wyswietla zcustomizowany OptionDialog z JOptionPane, wskazowke o zadanej tekstowej tresci i tytule.
     *  Definiuje opcje wyboru dostepne w komunikacie. Przyjmuje:
     * @param info tekst ktory zostanie wyswietlony w JTextArea
     * @param tytul tytul na belce komunikatu
     * @return a numer wybranej w oknie komunikatu opcji.*/
    public int wyswietlWskazowke(String info, String tytul)
    {
        JTextArea text=new JTextArea(info);
        text.setFont(new Font("Segoe UI", BOLD, 18));
        text.setForeground(new Color(52, 144, 235));
        text.setEditable(false);
        String[] opcje = { "OK, gramy dalej", "daj mi chwilę" };
        int a=JOptionPane.showOptionDialog(null, text, tytul,0, -1,null, opcje,opcje[0]);
        return a;

    }

    /** Metoda ta umozliwia odczytanie zawartosci pliku w formacie nazwa wlasna (bez bialych znakow) + tabulator + liczba calkowita
     *  i wyswietlenie wybranej liczby miejsc w formie tablicy wynikow. Metoda w przypadku rownych wynikow wyswietli kilka pozycji
     *  pod tym samym indeksem miejsca. W przypadku gdy plik o zadanej nazwie nie zostanie znaleziony, metoda utworzy go.
     *  Po tym etapie dotychczasowa zawartosc pliku w niezmienionej kolejnosci, ale z dodanymi danymi o aktualnej rozgrywce,
     *  tj. aktualnej wartosci pola klasy Detrasher nick oraz dobrePrzyporzadkowanieLicznik zostanie wpisana do pliku.
     *  Metoda tworzy okno za pomoca metody wyswietlTablicaWynikow().
     *  Przyjmuje
     *  @param nazwaPliku pelna sciezka do pliku
     *  @param ileMiejsc z ilu miejsc ma skladac sie wyswietlana tablica najwyzszych wynikow.*/
    public void TablicaWynikowZPliku(String nazwaPliku, int ileMiejsc){
        Scanner odczytaj;
        PrintWriter wpisz;
        ArrayList<String> nicki = new ArrayList<String>();
        ArrayList<Integer> wyniki = new ArrayList<Integer>();
        File f = new File(nazwaPliku);

        if(!f.exists())
        {
            try
            {
                f.createNewFile();
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        try
        {
            odczytaj = new Scanner(new FileReader(nazwaPliku));

            while (odczytaj.hasNext ())
            {
                nicki.add(odczytaj.next());
                wyniki.add(odczytaj.nextInt ());
            }

        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe+": Nie znaleziono Pliku!");
        }
        catch (InputMismatchException ime)
        {
            System.out.println(ime+": Dane w złym formacie!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        nicki.add(Detrasher.getNick());
        wyniki.add(dobrePrzyporzadkowanieLicznik);

        try{

            wpisz = new PrintWriter (nazwaPliku);


            for(int i=0; i<nicki.size(); i++)
            {
                wpisz.print(nicki.get(i) + "\t");
                wpisz.println(wyniki.get(i));


            }
            wpisz.close ();
        }

        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe+": Nie znaleziono pliku!");
        }
        catch (InputMismatchException ime)
        {
            System.out.println(ime+": Zły format danych!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        String informacjaWyniki=" ";
        int najwyzszy;
        int j=0;

        while(nicki.size()>0 && j<ileMiejsc)
        {
            ArrayList<Integer> indeksyNajwyzszych = new ArrayList<Integer>();
            najwyzszy = Collections.max(wyniki);

            int i = 0;
            for (int w : wyniki)
            {
                if (w == najwyzszy)
                {
                    indeksyNajwyzszych.add(i);
                }
                i++;
            }

            for (int ind : indeksyNajwyzszych)
            {
                informacjaWyniki = informacjaWyniki + (j+1)+ ". "+ nicki.get(ind) + "- " + wyniki.get(ind) + "\n ";

            }

            indeksyNajwyzszych.sort(Comparator.reverseOrder());
            for (int ind : indeksyNajwyzszych)
            {
                wyniki.remove(ind);
                nicki.remove(ind);

            }
            j++;

        }

        this.wyswietlTablicaWynikow(informacjaWyniki,"Gratulacje!");
    }
/**Metoda wykonuje sekwencje akcji wlasciwych dla konca gry. Zatrzymuje ja, wykonuje setCzyGraSkonczona(true), odmalowuje panel,
 *  zapisuje wyniki punktowe do pliku i wyswietla tablice najwyzszych wynikow. */
    private void koniecGry()
    {
        czyPausa=true;
        setCzyGraSkonczona(true);
        repaint();
        TablicaWynikowZPliku("Detrasher_wyniki.txt",5);
    }
    /**Metoda ustawiajaca odpowiednie wartosci polom obiektu klasy PanelGry,
     *  tak aby zgadzaly sie z ich stanem tuz po uruchomieniu programu. Wykonuje spadekStart(). */
    public void restartGry(){
        PanelGry.szanse=3;
        setCzyGraSkonczona(false);
        PanelGry.dobrePrzyporzadkowanieLicznik=0;
        this.poprzedniIndeksPrzyspieszenia=0;
        this.setInterwalGry(300);
        timer.setDelay(interwalGry);

        spadekStart();
    }
    /**Metoda wykonujaca updateGry() i ponowne namalowanie panelu w przypadku gdy pole czyPausa jest falszem.
     *  Jesli gra jest zatrzymana jedynie odmalowuje panel. Dokonuje sprawdzenia czy gracz stracil wszystkie szanse, jesli tak wykonuje koniecGry().*/
    private void graWToku()
    {
       if (czyPausa==false)
       {
           if(szanse!=0)
           {
               updateGry();
               repaint();
           }
           else koniecGry();
       }
       else
       {
           repaint();
       }
    }
/**Metoda odpowiedzialna za postep gry. Porusza obiektem curOdpad w dol w pionie.
 *  W przypadku dotarcia obiektu do linii sprawdzenia przyporzadkowania odpadu wykonuje metode sprawdzCzyDobryKosz(curOdpad).
 *  Realizuje przyspieszenie aktualizowania planszy zwiekszajc wartosc pola interwalGry w okreslonych warunkach.*/
    private void updateGry()
    {
        if ((curOdpad.getLgY()+curOdpad.getcY())<liniaSprawdzenia) //srodek ciezkosci obiektu przekorczy dana linie w y
        {
            curOdpad.setLgY(curOdpad.getLgY()+ruchPion); //spadanie
        }
        else
        {
            sprawdzCzyDobryKosz(curOdpad);

           if (dobrePrzyporzadkowanieLicznik>0 & dobrePrzyporzadkowanieLicznik%coIlePrzyspiesz==0
                   &(poprzedniIndeksPrzyspieszenia!=dobrePrzyporzadkowanieLicznik))//zapobiega przyspieszaniu wielokrotnemu jesli gracz nie zmieni liczby punktow bezposrednio po
            {
                if (interwalGry>100) interwalGry = interwalGry-50;
                poprzedniIndeksPrzyspieszenia=dobrePrzyporzadkowanieLicznik;
                timer.setDelay(interwalGry);
            }
            spadekStart();
        }

    }
    /**Metoda zmienia wartosc pola interwalGry obiektu. Przyjmuje:
     * @param nowyInterwal wartosc ktora ma zostac wpisana do pola obiektu klasy PanelGry*/
    private void setInterwalGry(int nowyInterwal)
    {
        this.interwalGry=nowyInterwal;
    }

/**Metoda tworzy nowy obiekt klasy Odpad i przypisuje go do pola obiektu PanelGry curOdpad. Ustawia jego polozenie na planszy, wczytuje obrazek.*/
    private void wczytajNowyOdpad()
    {
        curOdpad = new Odpad();
        try
        {
            smiecImage = ImageIO.read(new File(curOdpad.getSmiecPath()));
            curOdpad.setcX(smiecImage.getWidth()/2);
            curOdpad.setcY(smiecImage.getHeight()/2);
            curOdpad.setLgX(500-curOdpad.getcX());

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

/**Metoda dokonuje sprawdzenia czy obiekt klasy Odpad ma odpowiednie polozenie na planszy w poziomie po przekoczeniu lini sprawdzania w pionie.
 *  Jesli tak, inkrementowany jest dobrePrzyporzadkowanieLicznik, w przeciwnym razie wykonywana jest metoda zleDopasowano(Odpad o).
 *  Przyjmuje:
 *  @param o obiekt ktorego polozenie, typ jest sprawdzany*/
    private void sprawdzCzyDobryKosz(Odpad o)
    {
        switch((o.getLgX()+o.getcX())/szerKosza)
        {
            case 4:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.RESZTKOWY))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else
                {
                   zleDopasowano(o);
                }
            }
            break;

            case 3:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.SZKLO))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else
                {
                    zleDopasowano(o);
                }
            }
            break;

            case 2:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.PLASTIK))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else
                {
                    zleDopasowano(o);
                }
            }
            break;

            case 1:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.PAPIER))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else
                {
                    zleDopasowano(o);
                }
            }
            break;

            case 0:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.BIO))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else
                {
                   zleDopasowano(o);
                }
            }
            break;
        }

    }
/** Metoda wykonujaca sekwencje akcji w przypadku gdy odpad po dotarciu do lini sprawdzenia dopasowany zostal nieprawidlowo.
 *  Zmniejsza szanse klasy PanelGry, zatrzymuje gre, wyswietla wskazowke gdzie obiekt powinien trafic.
 *  W zaleznosci od wyboru przycisku w komunikacie wznawia gre lub pozostawia ja w stanie pauzy.
 *  Przyjmuje:
 *  @param o zalezy od niego tresc wyswietlonej wskazowki*/
    public void zleDopasowano(Odpad o)
    {
        int i;
        szanse--;
        pausa();
        i=wyswietlWskazowke("\n"+o.getNazwa()+" idzie do "+o.getPojemnikSlowo()+" pojemnika.\n","Pomyłka!");
        if (i==0)
        {
            wznowienie();
        }
    }
}








