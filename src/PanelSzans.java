import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelSzans extends JPanel {
    private BufferedImage GwiazdkaImage = null;
    public PanelSzans(){
        try
        {
            GwiazdkaImage = ImageIO.read(new File("src/Gwiazdka.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }




    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        rysujGwiazdki(g);
    }

    private void rysujGwiazdki(Graphics g) {

        switch(PanelGry.szanse) {
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
