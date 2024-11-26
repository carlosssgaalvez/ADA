import java.util.Scanner;
import java.util.Arrays;

public class Matrices {
	public static void main( String[] args){
	    try (Scanner sc = new Scanner(System.in)){
    	    int n = Integer.parseInt(sc.next())-1;
    	    String[] sdim = sc.next().split(",");
    	    int[] dim = new int[sdim.length];
    	    for (int i = 0 ; i < dim.length; i++) dim[i] = Integer.parseInt(sdim[i]);
    	    
    	    int k = MatricesBottomUp(n,dim);
    	    
    	    System.out.println(Integer.toString(k));
	    }
	}
	
	public static int MatricesBottomUp(int n, int dim[]) {
	    int[][] m = new int[n+1][n+1];
	    
	    for (int i=1; i<=n; i++)
	        m[i][i] = 0;
	    for (int L=2; L<=n; L++) {
	        for (int i=1; i<=n-L+1; i++) {
	            int j = i+L-1;
	            m[i][j] = Integer.MAX_VALUE;

	            for (int k=i; k<=j-1; k++) {
	                int q = m[i][k] + m[k+1][j] + dim[i-1]*dim[k]*dim[j];
	                if (q < m[i][j])
	                    m[i][j] = q;
	            }
	        }
	    }

	    return m[1][n];

	}
}//end class Matrices