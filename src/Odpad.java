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
     * PAPIER.
     * */
    public enum TypySmieci {/**typ smieci zmieszane*/RESZTKOWY,/**typ smieci plastik i metal*/PLASTIK,/**typ smieci biodegradowalne*/
    BIO,/**typ smieci szklo*/SZKLO,/**typ smieci papier*/PAPIER }

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
     *  w lewym gornym narozniku obrazka obiektu.
     *  @return cY*/
    public int getcY()
    {
        return cY;
    }

    /** Setter wspolrzednej y srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     *  w lewym gornym narozniku obrazka obiektu.
     *  @param cY wartosc ktora ma zostac ustawiona*/
    public void setcY(int cY)
    {
        this.cY = cY;
    }

    /** Getter wspolrzednej x srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     * w lewym gornym narozniku obrazka obiektu.
     * @return cX*/
    public int getcX()
    {
        return cX;
    }

    /** Getter wspolrzednej x srodka ciezkosci obiektu, w ukladzie wspolrzednych, ktorego poczatek znajduje sie
     *  w lewym gornym narozniku obrazka obiektu.
     *  @param cX wartosc ktora ma zostac ustawiona*/
    public void setcX(int cX)
    {
        this.cX = cX;
    }

    /** Getter wspolrzednej x lewego gornego naroznika obrazka obiektu w polu gry.
     * @return lgX*/
    public int getLgX()
    {
        return lgX;
    }

    /** Getter wspolrzednej y lewego gornego naroznika obrazka obiektu w polu gry.
     * @return lgY*/
    public int getLgY()
    {
        return lgY;
    }

    /** Setter wspolrzednej x lewego gornego naroznika obrazka obiektu w polu gry.
     * @param x  wratosc ktora ma zostac ustawiona*/
    public void setLgX(int x)
    {
        this.lgX =x;
    }

    /** Setter wspolrzednej y lewego gornego naroznika obrazka obiektu w polu gry.
     * @param y  wartosc ktora ma zostac ustawiona*/
    public void setLgY(int y)
    {
        this.lgY =y;
    }

    /** Metoda ta zwraca wartosc pola nazwa obiektu klasy Odpad.
     * @return nazwa*/
    public String getNazwa()
    {
        return nazwa;
    }
    /** Metoda ta zwraca wartosc pola typSmiecia obiektu klasy Odpad.
     * @return typSmiecia*/
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
        nazwySmieciMapa.put("res/Ości.png","Ości");
        nazwySmieciMapa.put("res/ButelkaPET.png","Plastikowa butelka");
        nazwySmieciMapa.put("res/ButelkaPoMleku.png","Butelka po mleku");
        nazwySmieciMapa.put("res/ButelkaPoPranie.png","Butelka po płynie do prania");
        nazwySmieciMapa.put("res/Gazety.png","Gazety");
        nazwySmieciMapa.put("res/Jabłko.png","Resztki jabłka");
        nazwySmieciMapa.put("res/Karton.png","Karton");
        nazwySmieciMapa.put("res/KartonPoSoku.png","Karton po soku");
        nazwySmieciMapa.put("res/KubekPoNapojuNaWynos.png","Kubek po napoju na wynos");
        nazwySmieciMapa.put("res/MetalowaPuszka1.png","Metalowa puszka");
        nazwySmieciMapa.put("res/PapierowaTorba.png","Papierowa torba");
        nazwySmieciMapa.put("res/PękniętyKubek.png","Pęknięta ceramika");
        nazwySmieciMapa.put("res/PuszkaPoNapoju.png","Puszka po napoju");
        nazwySmieciMapa.put("res/PuszkaPoSprayu.png","Puszka po sprayu");
        nazwySmieciMapa.put("res/SkorupkiJajka.png","Skorupki jajek");
        nazwySmieciMapa.put("res/SkórkaBanana.png","Skórka banana");
        nazwySmieciMapa.put("res/Słoik.png","Słoik");
        nazwySmieciMapa.put("res/SzklanaButelka1.png","Szklana butelka");
        nazwySmieciMapa.put("res/SzklanaButelka2.png","Szklana butelka");
        nazwySmieciMapa.put("res/TłustyKartonPoPizzy.png","Karton po pizzy");
        nazwySmieciMapa.put("res/ZbitaButelka.png","Zbita szklana butelka");
        nazwySmieciMapa.put("res/ZgniecionaKartka.png","Zgnieciona kartka papieru");

        this.nazwa=nazwySmieciMapa.get(this.getSmiecPath());
    }

    /** Metoda ta zwraca String, ktorego zawartosc wyznaczana jest na podstawie wartosci pola typSmiecia obiektu klasy Odpad.
     * Uzywana jest celem wyznaczenia slownego opisu koloru pojemnika do ktorego powinien byc wyrzyucany odpad
     * @return kolor pojemnika w String*/
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
        nazwyTypowMapa.put("res/Ości.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("res/ButelkaPET.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/ButelkaPoMleku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/ButelkaPoPranie.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/Gazety.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("res/Jabłko.png",TypySmieci.BIO);
        nazwyTypowMapa.put("res/Karton.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("res/KartonPoSoku.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/KubekPoNapojuNaWynos.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("res/MetalowaPuszka1.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/PapierowaTorba.png",TypySmieci.PAPIER);
        nazwyTypowMapa.put("res/PękniętyKubek.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("res/PuszkaPoNapoju.png",TypySmieci.PLASTIK);
        nazwyTypowMapa.put("res/PuszkaPoSprayu.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("res/SkorupkiJajka.png",TypySmieci.BIO);
        nazwyTypowMapa.put("res/SkórkaBanana.png",TypySmieci.BIO);
        nazwyTypowMapa.put("res/Słoik.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("res/SzklanaButelka1.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("res/SzklanaButelka2.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("res/TłustyKartonPoPizzy.png",TypySmieci.RESZTKOWY);
        nazwyTypowMapa.put("res/ZbitaButelka.png",TypySmieci.SZKLO);
        nazwyTypowMapa.put("res/ZgniecionaKartka.png",TypySmieci.PAPIER);

        this.typSmiecia =nazwyTypowMapa.get(this.getSmiecPath());
    }

    /** Metoda ta na podstawie wartosci pola wylosowanyNumer obiektu zwraca String,
     *  bedacy sciezka do pliku graficznego zawierajacego obrazek odpadu wyswietlany w  polu gry lub sluzacy do okreslenia nazwy, typu odpadu.
     *  @return sciezka do pliku*/
    public String getSmiecPath()
    {

        String smiecImagePath;
        switch(this.wylosowanyNumer)
        {
            case 0:
                smiecImagePath="res/Ości.png";
                break;
            case 1:
                smiecImagePath="res/ButelkaPET.png";
                break;
            case 2:
                smiecImagePath="res/ButelkaPoMleku.png";
                break;
            case 3:
                smiecImagePath="res/ButelkaPoPranie.png";
                break;
            case 4:
                smiecImagePath="res/Gazety.png";
                break;
            case 5:
                smiecImagePath="res/Jabłko.png";
                break;
            case 6:
                smiecImagePath="res/Karton.png";
                break;
            case 7:
                smiecImagePath="res/KartonPoSoku.png";
                break;
            case 8:
                smiecImagePath="res/KubekPoNapojuNaWynos.png";
                break;
            case 9:
                smiecImagePath="res/MetalowaPuszka1.png";
                break;
            case 10:
                smiecImagePath="res/PapierowaTorba.png";
                break;
            case 11:
                smiecImagePath="res/PękniętyKubek.png";
                break;
            case 12:
                smiecImagePath="res/PuszkaPoNapoju.png";
                break;
            case 13:
                smiecImagePath="res/PuszkaPoSprayu.png";
                break;
            case 14:
                smiecImagePath="res/SkorupkiJajka.png";
                break;
            case 15:
                smiecImagePath="res/SkórkaBanana.png";
                break;
            case 16:
                smiecImagePath="res/Słoik.png";
                break;
            case 17:
                smiecImagePath="res/SzklanaButelka1.png";
                break;
            case 18:
                smiecImagePath="res/SzklanaButelka2.png";
                break;
            case 19:
                smiecImagePath="res/TłustyKartonPoPizzy.png";
                break;
            case 20:
                smiecImagePath="res/ZbitaButelka.png";
                break;
            case 21:
                smiecImagePath="res/ZgniecionaKartka.png";
                break;
            default:
                smiecImagePath="";
                break;

        }
        return smiecImagePath;
    }

}
