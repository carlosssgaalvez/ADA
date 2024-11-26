public class Analizador {
    private static final int numIntentos = 5;
    private static final Temporizador temp = new Temporizador();

    private static void evaluar() {
        int n1 = 10;
        int n2 = 20;
        double ratioPequeno = mediaIntentos(n2) / mediaIntentos(n1);

        if (ratioPequeno > 1000) {
            if (ratioPequeno < 2000) {
                System.out.println("2N");
            } else {
                System.out.println("NF");
            }
        } else { // ratio < 1000
            n1 = 4000;
            n2 = 2 * n1;
            double ratio = (double) mediaIntentos(n2) / mediaIntentos(n1);

            if (6.5 <= ratio && ratio < 10.0) {
                System.out.println("N3");
            } else if (3.2 <= ratio && ratio < 6.5) {
                System.out.println("N2");
            } else if (2.7 <= ratio && ratio < 3.2) {
                System.out.println("NLOGN");
            } else if (1.5 <= ratio && ratio < 2.7) {
                System.out.println("N");
            } else if (1.005 <= ratio && ratio < 1.5) {
                System.out.println("LOGN");
            } else if (0 < ratio && ratio <= 1.005) {
                System.out.println("1");
            }
        }
    }

    public static void main(String[] args) {
        evaluar();
    }

    private static double mediaIntentos(int num) {
        int media = 0;
        for (int i = 0; i < numIntentos; i++) {
            temp.reiniciar();
            temp.iniciar();
            try {
                Algoritmo.f(num);
            } catch (Exception e) {
                System.err.println("Error en Algoritmo.f(): " + e.getMessage());
                // Manejar el error según sea necesario
            }
            temp.parar();
            media += temp.tiempoPasado();
        }
        return media / numIntentos;
    }
}