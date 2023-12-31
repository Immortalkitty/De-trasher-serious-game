import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;

import static java.awt.Font.BOLD;

/* TODO
*   ladniejsze fonty, kolory, nowe smieci
*  wyswietlanie wersji menuzeby serio nie przekroczyli inata */

//cur od current
public class PanelGry extends JPanel {

    private int interwalGry;
    private Timer timer;
    private boolean czyPausa;
    private static int dobrePrzyporzadkowanieLicznik ;
    private Odpad curOdpad;
    private static int szanse;
    private BufferedImage smiecImage=null;
    private BufferedImage tloImage=null;
    private int poprzedniIndeksPrzyspieszenia;
    private boolean czyGraSkonczona;
    private int ruchPoziomy;
    private int liniaSprawdzenia;
    private int ruchPion;
    private int szerKosza;
    private int coIlePrzyspiesz;

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

                switch (keycode) {

                    case KeyEvent.VK_P -> pausa();
                    case KeyEvent.VK_ENTER -> wznowienie();
                    case KeyEvent.VK_LEFT -> sprobujRuszyc( curOdpad.getLgX() - 1*ruchPoziomy, curOdpad.getLgY());
                    case KeyEvent.VK_RIGHT -> sprobujRuszyc(curOdpad.getLgX()+1*ruchPoziomy, curOdpad.getLgY());


                }
            }

        });

        try
        {
            tloImage = ImageIO.read(new File("resources/Tło-detrasher.jpg"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static int getSzanse() {
        return szanse;
    }

    public static int getDobrePrzyporzadkowanieLicznik(){
        return dobrePrzyporzadkowanieLicznik;

    }

    public boolean getCzyGraSkonczona() {
        return czyGraSkonczona;
    }

    public void setCzyGraSkonczona(boolean czyGraSkonczona){
        this.czyGraSkonczona=czyGraSkonczona;
    }

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

    public void spadekStart()
    {
        wczytajNowyOdpad();
        timer.restart();
    }

   public void pausa()
   {
        if (czyPausa==false)
        {
            czyPausa=true;
            repaint();
        }

    }
    public boolean getCzyPausa()
    {
        if (czyPausa==true) return true;
        else return false;
    }
    public void wznowienie()
    {
        if (czyPausa==true)
        {
            czyPausa=false;
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        rysuj(g);
    }

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

    private class CyklGry implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            graWToku();
        }
    }

    public void wyswietlTablicaWynikow(String infoMessage, String titleBar)
    {
        JTextArea text=new JTextArea("Oto najwyższe z uzyskanych wyników! \n\n"+infoMessage);
        text.setFont(new Font("Segoe UI", BOLD, 18));
        text.setForeground(new Color(168, 52, 235));
        text.setEditable(false);
        JOptionPane.showMessageDialog(null, text,titleBar, JOptionPane.PLAIN_MESSAGE);
    }

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

    private void koniecGry()
    {
        czyPausa=true;
        setCzyGraSkonczona(true);
        repaint();
        TablicaWynikowZPliku("Detrasher_wyniki.txt",5);
    }
    public void restartGry(){
        PanelGry.szanse=3;
        setCzyGraSkonczona(false);
        PanelGry.dobrePrzyporzadkowanieLicznik=0;
        this.poprzedniIndeksPrzyspieszenia=0;
        this.setInterwalGry(300);
        timer.setDelay(interwalGry);

        spadekStart();
    }
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
    private void setInterwalGry(int nowyInterwal){
        this.interwalGry=nowyInterwal;
    }


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








