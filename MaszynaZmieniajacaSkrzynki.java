

public class MaszynaZmieniajacaSkrzynki implements Runnable {
    private Skrzynka skrzynka;

    public MaszynaZmieniajacaSkrzynki(Skrzynka skrzynka) {
        this.skrzynka = skrzynka;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Uruchamiam maszynę zmieniającą skrzynki;");

        while (true) {
            synchronized (skrzynka) {
                while (!skrzynka.skrzynkaJestPelna()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Skrzynka nie jest pełna, więc czekam;");
                        skrzynka.wait();
                        System.out.println(Thread.currentThread().getName() + " Zostalem wywolany;");

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            synchronized (skrzynka) {
                skrzynka.pobierzIloscButelek();
                skrzynka.zamianaSkrzynek();
                skrzynka.dodajSkrzynke(new Skrzynka());
                System.out.println(Thread.currentThread().getName() + " Przekazałem skrzynkę do maszyny pakującej;");
                System.out.println(Thread.currentThread().getName() + " Skrzynka po zmianie jest pusta;");
                skrzynka.notifyAll();
            }
        }
    }
}
