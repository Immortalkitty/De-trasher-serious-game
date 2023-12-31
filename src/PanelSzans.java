import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**Klasa wyswietla odpowiednia liczbe graficznych szans w zaleznosci od stanu klasy PanelGry. */
public class PanelSzans extends JPanel
{
    /**Pole przetrzymujace obraz ktory ma byc wyswietlany na panelu.*/
    private BufferedImage GwiazdkaImage;

    /**Konstruktor klasy PanelSzans, wczytuje plik z podanej sciezki obrazka do pola GwiazdkaImage. */
    public PanelSzans()
    {
        GwiazdkaImage = null;
        try
        {
            GwiazdkaImage = ImageIO.read(new File("resources/Gwiazdka.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
    /**
     * Nadpisuje metodę odpowiedzialną za odrysowanie panelu - własne wypełnienie
     * pola graficznego.
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        rysujGwiazdki(g);
    }

    /**Metoda w zaleznosci od wartosci pola szanse klasy PanelGry rysuje 0,1,2 lub 3 obrazki
     * z pola GwiazdkaImage obiektu obok siebie w poziomie. */
    private void rysujGwiazdki(Graphics g) {

        switch(PanelGry.getSzanse()) {
            case 3:
            {
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2, (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2 + GwiazdkaImage.getWidth(), (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2 + 2 * GwiazdkaImage.getWidth(), (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
            }
            break;
            case 2:
            {
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2, (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2 + GwiazdkaImage.getWidth(), (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
            }
            break;
            case 1:
            {
                g.drawImage(GwiazdkaImage, (this.getWidth() - 3 * GwiazdkaImage.getWidth()) / 2, (this.getHeight() - GwiazdkaImage.getHeight()) / 2, null);
            }

        }
    }
}
