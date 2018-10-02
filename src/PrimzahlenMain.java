import java.util.Scanner;


public class PrimzahlenMain {

    public static final int MAXPRIM = 1000000;

    public static void main(String[] args) {

        int n;
        Primzahlen meinePrimzahlen = new Primzahlen(MAXPRIM);
        Scanner sc = new Scanner(System.in);

        PrimzahlenConsumer pc = new PrimzahlenConsumer(meinePrimzahlen);
        Thread t = new Thread(() -> {
            PrimzahlenProducer pp = new PrimzahlenProducer(meinePrimzahlen);
        });
        t.start();
        do {
            // Einlesen einer Zahl
            n = sc.nextInt();
            int finalN = n;
            new Thread(() -> {
                while (meinePrimzahlen.ready < finalN && pc.istPrimzahl(finalN)) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (finalN < MAXPRIM) {
                    if (pc.istPrimzahl(finalN)) {
                        System.out.println(finalN + " ist eine Primzahl");
                    } else {
                        System.out.println(finalN + " ist keine Primzahl");
                    }
                }
            }).start();
        }
        while (n > 0);
    }
}
    
