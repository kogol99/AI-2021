package Gra;

import java.util.ArrayList;
import java.util.List;

public class Kalaha {
    private Mapa mapa;
    private int aktualnyGracz;
    private int czyKolejnyGracz;
    private int wybranyIndex;

    public Kalaha() {
        this.mapa = new Mapa();
        aktualnyGracz = 2;
        czyKolejnyGracz = 1;
        wybranyIndex = -1;
    }

    boolean przesunKamyki(int index) {
        this.czyKolejnyGracz = 1;
        this.wybranyIndex = index;
        int zasada = mapa.sprawdzZasady(index, this.aktualnyGracz);
        int ostatniIndex = mapa.przesunKamyki(index, this.aktualnyGracz);

        if (zasada == -1) {
            throw new IllegalArgumentException("Puste pole"); // puste pole
        }
        if (zasada == 0) {
            return true; // poprawny ruch
        }
        if (zasada == 1) { // zasada o ostatnim kamyku w studni
            this.czyKolejnyGracz = 0;
            return false;
        }
        if (zasada == 2) { // zasada o przeniesieniu swoich kamykow oraz przeciwnika do swojej studni
            wykonajZasade2(ostatniIndex);
        }
        return true;
    }

    public boolean wykonajRuchGracz(int zPola, int jakiGracz) {
        this.aktualnyGracz = jakiGracz;
        if (this.aktualnyGracz == 1) {
            if (zPola > 6 || zPola < 1) {
                throw new IllegalArgumentException("Zle wybrane pole ruchu Gracz 1");
            } else {
                return przesunKamyki((zPola - 1));
            }
        } else if (this.aktualnyGracz == 2) {
            if (zPola > 6 || zPola < 1) {
                throw new IllegalArgumentException("Zle wybrane pole ruchu Gracz 1");
            } else {
                return przesunKamyki((zPola - 1) + (mapa.getPole().length / 2));
            }
        } else {
            throw new IllegalArgumentException("Zle podany gracz do ruchu");
        }
    }

    public int ilePunktowGracz(int jakiGracz) {
        if (this.sprawdzCzyKoniec()) {
            this.zsumujKamyki();
        }
        if (jakiGracz == 1) {
            return mapa.getPole()[6].getIloscKamykow();
        } else if (jakiGracz == 2) {
            return mapa.getPole()[13].getIloscKamykow();
        } else {
            throw new IllegalArgumentException("Zle podany gracz do odczytu punktow");
        }
    }

    public int czyjNastepnyRuch() {
        if (this.czyKolejnyGracz == 1) {
            if (aktualnyGracz == 1) return 2;
            if (aktualnyGracz == 2) return 1;
        } else {
            if (aktualnyGracz == 1) return 1;
            if (aktualnyGracz == 2) return 2;
        }
        return 0;
    }

    public boolean sprawdzCzyKoniec() {
        return mapa.sprawdzCzyKoniec();
    }

    public void pokazMape() {
        mapa.pokazMape();
    }

    public void zsumujKamyki() {
        mapa.zsumujKamyki();
    }

    public void podajWynik() {
        mapa.podajWynik();
    }

    void wykonajZasade2(int ostatniIndex) {
        mapa.wykonajZasade2(ostatniIndex, this.aktualnyGracz);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public void setAktualnyGracz(int aktualnyGracz) {
        this.aktualnyGracz = aktualnyGracz;
    }

    public void setCzyKolejnyGracz(int czyKolejnyGracz) {
        this.czyKolejnyGracz = czyKolejnyGracz;
    }

    public void setWybranyIndex(int wybranyIndex) {
        this.wybranyIndex = wybranyIndex;
    }

    public int getAktualnyGracz(){
        return this.aktualnyGracz;
    }

    public boolean czyPierwszyRuch(){ return this.wybranyIndex == -1;}

    public int getWybranyIndex(){ return this.wybranyIndex; }

    public List<Kalaha> getDzieciGracza(int jakiGracz){
        List<Kalaha> dzieci = new ArrayList<>();
        for( int i = 1; i < this.mapa.getRozmiarMapy()/2; i++) {
            dodajDzieckoPoRuchuZPola(i, dzieci, jakiGracz);
        }
        return dzieci;
    }

    public void dodajDzieckoPoRuchuZPola(int idPola, List<Kalaha> dzieci, int jakiGracz){
        if (!(this.mapa.getPole(idPola, jakiGracz).getIloscKamykow() == 0)){
            Kalaha dziecko = null;
            try {
                dziecko = (Kalaha) this.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert dziecko != null;
            dziecko.wykonajRuchGracz(idPola, jakiGracz);
            dzieci.add(dziecko);
        }
    }

    public int getPrzewagaGracza(int gracz){
        return this.mapa.getRoznicaKamykowDlaGracza(gracz);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Kalaha kalaha = null;
        try {
            kalaha = new Kalaha();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mapa mapa = (Mapa) this.getMapa().clone();
        kalaha.setMapa(mapa);
        kalaha.setAktualnyGracz(this.aktualnyGracz);
        kalaha.setCzyKolejnyGracz(this.czyKolejnyGracz);
        kalaha.setWybranyIndex(this.wybranyIndex);

        return kalaha;
    }
}