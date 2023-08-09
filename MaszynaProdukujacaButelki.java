

public class MaszynaProdukujacaButelki implements Runnable {
    private Skrzynka skrzynka;
    private int i = 0;

    public MaszynaProdukujacaButelki(Skrzynka skrzynka) {
        this.skrzynka = skrzynka;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Uruchamiam maszynę produkującą butelki;");

        while (true) {
            synchronized (skrzynka) {

                while (skrzynka.skrzynkaJestPelna()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Informuję, że trzeba wymienić skrzynkę i czekam;");
                        skrzynka.wait();
                        System.out.println(Thread.currentThread().getName() + " Powróciłem do produkcji;");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (skrzynka) {
                System.out.println(Thread.currentThread().getName() + " Wyprodukowałem " + (++i) + " butelkę");
                skrzynka.dodajButelke(new Butelka());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                skrzynka.notifyAll();
            }
        }
    }
}
