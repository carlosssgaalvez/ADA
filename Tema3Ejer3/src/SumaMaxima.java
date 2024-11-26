import java.util.Scanner;

/*
 * Dado un vector V de n enteros. Escribir un algoritmo que en tiempo O(nlogn) encuentre el subvector (formado por elementos consecutivos) cuya suma sea máxima
 * 
 * */
public class SumaMaxima {
    public static class Res{
		public int i,j,sum;

		public Res(int i, int j, int sum) {
			this.i = i;
			this.j = j;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "Res [i=" + i + ", j=" + j + ", sum=" + sum + "]";
		}
	}
	
	
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] sv = sc.next().split(",");
    	    
    	    Integer[] v = new Integer[sv.length];
    	    
    	    for (int i = 0 ; i < sv.length; i++) v[i] = Integer.parseInt(sv[i]);
    	    
    	    Res k = SM(v);
    	    
    	    System.out.println(k.toString());
	    }
	}
	
	public static Res SM(Integer[] v) {
		return SMR(v,0,v.length-1);
	}
	
	private static Res SMR(Integer[] v, int i, int j) {
 	    // Si el subvector contiene solo un elemento, devuelve un nuevo objeto Res con i, j y v[i] como la suma
	    if (i == j) {
	        return new Res(i, j, v[i]);
	    }

	    // Calcula el punto medio del subvector
	    int m = (i + j) / 2;

	    								
	    Res izq = SMR(v, i, m);				// Encuentra la suma máxima en el subvector izquierdo
	    Res der= SMR(v, m + 1, j);			// Encuentra la suma máxima en el subvector derecho
	    Res med = sumMed(v, i, m, j);		// Encuentra la suma máxima en el subvector que cruza el punto medio

	    // Compara las sumas máximas de los tres subvectores y devuelve el objeto Res con la mayor suma
	    if (izq.sum >= der.sum && izq.sum >= med.sum) {
	        return izq;
	    } else if (der.sum >= izq.sum && der.sum >= med.sum) {
	        return der;
	    } else {
	        return med;
	    }
	}

	// Método para encontrar la suma máxima en un subvector que cruza el punto medio
	private static Res sumMed(Integer[] v, int i, int m, int j) {
	    // Inicializa sumIzq con el valor mínimo posible para un entero
	    int sumIzq = Integer.MIN_VALUE;
	    int sum = 0;
	    int maxIzq = -1;
	    // Calcula la suma máxima en el subvector que termina en el punto medio
	    for (int k = m; k >= i; k--) {
	        sum += v[k];
	        if (sum > sumIzq) {
	            sumIzq = sum;
	            maxIzq = k;
	        }
	    }

	    // Inicializa sumDer con el valor mínimo posible para un entero
	    int sumDer = Integer.MIN_VALUE;
	    sum = 0;
	    int maxDer = -1;
	    // Calcula la suma máxima en el subvector que comienza en el punto medio
	    for (int k = m + 1; k <= j; k++) {
	        sum += v[k];
	        if (sum > sumDer) {
	            sumDer = sum;
	            maxDer = k;
	        }
	    }

	    // Devuelve un nuevo objeto Res con los índices de inicio y fin del subvector con la suma máxima y la suma de los elementos en el subvector
	    return new Res(maxIzq, maxDer, sumIzq + sumDer);
	}
}



