import java.util.Scanner;

/*
 * Dados dos vectores de n enteros cada uno y ordenados de forma creciente, 
 * escribir un algoritmo para hallar la mediana del vector formado por los 2n enteros, cuya complejidad sea logn.
 * por ejemplo:
 * Sea v = [2 4 6 8 10 10 12 13] y w = [2 3 3 5 6 6 10 12]. Entonces vw seria = [2 2 3 3 4 5 6 6 6 8 10 10 10 12 12 13] y su mediana seria 6 
 * 
 * */
public class Mediana {
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    String[] sv = sc.next().split(",");
    	    String[] sw = sc.next().split(",");
    	    
    	    Integer[] v = new Integer[sv.length];
    	    Integer[] w = new Integer[sw.length];
    	    
    	    for (int i = 0 ; i < sv.length; i++) v[i] = Integer.parseInt(sv[i]);
    	    for (int i = 0 ; i < sw.length; i++) w[i] = Integer.parseInt(sw[i]);
    	    
    	    int k = med(v,w);
    	    
    	    System.out.println(Integer.toString(k));
	    }
	}
	
	public static int med(Integer[] v, Integer[] w) {
		return medREC(v,w,0,v.length-1,0,w.length-1);
	}

	private static int medREC(Integer[] v, Integer[] w, int iv, int jv, int iw, int jw) {
		if(jv-iv <= 1) {
			return (Math.max(v[iv], w[iw]) + Math.min(v[jv], w[jw]))/2;
		} else {
			int medioV = (jv+iv)/2;
			int medioW = (jw+iw)/2;
			if(v[medioV] <= w[medioW]) {
				return medREC(v,w,medioV,jv,iw,medioW);
			} else {
				return medREC(v,w,iv,medioV,medioW,jw);
			}
		}
	}
}




