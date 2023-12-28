import java.util.HashMap;
import java.util.Random;

public class Odpad {

    private int lgX; //lewy gorny naroznik
    private int lgY;
    public int cX;//x centrum
    public int cY;
    public String nazwa;

    private TypySmieci typSmiecia;

    private int wylosowanyNumer;


    public Odpad(){
        this.lgX =500;
        this.lgY =0;
        Random losujSmiec= new Random();
        wylosowanyNumer= losujSmiec.nextInt(0,21);
        this.setTyp();
        this.setNazwa();

    }

    public enum TypySmieci {RESZTKOWY,PLASTIK,BIO,SZKLO,PAPIER }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa()
    {
        HashMap<String,String> nazwySmieciMapa;
        nazwySmieciMapa = new HashMap<String,String>();
        nazwySmieciMapa.put("src/Ości.png","Ości");
        nazwySmieciMapa.put("src/ButelkaPET.png","Plastikowa butelka");
        nazwySmieciMapa.put("src/ButelkaPoMleku.png","Butelka po mleku");
        nazwySmieciMapa.put("src/ButelkaPoPranie.png","Butelka po płynie do prania");
        nazwySmieciMapa.put("src/Gazety.png","Gazety");
        nazwySmieciMapa.put("src/Jabłko.png","Resztki jabłka");
        nazwySmieciMapa.put("src/Karton.png","Karton");
        nazwySmieciMapa.put("src/KartonPoSoku.png","Karton po soku");
        nazwySmieciMapa.put("src/KubekPoNapojuNaWynos.png","Kubek po napoju na wynos");
        nazwySmieciMapa.put("src/MetalowaPuszka1.png","Metalowa puszka");
        nazwySmieciMapa.put("src/PapierowaTorba.png","Papierowa torba");
        nazwySmieciMapa.put("src/PękniętyKubek.png","Pęknięta ceramika");
        nazwySmieciMapa.put("src/PuszkaPoNapoju.png","Puszka po napoju");
        nazwySmieciMapa.put("src/PuszkaPoSprayu.png","Puszka po sprayu");
        nazwySmieciMapa.put("src/SkorupkiJajka.png","Skorupki jajek");
        nazwySmieciMapa.put("src/SkórkaBanana.png","Skórka banana");
        nazwySmieciMapa.put("src/Słoik.png","Słoik");
        nazwySmieciMapa.put("src/SzklanaButelka1.png","Szklana butelka");
        nazwySmieciMapa.put("src/SzklanaButelka2.png","Szklana butelka");
        nazwySmieciMapa.put("src/TłustyKartonPoPizzy.png","Karton po pizzy");
        nazwySmieciMapa.put("src/ZbitaButelka.png","Zbita szklana butelka");
        nazwySmieciMapa.put("src/ZgniecionaKartka.png","Zgnieciona kartka papieru");

        this.nazwa=nazwySmieciMapa.get(this.getSmiecPath());
    }

    public int getLgX() { return lgX; }
    public int getLgY() { return lgY; }

    public void setLgX(int x)  { this.lgX =x; }
    public void setLgY(int y)  { this.lgY =y; }

    public TypySmieci getTyp()
    {
        return this.typSmiecia;
    }

    public String getPojemnik()
    {
        if (getTyp().equals(TypySmieci.PLASTIK))
            {
                return "żółtego";
            }

        if (getTyp().equals(TypySmieci.BIO))
            {
                return "bio";
            }

        if (getTyp().equals(TypySmieci.PAPIER))
            {
                return "niebieskiego";
            }

        if (getTyp().equals(TypySmieci.SZKLO))
            {
                return "zielonego";
            }

        if (getTyp().equals(TypySmieci.RESZTKOWY))
            {
                return "czarnego";
            }

        else return null;
    }
    private void setTyp()
    {
        HashMap<String,TypySmieci> nazwyTypowMapa = new HashMap<String,TypySmieci>();
        nazwyTypowMapa.put("src/Ości.png",TypySmieci.BIO);
        nazwyTypowMapa.put("src/ButelkaPET.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/ButelkaPoMleku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/ButelkaPoPranie.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/Gazety.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("src/Jabłko.png",TypySmieci.BIO);
        nazwyTypowMapa.put("src/Karton.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("src/KartonPoSoku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/KubekPoNapojuNaWynos.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("src/MetalowaPuszka1.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/PapierowaTorba.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("src/PękniętyKubek.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("src/PuszkaPoNapoju.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("src/PuszkaPoSprayu.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("src/SkorupkiJajka.png",TypySmieci.BIO);
        nazwyTypowMapa.put("src/SkórkaBanana.png",TypySmieci.BIO);
        nazwyTypowMapa.put("src/Słoik.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("src/SzklanaButelka1.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("src/SzklanaButelka2.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("src/TłustyKartonPoPizzy.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("src/ZbitaButelka.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("src/ZgniecionaKartka.png",TypySmieci.PAPIER);

        this.typSmiecia =nazwyTypowMapa.get(this.getSmiecPath());
    }

    public String getSmiecPath(){

        String smiecImagePath;
        switch(this.wylosowanyNumer)
        {
            case 0:
                smiecImagePath="src/Ości.png";
                break;
            case 1:
                smiecImagePath="src/ButelkaPET.png";
                break;
            case 2:
                smiecImagePath="src/ButelkaPoMleku.png";
                break;
            case 3:
                smiecImagePath="src/ButelkaPoPranie.png";
                break;
            case 4:
                smiecImagePath="src/Gazety.png";
                break;
            case 5:
                smiecImagePath="src/Jabłko.png";
                break;
            case 6:
                smiecImagePath="src/Karton.png";
                break;
            case 7:
                smiecImagePath="src/KartonPoSoku.png";
                break;
            case 8:
                smiecImagePath="src/KubekPoNapojuNaWynos.png";
                break;
            case 9:
                smiecImagePath="src/MetalowaPuszka1.png";
                break;
            case 10:
                smiecImagePath="src/PapierowaTorba.png";
                break;
            case 11:
                smiecImagePath="src/PękniętyKubek.png";
                break;
            case 12:
                smiecImagePath="src/PuszkaPoNapoju.png";
                break;
            case 13:
                smiecImagePath="src/PuszkaPoSprayu.png";
                break;
            case 14:
                smiecImagePath="src/SkorupkiJajka.png";
                break;
            case 15:
                smiecImagePath="src/SkórkaBanana.png";
                break;
            case 16:
                smiecImagePath="src/Słoik.png";
                break;
            case 17:
                smiecImagePath="src/SzklanaButelka1.png";
                break;
            case 18:
                smiecImagePath="src/SzklanaButelka2.png";
                break;
            case 19:
                smiecImagePath="src/TłustyKartonPoPizzy.png";
                break;
            case 20:
                smiecImagePath="src/ZbitaButelka.png";
                break;
            case 21:
                smiecImagePath="src/ZgniecionaKartka.png";
                break;
            default:
                smiecImagePath="";
                break;

        }
        return smiecImagePath;
    }





}
