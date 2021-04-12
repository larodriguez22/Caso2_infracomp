/**
 * 
 */
package caso2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import caso2.Thread1;


/**
 * @author jd.fuentes
 * @author la.rodriguez
 *
 */
public class Principal {
	/**
	 * Número de marcos de página en memoria RAM que el sistema le asigna al proceso
	 */
	private static int nMarcosPagina;
	
	/**
	 * Número de páginas del proceso
	 */
	private static int nPaginas;
	
	/**
	 * Datos del proceso
	 */
	private static ArrayList<Integer> leer = new ArrayList<>();
	
	/**
	 * Nivel de localidad. Manejaremos un porcentaje (un número entre 0 y 100) que indicará el porcentaje de
	 * referencias que se concentrarán en el 25% de las páginas del programa. Observe que introducimos este
	 * parámetro para poder simular el comportamiento de programas con mayor o menor nivel de localidad
	 */
	private static double nivelLocalidad;

	/**
	 * 
	 */
	public Principal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		FileReader archivo;
		try {
			Scanner scaner = new Scanner(System.in);
			System.out.println("Ingrese el número del archivo que quiere usar");
			int numero = Integer.parseInt(scaner.next());
			archivo = new FileReader("./data/referencias"+numero+".txt");
			BufferedReader lector = new BufferedReader(archivo);
	        
	        nMarcosPagina = Integer.parseInt(lector.readLine());
			nPaginas = Integer.parseInt(lector.readLine());
			nivelLocalidad = Double.parseDouble(lector.readLine());
			String line = lector.readLine();
			while(line != null){
				leer.add(Integer.parseInt(line));
				line = lector.readLine();
			}

	        lector.close();
	        archivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread2 envejecimiento = new Thread2();
		Thread1 actualizacionMemoria = new Thread1(nPaginas,nMarcosPagina, leer,envejecimiento);
		
		actualizacionMemoria.start();
		
        
		System.out.println("---------------------------------");
		System.out.println("Numero de marcos de pagina: "+nMarcosPagina);
		System.out.println("Numero de de paginas: "+nPaginas);
		System.out.println("Nivel de localidad: "+nivelLocalidad);
		System.out.println("A leer: "+leer);
		
	}

}
