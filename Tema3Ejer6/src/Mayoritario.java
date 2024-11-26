import java.util.Scanner;

public class Mayoritario {
	public static class Res{
		public int elem;
		public boolean existe;

		public Res(int elem, boolean existe) {
			this.elem = elem;
			this.existe = existe;
		}

		@Override
		public String toString() {
			if (!existe) return "NA";
			return Integer.toString(elem);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = (!aux.existe && !this.existe) ||
					 (aux.elem == this.elem && aux.existe && this.existe);
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(elem);
		}
	}
	
	
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)){
    	    String[] sv = sc.next().split(",");
    	    
    	    int[] v = new int[sv.length];
    	    
    	    for (int i = 0 ; i < sv.length; i++) v[i] = Integer.parseInt(sv[i]);
    	    
    	    Res k = mayoritario(v);
    	    
    	    System.out.println(k.toString());
	    }	
	}
	
	public static Res mayoritario(int[] v) {
		return mayoritarioR(v,0,v.length-1);
	}
	
	private static Res mayoritarioR(int[] v, int i, int j){
		int cont = 0, candidato = -1;
	    for (int k = i; k <= j; k++) {  		// vemos que numero se repite mas
	        if (cont == 0) {
	            candidato = v[k];
	            cont = 1;
	        } else if (v[k] == candidato) {
	            cont++;
	        } else {
	            cont--;
	        }
	    }

	    cont = 0;
	    for (int k = i; k <= j; k++) {			// vemos las veces que se repite
	        if (v[k] == candidato) {
	            cont++;
	        }
	    }

	    if (cont > (j - i + 1) / 2) {			// si cumple condicion es elemento mayoritario
	        return new Res(candidato, true);
	    } else {
	        return new Res(0, false);
	    }
	}

}