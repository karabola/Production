

public class MaszynaPakujaca implements Runnable {
    private Skrzynka skrzynka;

    public MaszynaPakujaca(Skrzynka skrzynka) {
        this.skrzynka = skrzynka;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Uruchamiam maszynę pakującą;");

        while (true) {
            synchronized (skrzynka) {
                while (!skrzynka.paletaJestPelna()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Paleta nie jest pełna, więc czekam;");
                        skrzynka.wait();
                        System.out.println(Thread.currentThread().getName() + " Zostalem wywolany;");

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            synchronized (skrzynka) {
                skrzynka.pobierzIloscSkrzynek();
                skrzynka.zamianaPalet();
                skrzynka.pobierzIloscSkrzynek();
                System.out.println(Thread.currentThread().getName() + " Zmieniłem paletę, aktualnie jest pusta.");
                skrzynka.notifyAll();
            }
        }
    }
}
