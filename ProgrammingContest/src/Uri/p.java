package Uri;
import java.util.Scanner;


public class p {

	public static void main( String[ ] args ) {
		double res=1;
		Scanner scan = new Scanner( System.in );
		
		System.out.println("ingrese el numero de repetidas");
		int n=scan.nextInt( );
		System.out.println("ingrese el numero de sobres");
		int sobres=scan.nextInt( );
		
		for ( int i = 599; i >(599)-(sobres)+n+1  ; i-- ) {
			res*=599/(600*1.0);
			
		}
		
		for ( int i = 0; i < n+1; i++ ) {
			res*=1/(600*1.0);
		}
		//System.out.println(res*(1/(600*1.0))*(1/(600*1.0)));
		System.out.println(res);
	}

}
