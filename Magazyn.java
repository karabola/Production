;

public class Magazyn implements Runnable {
    private Skrzynka skrzynka;
    public Magazyn(Skrzynka skrzynka) {
        this.skrzynka = skrzynka;
    }
    public void run() {
        synchronized (skrzynka){

                while (skrzynka.paletaJestPelna()){
                    try {
                        System.out.println(Thread.currentThread().getName() + " Otrzymałem paletę;");
                        skrzynka.wait();
                        System.out.println(Thread.currentThread().getName() + " iii");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }skrzynka.notifyAll();
        }
    }
}
