package ar.edu.unlam.diit.scaw.beans.heramientas;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HerramientasExprecionesRegulares
{  
    public static Boolean tieneComillasElString(String stringAVer)
    {
        //Expresion regular, 1er caracter si o si letra, resto alfanumerico
        String REGEX = "^[a-zA-Z0-9]+$";;
        
        //Crea el patron a comparar (supongo)
        Pattern pattern = Pattern.compile(REGEX);

        String inputAnalizado1  = stringAVer;//String a analizar
        
        //En base a esto se puede comparar con 3 metodos. Solo usado matches()
        Matcher matcher1 = pattern.matcher(inputAnalizado1);
        return matcher1.matches();
     
        
       
    }
}