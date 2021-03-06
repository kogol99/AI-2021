package Gra;

public class Mapa {
    private Pole[] pole;
    private static final int ILOSC_KAMYKOW = 4;

    public Mapa() {
        this.pole = new Pole[14];
        int i = 0;
        for (; i < 6; i++) {
            pole[i] = new Pole(ILOSC_KAMYKOW, false, 1);
        }
        pole[i++] = new Pole(0, true, 1);
        for (; i < 13; i++) {
            pole[i] = new Pole(ILOSC_KAMYKOW, false, 2);
        }
        pole[i] = new Pole(0, true, 2);
    }

    /**
     * @param index index z ktorego rozpoczynamy przesuwanie kamykow
     * @param gracz 1 - gracz pierwszy; 2 - gracz drugi; numer aktualnego gracza
     * @return ostatni index na ktory wpadl kamyk
     */
    int przesunKamyki(int index, int gracz) {
        int iloscDoPrzesuniecia = this.pole[index].getIloscKamykow();
        this.pole[index].setIloscKamykow(0);
        if (iloscDoPrzesuniecia <= 0) {
            //System.out.println("index: " + index + "; gracz: " + gracz +"; iloscDoPrzesuniecia: " + iloscDoPrzesuniecia);
            return -1;
        }
        for (int i = 1; i < iloscDoPrzesuniecia + 1; i++) {
            if (pole[(index + i) % (pole.length)].isCzyKoncowy() && pole[(index + i) % (pole.length)].getCzyjePole() != gracz) {
                iloscDoPrzesuniecia++;
            } else {
                pole[(index + i) % (pole.length)].setIloscKamykow(pole[(index + i) % (pole.length)].getIloscKamykow() + 1);
            }
        }
        return (index + iloscDoPrzesuniecia) % (pole.length);
    }

    /**
     * @param index index z ktorego zostal wykonany ruch
     * @param gracz 1 - gracz pierwszy; 2 - gracz drugi; numer aktualnego gracza
     * @return ktora spelniona zostala zasada
     */
    int sprawdzZasady(int index, int gracz) {
        int iloscDoPrzesuniecia = 0;
        for (int i = index; i < (this.pole[index].getIloscKamykow()) + index; i++) {
            if (this.pole[i % (this.pole.length)].isCzyKoncowy() && !(this.pole[i % (this.pole.length)].getCzyjePole() == gracz)) {
                iloscDoPrzesuniecia += 2;
            } else {
                iloscDoPrzesuniecia += 1;
            }
        }
        Pole poleOstatniegoKamyka = this.pole[(index + iloscDoPrzesuniecia) % (pole.length)];
        int spelnienieZasady = 0;
        boolean czyKoncowy = poleOstatniegoKamyka.isCzyKoncowy();
        if (poleOstatniegoKamyka.getCzyjePole() == gracz && czyKoncowy) spelnienieZasady = 1; // zasada o ostatnim kamyku w studni
        if ((poleOstatniegoKamyka.getIloscKamykow() == 0) && (!czyKoncowy) && (poleOstatniegoKamyka.getCzyjePole() == gracz)) {
            spelnienieZasady = 2; // zasada o przeniesieniu swoich kamykow oraz przeciwnika do swojej studni
        }
        return spelnienieZasady;
    }

    /**
     * Metoda wykonuj??ca zasad??, kt??ra m??wi: je??li ostatni kamie?? wpad?? do w??asnego pustego do??ka, gracz bierze wszystkie kamienie z le????cego naprzeciw do??ka przeciwnika i wk??ada je do swojej bazy.
     * @param ostatniIndex index, na kt??ry wpad?? ostatni kamyk
     * @param gracz numer gracza
     */
    void wykonajZasade2(int ostatniIndex, int gracz) {
        int przeciwnyIndex;
        int indexGlownego;

        if (gracz == 1) {
            przeciwnyIndex = ostatniIndex + ((6 - ostatniIndex) * 2);
            indexGlownego = pole.length / 2 - 1;
        } else {
            przeciwnyIndex = (ostatniIndex + ((13 - ostatniIndex) * 2)) % pole.length;
            indexGlownego = pole.length - 1;
        }
        if (pole[przeciwnyIndex].getIloscKamykow() == 0) {
            pole[indexGlownego].setIloscKamykow(pole[indexGlownego].getIloscKamykow() + pole[przeciwnyIndex].getIloscKamykow());
            pole[przeciwnyIndex].setIloscKamykow(0);
        } else {
            pole[indexGlownego].setIloscKamykow(pole[indexGlownego].getIloscKamykow() + pole[przeciwnyIndex].getIloscKamykow() + pole[ostatniIndex].getIloscKamykow());
            pole[przeciwnyIndex].setIloscKamykow(0);
            pole[ostatniIndex].setIloscKamykow(0);
        }
    }

