import java.util.HashMap;
import java.util.Random;

/**
 * Klasa modelujaca spadajacy odpad w grze.
 */
public class Odpad
{
    /** Wspolrzedna x lewego gornego naroznika obrazka odpadu w przestrzeni gry.*/
    private int lgX;

    /** Wspolrzedna y lewego gornego naroznika obrazka odpadu w przestrzeni gry.*/
    private int lgY;

    /** Wspolrzedna x srodka ciezkosci obrazka (w przyblizeniu prostokat) odpadu w stosunku do lewego gornego naroznika tegoz obrazka.*/
    private int cX;

    /** Wspolrzedna y srodka ciezkosci obrazka (w przyblizeniu prostokat) odpadu w stosunku do lewego gornego naroznika tegoz obrazka.*/
    private int cY;

    /** Slowna nazwa odpadu.*/
    private String nazwa;
    /** Typ smiecia, na jego podstawie wykonywane jest sprawdzenie gdzie obiekt tej klasy powinien byc przez gracza umieszczony.*/
    private TypySmieci typSmiecia;
    /** Numer, na ktorego podstawie obiektowi jest przydzielana tozsamosc z puli odpadow.*/
    private final int wylosowanyNumer;

    /** Definiuje dozowlone typy smieci. Sa to:
     * RESZTKOWY,
     * PLASTIK,
     * BIO,
     * SZKLO,
     * PAPIER.*/
    public enum TypySmieci {RESZTKOWY,PLASTIK,BIO,SZKLO,PAPIER }

    /** Uniwersalny konstruktor obiektu klasy Odpad. Definiuje polozenie, losuje numer dla obiektu,
     *  przypisuje typ obiektowi oraz nazwe na podstawie wylosowanego numeru.*/
    public Odpad()
    {

        Random losujSmiec= new Random();
        wylosowanyNumer= losujSmiec.nextInt(0,21);
        this.setTyp();
        this.setNazwa();
        this.lgX=500;
        this.lgY=0;
    }

    /** Getter wspolrzednej y srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     *  w lewym gornym narozniku obrazka obiektu.*/
    public int getcY()
    {
        return cY;
    }

    /** Setter wspolrzednej y srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     *  w lewym gornym narozniku obrazka obiektu.*/
    public void setcY(int cY)
    {
        this.cY = cY;
    }

    /** Getter wspolrzednej x srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     * w lewym gornym narozniku obrazka obiektu.*/
    public int getcX()
    {
        return cX;
    }

    /** Getter wspolrzednej x srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     *  w lewym gornym narozniku obrazka obiektu.*/
    public void setcX(int cX)
    {
        this.cX = cX;
    }

    /** Getter wspolrzednej x lewego gornego naroznika obrazka obiektu w polu gry .*/
    public int getLgX()
    {
        return lgX;
    }

    /** Getter wspolrzednej y lewego gornego naroznika obrazka obiektu w polu gry .*/
    public int getLgY()
    {
        return lgY;
    }

    /** Setter wspolrzednej x lewego gornego naroznika obrazka obiektu w polu gry .*/
    public void setLgX(int x)
    {
        this.lgX =x;
    }

    /** Setter wspolrzednej y lewego gornego naroznika obrazka obiektu w polu gry .*/
    public void setLgY(int y)
    {
        this.lgY =y;
    }

    /** Metoda ta zwraca wartosc pola nazwa obiektu klasy Odpad.*/
    public String getNazwa()
    {
        return nazwa;
    }
    /** Metoda ta zwraca wartosc pola typSmiecia obiektu klasy Odpad.*/
    public TypySmieci getTyp()
    {
        return this.typSmiecia;
    }

    /** Metoda ta tworzy i wypelnia obiekt klasy HashMapa przechowujacy jako klucz unikalna sciezke do obrazka obiektu
     * i odpowiadajaca mu nazwe slowna odpadu jako wartosc.
     * Na podstawie wartosci pola wylosowanyNumer obiektu klasy Odpad wykonywana jest metoda getSmiecPath(),
     * a z jej wyniku w HasMapie wyszukiwana jest slowana nazwa przypisywana jako pole nazwa obiektu.
     */
    public void setNazwa()
    {
        HashMap<String,String> nazwySmieciMapa= new HashMap<String,String>();
        nazwySmieciMapa.put("resources/Ości.png","Ości");
        nazwySmieciMapa.put("resources/ButelkaPET.png","Plastikowa butelka");
        nazwySmieciMapa.put("resources/ButelkaPoMleku.png","Butelka po mleku");
        nazwySmieciMapa.put("resources/ButelkaPoPranie.png","Butelka po płynie do prania");
        nazwySmieciMapa.put("resources/Gazety.png","Gazety");
        nazwySmieciMapa.put("resources/Jabłko.png","Resztki jabłka");
        nazwySmieciMapa.put("resources/Karton.png","Karton");
        nazwySmieciMapa.put("resources/KartonPoSoku.png","Karton po soku");
        nazwySmieciMapa.put("resources/KubekPoNapojuNaWynos.png","Kubek po napoju na wynos");
        nazwySmieciMapa.put("resources/MetalowaPuszka1.png","Metalowa puszka");
        nazwySmieciMapa.put("resources/PapierowaTorba.png","Papierowa torba");
        nazwySmieciMapa.put("resources/PękniętyKubek.png","Pęknięta ceramika");
        nazwySmieciMapa.put("resources/PuszkaPoNapoju.png","Puszka po napoju");
        nazwySmieciMapa.put("resources/PuszkaPoSprayu.png","Puszka po sprayu");
        nazwySmieciMapa.put("resources/SkorupkiJajka.png","Skorupki jajek");
        nazwySmieciMapa.put("resources/SkórkaBanana.png","Skórka banana");
        nazwySmieciMapa.put("resources/Słoik.png","Słoik");
        nazwySmieciMapa.put("resources/SzklanaButelka1.png","Szklana butelka");
        nazwySmieciMapa.put("resources/SzklanaButelka2.png","Szklana butelka");
        nazwySmieciMapa.put("resources/TłustyKartonPoPizzy.png","Karton po pizzy");
        nazwySmieciMapa.put("resources/ZbitaButelka.png","Zbita szklana butelka");
        nazwySmieciMapa.put("resources/ZgniecionaKartka.png","Zgnieciona kartka papieru");

        this.nazwa=nazwySmieciMapa.get(this.getSmiecPath());
    }

