/**
 * 
 */
package caso2;

import java.util.ArrayList;

/**
 * @author jd.fuentes
 * @author la.rodriguez
 *
 */
public class Thread1  extends Thread{
	
	/**
	 * Arreglo de bits
	 */
	private int[] bits;	

	/**
	 * Matriz de bits
	 */
	private int[][] matrizBits;	
	
	/**
	 * Arreglo de paginas
	 */
	private boolean[] paginas;	
	
	/**
	 * Arreglo de marcos
	 */
	private boolean[] marcos;	
	
	/**
	 * Arreglo de referencias
	 */
	private ArrayList<Integer> referencias;
	
	/**
	 * Numero de fallos
	 */
	private int fallos;
	
	/**
	 * Referencia al thread2
	 */
	private Thread2 thread2;

	/**
	 * Clase Thread 1
	 */
	public Thread1(int nPaginas,int nMarcos, ArrayList<Integer> pReferencias, Thread2 pThread2) {
		// TODO Auto-generated constructor stub
		fallos=0;
		matrizBits =new int[nMarcos][8];
		for(int i=0; i<matrizBits.length;i++) {
			for(int j=0;j<matrizBits[0].length;j++) {
				matrizBits[i][j]=0;
			}
		}
		System.out.println(matrizBits);
		thread2 = pThread2;
		paginas = new boolean[nPaginas];
		marcos = new boolean[nMarcos];
		for(int i=0; i<marcos.length;i++) {
			marcos[i]=false;
		}
		referencias = pReferencias;
	}
	
	public void run()
	{
		
		for(int i = 0; i<referencias.size(); i++) {
			int pos = isEmpty(marcos);
			
			if(pos!=-1)
			{
				matrizBits = thread2.agregarBit(matrizBits,pos);
				revisarPaginas();
				contFallas(referencias.get(i));//errores iniciales
				marcos[pos]=true;
				paginas[referencias.get(i)]=true;
				System.out.println(referencias.get(i));
			}
			else if(pos==-1 && paginas[referencias.get(i)]){//sin error
				System.out.println(referencias.get(i));
				matrizBits = thread2.agregarBit(matrizBits,pos);
				revisarPaginas();
			}
			else {//hay reemplazo
				
				System.out.println(referencias.get(i));
				fallos++;
			}
		}
		System.out.println("Numero de fallos: "+fallos);
	}
	
	public void revisarPaginas() {
		for(int i=0; i<matrizBits.length;i++)
		{
			boolean hayUno=false;
			for(int j=0;j<matrizBits[0].length&&!hayUno;j++)
			{
				if(matrizBits[i][j]==1) {
					hayUno=true;
				}
				else if(j==matrizBits[0].length-1) {
					marcos[i]=false;
					paginas[i]=false;
				}
			}
		}
	}
	
	public void contFallas(int ref) {
		if(!paginas[ref]) {
			fallos++;
		}
	}

	private int isEmpty(boolean[] lista) {
		for(int i = 0; i<lista.length; i++) {
			if(!lista[i]) {
				return i;
			}
		}
		return -1;
	}

}
