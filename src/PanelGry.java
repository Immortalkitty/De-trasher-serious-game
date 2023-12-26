import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
//cur od current
public class PanelGry extends JPanel {

    private int interwalGry;
    private Timer timer;
    private boolean czyPausa;
    public static int dobrePrzyporzadkowanieLicznik = 0;
    public Odpad curOdpad;
    public static int szanse=3;
    private BufferedImage smiecImage = null;
    private BufferedImage tloImage = null;
    private int poprzedniIndeksPrzyspieszenia=0;
    private int k;



    private void initUIPoleGry()
    {
        k=1;
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
                    case KeyEvent.VK_LEFT -> sprobujRuszyc( curOdpad.getX() - 1*25, curOdpad.getY());
                    case KeyEvent.VK_RIGHT -> sprobujRuszyc(curOdpad.getX()+1*25, curOdpad.getY());


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

        g.drawImage(smiecImage, curOdpad.getX(),curOdpad.getY(), null);

        if (k==0)
        {
            String s="Koniec Gry";
            g.setFont(new Font("serif", Font.BOLD, 60));
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

        curOdpad.setX(newX);
        curOdpad.setY(newY);
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

    private void koniecGry()
    {
        czyPausa=true;
        k=0;
        repaint();


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

        if (curOdpad.getY()<525)
        {
            curOdpad.setY(curOdpad.getY()+25); //spadanie
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


    private void wczytajNowyOdpad()
    {
        curOdpad = new Odpad();
        try
        {
            smiecImage = ImageIO.read(new File(curOdpad.getSmiecPath()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    private void sprawdzCzyDobryKosz(Odpad o)
    {
        switch(o.getX()/200) //dzielenie bez reszty przez szerokosc kosza celem weryfikacji w switchu do ktorego spadl odpad
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