    /** Metoda ta zwraca String, ktorego zawartosc wyznaczana jest na podstawie wartosci pola typSmiecia obiektu klasy Odpad.
     * Uzywana jest celem wyznaczenia slownego opisu koloru pojemnika do ktorego powinien byc wyrzyucany odpad */
    public String getPojemnikSlowo()
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

    /** Metoda ta tworzy i wypelnia obiekt klasy HashMapa przechowujacy jako klucz unikalna sciezke do obrazka obiektu
     * i odpowiadajacy mu typ smieci z zakresu enum TypySmieci jako wartosc.
     * Na podstawie wartosci pola wylosowanyNumer obiektu klasy Odpad wykonywana jest metoda getSmiecPath(),
     * a z jej wyniku w HasMapie wyszukiwany jest typ smiecia przypisywany jako pole typSmiecia obiektu.
     */
    private void setTyp()
    {
        HashMap<String,TypySmieci> nazwyTypowMapa = new HashMap<String,TypySmieci>();
        nazwyTypowMapa.put("resources/Ości.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("resources/ButelkaPET.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/ButelkaPoMleku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/ButelkaPoPranie.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/Gazety.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("resources/Jabłko.png",TypySmieci.BIO);
        nazwyTypowMapa.put("resources/Karton.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("resources/KartonPoSoku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/KubekPoNapojuNaWynos.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("resources/MetalowaPuszka1.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/PapierowaTorba.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("resources/PękniętyKubek.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("resources/PuszkaPoNapoju.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("resources/PuszkaPoSprayu.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("resources/SkorupkiJajka.png",TypySmieci.BIO);
        nazwyTypowMapa.put("resources/SkórkaBanana.png",TypySmieci.BIO);
        nazwyTypowMapa.put("resources/Słoik.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("resources/SzklanaButelka1.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("resources/SzklanaButelka2.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("resources/TłustyKartonPoPizzy.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("resources/ZbitaButelka.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("resources/ZgniecionaKartka.png",TypySmieci.PAPIER);

        this.typSmiecia =nazwyTypowMapa.get(this.getSmiecPath());
    }

    /** Metoda ta na podstawie wartosci pola wylosowanyNumer obiektu zwraca String,
     *  bedacy sciezka do pliku graficznego zawierajacego obrazek odpadu wyswietlany w  polu gry lub sluzacy do okreslenia nazwy, typu odpadu.*/
    public String getSmiecPath()
    {

        String smiecImagePath;
        switch(this.wylosowanyNumer)
        {
            case 0:
                smiecImagePath="resources/Ości.png";
                break;
            case 1:
                smiecImagePath="resources/ButelkaPET.png";
                break;
            case 2:
                smiecImagePath="resources/ButelkaPoMleku.png";
                break;
            case 3:
                smiecImagePath="resources/ButelkaPoPranie.png";
                break;
            case 4:
                smiecImagePath="resources/Gazety.png";
                break;
            case 5:
                smiecImagePath="resources/Jabłko.png";
                break;
            case 6:
                smiecImagePath="resources/Karton.png";
                break;
            case 7:
                smiecImagePath="resources/KartonPoSoku.png";
                break;
            case 8:
                smiecImagePath="resources/KubekPoNapojuNaWynos.png";
                break;
            case 9:
                smiecImagePath="resources/MetalowaPuszka1.png";
                break;
            case 10:
                smiecImagePath="resources/PapierowaTorba.png";
                break;
            case 11:
                smiecImagePath="resources/PękniętyKubek.png";
                break;
            case 12:
                smiecImagePath="resources/PuszkaPoNapoju.png";
                break;
            case 13:
                smiecImagePath="resources/PuszkaPoSprayu.png";
                break;
            case 14:
                smiecImagePath="resources/SkorupkiJajka.png";
                break;
            case 15:
                smiecImagePath="resources/SkórkaBanana.png";
                break;
            case 16:
                smiecImagePath="resources/Słoik.png";
                break;
            case 17:
                smiecImagePath="resources/SzklanaButelka1.png";
                break;
            case 18:
                smiecImagePath="resources/SzklanaButelka2.png";
                break;
            case 19:
                smiecImagePath="resources/TłustyKartonPoPizzy.png";
                break;
            case 20:
                smiecImagePath="resources/ZbitaButelka.png";
                break;
            case 21:
                smiecImagePath="resources/ZgniecionaKartka.png";
                break;
            default:
                smiecImagePath="";
                break;

        }
        return smiecImagePath;
    }

}
