

import java.util.ArrayList;
import java.util.List;

public class Skrzynka {
    private List listaButelek = new ArrayList<>();
    private List listaSkrzynek = new ArrayList<>();

    private final int POJEMNOSC = 3;
    private final int PALETOWAILOSCSKRZYNEK = 2;

    public boolean skrzynkaJestPelna() {
        if (listaButelek.size() == POJEMNOSC)
            return true;
        return false;
    }

    public void dodajButelke(Butelka butelka) {
        listaButelek.add(butelka);
    }

    public int pobierzIloscButelek() {
        System.out.println(Thread.currentThread().getName() + " aktualnie w skrzynce jest: " + listaButelek.size() + " butelek;");
        return listaButelek.size();
    }

    public void zamianaPalet() {
        System.out.println(Thread.currentThread().getName() + " Zamieniam palety");
        listaSkrzynek.clear();
    }

    public void zamianaSkrzynek() {
        System.out.println(Thread.currentThread().getName() + " Zamieniam skrzynki");
        listaButelek.clear();
    }

    public void dodajSkrzynke(Skrzynka skrzynka) {
        listaSkrzynek.add(skrzynka);
    }

    public int pobierzIloscSkrzynek() {
        System.out.println(Thread.currentThread().getName() + " Aktualnie na palecie znajduje się: " + listaSkrzynek.size() + " skrzynek;");
        return listaSkrzynek.size();
    }

    public boolean paletaJestPelna() {
        if (listaSkrzynek.size() == PALETOWAILOSCSKRZYNEK)
            return true;
        return false;
    }
}
