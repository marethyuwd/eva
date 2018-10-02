public class PrimzahlenProducer {
    boolean prim[];
    long ready = 0;


    public PrimzahlenProducer(Primzahlen meinePrimzahlen) {
        Thread thread = new Thread(() -> {
            int n = meinePrimzahlen.getLength() - 1;

            // Alle Zahlen von 1 bis n aufschreiben
            for (int j = 1; j <= n; j++) {

                meinePrimzahlen.setPrim(j);
                // Jedes Feldelement wird erst einmal auf wahr (= Primzahl) gesetzt
            }

            // 1 ist keine Primzahl
            meinePrimzahlen.setNonPrim(1);
            meinePrimzahlen.setReady(1);
            System.out.println("Primzahlen bis " + 1 + " verfuegbar");

            for (int i = 2; i * i <= n; i++) {
                meinePrimzahlen.setReady(i);
                // System.out.println("Primzahlen bis " + i + " verfuegbar");

                if (meinePrimzahlen.isPrim(i)) // not durchgestrichen(i)
                {
                    // durchstreichenVielfaches(i)
                    for (int j = 2 * i; j <= n; j = j + i) {
                        meinePrimzahlen.setNonPrim(j);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            meinePrimzahlen.setReady(n);
            System.out.println("Primzahlen bis " + n + " verfuegbar");
        });
        thread.start();
    } //eo-Konstruktor

}