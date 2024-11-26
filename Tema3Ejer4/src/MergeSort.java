import java.util.Scanner;
import java.util.Arrays;

/*
 * Implementar el algoritmo de ordenación Merge sort
 * 
 * */
public class MergeSort {
    
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] sv = sc.next().split(",");
    	    
    	    Integer[] v = new Integer[sv.length];
    	    
    	    for (int i = 0 ; i < sv.length; i++) v[i] = Integer.parseInt(sv[i]);
    	    
    	    sort(v);
    	    
    	    System.out.print(Arrays.toString(v));
    	    
    	    System.out.println();
	    }
	}
	
	public static void sort(Integer[] v) {
		sortR(v,0,v.length-1);
	}
	
	private static void sortR(Integer[] v, int i, int j) {
	    if(i < j) {							// dividimos hasta tener arrays de 1 elemento
	    	int mitad = (i + j)/2;
	    	sortR(v,i,mitad);
	    	sortR(v,mitad+1,j);
	    	merge(v,i,mitad,j);				// mezclamos
	    	
	    }
	}
	
	public static void merge(Integer[] v, int i, int medio, int j) {
		int n1 = medio - i + 1;					//  nº elementos desde el principio hasta la mitad incluida
		int n2 = j - medio;						//  nº elementos desde el medio (sin incluir) hasta el final

		int izquierdos[] = new int [n1]; 			// creamos los dos arrays
		int derechos[] = new int [n2];

		for (int x=0; x < n1; x++) {  			// copiamos en arrays izquierdos y derechos	
			izquierdos[x] = v[i+x];
		}
		for (int y=0; y < n2; y++) {				// derechos no cogen medio
			derechos[y] = v[medio + y + 1];
		}

		int x = 0, y = 0;
		int k = i;

		while (x < n1 && y < n2) {				//Ordenamos hasta que una de las listas temporales se quede sin elementos a evaluar
			if (izquierdos[x] <= derechos[y]) {		// vamos comparando numeros de los arrays y los ordenamos en el vector principal
				v[k] = izquierdos[x];
				x++;
		    } else {
		    	v[k] = derechos[y];
		    	y++;
		    }
		    k++;
		}

		while (x < n1) { 							// quedan elementos en izquierdos que no hemos metido en el vector porque son mayores
			v[k] = izquierdos[x];
			x++;
			k++;
		}
		while (y < n2) {							// quedan elementos en derechos que no hemos metido en el vector porque son mayores
			v[k] = derechos[y];						
			y++;
		    k++;
		}
	}
}


