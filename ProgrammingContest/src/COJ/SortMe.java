package COJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class SortMe {

	// split de array
	public static int[ ] atoi( String cad ) {
		String read[] = cad.split( " " );
		int res[] = new int[ read.length ];
		for ( int i = 0; i < read.length; i++ ) {
			res[ i ] = Integer.parseInt( read[ i ] );
		}
		return res;
	}
	public static int parseo( String cad, int index ) {
		return Integer.parseInt( cad.split( " " )[ index ] );
	}
	public static void main( String[ ] args ) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		String line = "";
		StringBuilder out = new StringBuilder( );
		HashMap<Character,Character> list= new HashMap<Character, Character>( );
		HashMap<Character,Character> listInverse= new HashMap<Character, Character>( );
		int times=1;
		while ( (line = in.readLine( )) != null && line.length( ) != 0 ) {
			if(line.equals( "0" ))
				break;
			list.clear( );
			int n = parseo( line, 0 );
			char arr[]= line.split( " " )[1].toCharArray( );
			for ( int i = 0; i < arr.length; i++ ) {
				list.put( arr[i], (char)(i+'a') );
				listInverse.put((char)(i+'a'), arr[i]  );
			}
			ArrayList<String> cad= new ArrayList<String>( );
			char m[][] =new char[n][];
			for ( int i = 0; i < n; i++ ) {
			    m[i]=in.readLine( ).toCharArray( );
				String w="";
				for ( int j = 0; j < m[i].length; j++ ) {
					w+=list.get( m[i][j] );
				}
				cad.add( w );
			}
			Collections.sort( cad );
			out.append( "year "+times++ +"\n");
			for ( int i = 0; i < n; i++ ) {
			    m[i]=cad.get(i).toCharArray( );
				String w="";
				for ( int j = 0; j < m[i].length; j++ ) {
					w+=listInverse.get( m[i][j] );
				}
				out.append( w+"\n" );
			}
			
		}
		System.out.print( out );
	}

}
