package ar.edu.unlam.diit.scaw.beans.heramientas;

public class HerramientaValidaciones {
	
	/**Si es menor la cantidad de un string que le paso devuelve false
	 * @param stringAValidad
	 * @param cantidadMinimaDeCaracteres
	 * @return
	 */
	public static Boolean elLargoStringPasadoEsMayorALaCantidadPasada(String stringAValidad,Integer cantidadCaracteresAPasar)
	{
		if(stringAValidad.length()<cantidadCaracteresAPasar)
		{
			return false;
		}
		return true;
	}
	
}
