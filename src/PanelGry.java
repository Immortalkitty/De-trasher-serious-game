import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;

import static java.awt.Font.BOLD;

//cur od current
public class PanelGry extends JPanel {

    private int interwalGry;
    private Timer timer;
    private boolean czyPausa;
    public static int dobrePrzyporzadkowanieLicznik ;
    public Odpad curOdpad;
    public static int szanse;
    private BufferedImage smiecImage = null;
    private BufferedImage tloImage = null;
    private int poprzedniIndeksPrzyspieszenia=0;
    public int k;
    private String nick;
    private int wynik;



    private void initUIPoleGry()
    {
        k=1;
        szanse=3;
        dobrePrzyporzadkowanieLicznik=0;

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
    public PanelGry(Detrasher parent) {

        initUIPoleGry();
        czyPausa = true;
        interwalGry = 300;
        timer = new Timer(interwalGry,new CyklGry());
        timer.start();
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {

                int keycode = e.getKeyCode();

                switch (keycode) {

                    case KeyEvent.VK_P -> pausa();
                    case KeyEvent.VK_ENTER -> wznowienie();
                    case KeyEvent.VK_LEFT -> sprobujRuszyc( curOdpad.getLgX() - 1*25, curOdpad.getLgY());
                    case KeyEvent.VK_RIGHT -> sprobujRuszyc(curOdpad.getLgX()+1*25, curOdpad.getLgY());


                }
            }

        });

        try
        {
            tloImage = ImageIO.read(new File("src/Tło-detrasher.jpg"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

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
            //System.out.println(Detrasher.nick);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        rysuj(g,k);
    }

    private void rysuj(Graphics g,int k)
    {
        g.drawImage(tloImage, 0, 0, null);

        g.drawImage(smiecImage, curOdpad.getLgX(),curOdpad.getLgY(), null);

        if (k==0)
        {
            String s="Koniec Gry";
            g.setFont(new Font("serif", BOLD, 60));
            g.setColor(Color.RED);
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

        if (x < 0 || x >= 1000-25 || y < 0 || y >= 525)
        {
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

    public void tablicaWynikow(){
        Scanner odczytaj;
        PrintWriter wpisz;
        ArrayList<String> nicki = new ArrayList<String>();
        ArrayList<Integer> wyniki = new ArrayList<Integer>();
        File f = new File("Detrasher_wyniki.txt");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try
        {
            odczytaj = new Scanner(new FileReader("Detrasher_wyniki.txt"));

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

        nicki.add(Detrasher.nick);
        wyniki.add(dobrePrzyporzadkowanieLicznik);

        try{

            wpisz = new PrintWriter ("Detrasher_wyniki.txt");


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

        while(nicki.size()>0 && j<5)
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
        k=0;
        repaint();
        tablicaWynikow();
    }
    public void restartGry(){
        PanelGry.szanse=3;
        k=1;
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

        if ((curOdpad.getLgY()+curOdpad.cY)<550) //srodek ciezkosci obiektu przekorczy dana linie w y
        {
            curOdpad.setLgY(curOdpad.getLgY()+25); //spadanie
        }
        else
        {
            sprawdzCzyDobryKosz(curOdpad);

           if (dobrePrzyporzadkowanieLicznik>0 & dobrePrzyporzadkowanieLicznik%5==0 &(poprzedniIndeksPrzyspieszenia!=dobrePrzyporzadkowanieLicznik))//zapobiega przyspieszaniu wielokrotnemu jesli gracz nie zmieni liczby punktow bezposrednio po
            {
                interwalGry = interwalGry /2;
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
            curOdpad.cX = smiecImage.getWidth()/2;
            curOdpad.cY = smiecImage.getHeight()/2;

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    private void sprawdzCzyDobryKosz(Odpad o)
    {
        switch((o.getLgX()+o.cX)/200) //dzielenie bez reszty przez szerokosc kosza celem weryfikacji w switchu do ktorego spadl odpad
        {
            case 4:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.RESZTKOWY))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else szanse--;
            }
            break;
            case 3:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.SZKLO))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else szanse--;
            }
            break;

            case 2:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.PLASTIK))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else szanse--;
            }
            break;
            case 1:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.PAPIER))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else szanse--;
            }
            break;
            case 0:
            {
                if(o.getTyp().equals(Odpad.TypySmieci.BIO))
                {
                    dobrePrzyporzadkowanieLicznik++;
                }
                else szanse--;
            }
            break;
        }

    }
}






