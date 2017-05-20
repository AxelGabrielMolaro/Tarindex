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
	 * @param listaDeUsuarios
	 * @return
	 */
	public static List<Usuario> encriptarContraseñaMD5TodaUnListaDeUsuarios(List<Usuario> listaDeUsuarios)
	{
		
		for(Usuario u : listaDeUsuarios)
		{
			u.setContrasena(DigestUtils.md5Hex(u.getContrasena()));
		}
		
		
		return listaDeUsuarios;
	}
}