    /**
     * metoda sprawdza czy gra dobieg??a ko??ca
     * @return true - zako??czona; false - trwa nadal
     */
    boolean sprawdzCzyKoniec() {
        if (pole[6].getIloscKamykow() >= 0 || pole[13].getIloscKamykow() >= 0) {
            int ileSpelnia = 0;
            for (int i = 0; i < 6; i++) {
                if (pole[i].getIloscKamykow() == 0) ileSpelnia++;
            }
            if (ileSpelnia == 6) return true;
            ileSpelnia = 0;
            for (int i = 7; i < 13; i++) {
                if (pole[i].getIloscKamykow() == 0) ileSpelnia++;
            }
            return ileSpelnia == 6;
        }
        return false;
    }

    /**
     * Meroda u??ywana po zako??czeniu rogrywki, wrzuca pozosta??e kamyki z mapy do baz
     */
    void zsumujKamyki() {
        int i = 0;
        for (; i < 6; i++) {
            pole[6].setIloscKamykow(pole[6].getIloscKamykow() + pole[i].getIloscKamykow());
        }
        for (i++; i < 13; i++) {
            pole[13].setIloscKamykow(pole[13].getIloscKamykow() + pole[i].getIloscKamykow());
        }
    }

    /**
     * Metoda wy??wietlaj??ca wyniki rozegranej rozgrywki
     */
    void podajWynik() {
        System.out.println("\n\n@@ WYNIKI @@");
        int wynikGracz1 = pole[6].getIloscKamykow();
        int wynikGracz2 = pole[13].getIloscKamykow();
        System.out.println("Gracz 1:" + wynikGracz1);
        System.out.println("Gracz 2:" + wynikGracz2);
        if (wynikGracz1 > wynikGracz2) System.out.println("Wygra?? Gracz 1");
        else if (wynikGracz1 < wynikGracz2) System.out.println("Wygra?? Gracz 2");
        else System.out.println("Remis");
    }

    /**
     * Metoda wy??wietlajaca aktualna mape rogrywki
     */
    void pokazMape() {
        //baza to indexy 6 i 13
        int index = pole.length - 2;
        for (int i = 0; i < 6; i++) {
            System.out.print("\t" + pole[index].getIloscKamykow());
            index--;
        }
        index = 6;
        System.out.println("\n" + pole[pole.length - 1].getIloscKamykow() + "\t\t\t\t\t\t\t" + pole[index].getIloscKamykow());
        index = 0;

        for (int i = 0; i < 6; i++) {
            System.out.print("\t" + pole[index].getIloscKamykow());
            index++;
        }
    }

    public Pole[] getPole() {
        return pole;
    }

    public Pole getPole(int numerPola, int jakiGracz){
        if (jakiGracz == 1) {
            return this.pole[numerPola - 1];
        } else {
            int liczba = numerPola + this.getRozmiarMapy()/2 - 1;
            Pole pole = this.pole[liczba];
            return pole;
        }
    }

    public void setPole(Pole[] pole) {
        this.pole = pole;
    }

    public int getRozmiarMapy(){
        return pole.length;
    }

    public int getRoznicaKamykowDlaGracza(int gracz){
        int ilosc = this.pole[getRozmiarMapy()/2 - 1].getIloscKamykow() - this.pole[getRozmiarMapy() - 1].getIloscKamykow();
        if (gracz == 1){
            return ilosc;
        } else {
            return -ilosc;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Mapa mapa = null;
        try {
            mapa = new Mapa();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pole[] pole = new Pole[14];
        for (int i = 0; i < 14; i++) {
            pole[i] = (Pole) this.getPole()[i].clone();
        }
        mapa.setPole(pole);

        return mapa;
    }
}