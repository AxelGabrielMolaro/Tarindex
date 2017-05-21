package ar.edu.unlam.diit.scaw.beans.heramientas;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public class HerramientasEncriptar {
	
	public static String encriptarMD5(String stringSinEditar)
	{
		String stringEncriptado = DigestUtils.md5Hex(stringSinEditar);
		return stringEncriptado;
	}
	
	
	/**Encripta las contraseñas de una lista de usuaios
	 * @param listaFinal
	 * @return
	 */
	public static List<HerramientasUsuarioEspecial>  encriptarContraseñaMD5TodaUnListaDeUsuarios(List<HerramientasUsuarioEspecial> listaFinal)
	{
		
		for(HerramientasUsuarioEspecial u : listaFinal)
		{
			u.setContrasena(DigestUtils.md5Hex(u.getContrasena()));
	
		}
		
		
		return listaFinal;
	}
	
	
}
