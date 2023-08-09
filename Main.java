

public class Main {
    public static void main(String[] args) {
        Skrzynka skrzynka = new Skrzynka();
        MaszynaProdukujacaButelki maszynaProdukujacaButelki = new MaszynaProdukujacaButelki(skrzynka);
        MaszynaZmieniajacaSkrzynki maszynaZmieniajacaSkrzynki = new MaszynaZmieniajacaSkrzynki(skrzynka);
        MaszynaPakujaca maszynaPakujaca = new MaszynaPakujaca(skrzynka);

        Thread produkcja = new Thread(maszynaProdukujacaButelki, "producent:");
        Thread zmieniacz = new Thread(maszynaZmieniajacaSkrzynki, "zmieniacz:");
        Thread pakowacz = new Thread(maszynaPakujaca, "pakowacz:");

        produkcja.start();
        zmieniacz.start();
        pakowacz.start();

        try {
            produkcja.join();
            zmieniacz.join();
            pakowacz.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("wychodze");

    }
}
