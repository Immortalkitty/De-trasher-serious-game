import java.util.Random;

public class Odpad {

    private int posX;
    private int posY;
    private TypySmieci TypSmiecia;

    private int wylosowanyNumer;

     TypySmieci[] tablicaTypowSmieciaObrazkow= {TypySmieci.RESZTKOWY,TypySmieci.PLASTIK,TypySmieci.PLASTIK,
             TypySmieci.PLASTIK,TypySmieci.PLASTIK,TypySmieci.PAPIER,
             TypySmieci.PLASTIK,TypySmieci.PAPIER,TypySmieci.RESZTKOWY };

    public Odpad(){
        this.posX=500;
        this.posY=0;
        Random losujSmiec= new Random();
        wylosowanyNumer= losujSmiec.nextInt(1,9);
        this.setTyp(wylosowanyNumer);
    }

    public enum TypySmieci {RESZTKOWY,PLASTIK,BIO,SZKLO,PAPIER }

    public int getX() { return posX; }
    public int getY() { return posY; }

    public void setX(int x)  { this.posX=x; }
    public void setY(int y)  { this.posY=y; }

    public TypySmieci getTyp()
    {
        return this.TypSmiecia;
    }
    private void setTyp(int i)
    {
        this.TypSmiecia=tablicaTypowSmieciaObrazkow[i];
    }

    public String getSmiecPath(){
        String smiecImagePath;
        switch(this.wylosowanyNumer){

            case 1:
                smiecImagePath="src/ButelkaPET.png";
                break;
            case 2:
                smiecImagePath="src/PuszkaPoNapoju.png";
                break;
            case 3:
                smiecImagePath="src/ButelkaPoMleku.png";
                break;
            case 4:
                smiecImagePath="src/ButelkaPoPranie.png";
                break;
            case 5:
                smiecImagePath="src/Gazety.png";
                break;
            case 6:
                smiecImagePath="src/MetalowaPuszka2.png";
                break;
            case 7:
                smiecImagePath="src/PapierowaTorba.png";
                break;
            case 8:
                smiecImagePath="src/PuszkaPoSprayu.png";
                break;
            case 0:
                smiecImagePath="src/PękniętyKubek.png";
                break;
            default:
                smiecImagePath="";
                break;

        }
        return smiecImagePath;
    }





}
