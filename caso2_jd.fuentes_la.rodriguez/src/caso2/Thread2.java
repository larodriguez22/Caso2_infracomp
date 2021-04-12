/**
 * 
 */
package caso2;

/**
 * @author jd.fuentes
 * @author la.rodriguez
 *
 */
public class Thread2 {

	/**
	 * 
	 */
	public Thread2() {
		// TODO Auto-generated constructor stub
	}

	public int[][] agregarBit(int[][] matrizBits, int pos) {
		// TODO Auto-generated method stub
		for(int i=0;i<matrizBits.length &&i!=pos;i++)
		{
			matrizBits[i]= corrimiento(matrizBits[i]);	
		}
		for(int j=0;j<matrizBits[0].length;j++)
		{
			if(matrizBits[pos][j]==1)
			{
				matrizBits[pos][j]=0;
			}
		}
		matrizBits[pos][0]=1;
		return matrizBits;
	}
	
	public int[] corrimiento(int[] arreglo) {
		boolean hayCorrimiento=false;
		for(int i=0;i<arreglo.length&&!hayCorrimiento;i++)
		{
			if(arreglo[i]==1)
			{
				arreglo[i]=0;
				try {
					arreglo[i+1]=1;
					hayCorrimiento=true;
				}catch (Exception e){}
			}
		}
		return arreglo;
		
	}

}
