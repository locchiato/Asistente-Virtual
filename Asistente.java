package jenkins;

import java.util.Date;
import java.util.GregorianCalendar;


public class Asistente {

	private String name;
	
	private String [] saludos = {"hola","buen día","buenas tardes","hey"};
	
	public final static String USUARIO = "delucas"; 
	
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	
	private String [] diasDeLaSemana = {"domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado"}; 
	
	
	public Asistente(){
		
	}
	
	public Asistente(String name){
		this.setName(name);
		
	}

	@SuppressWarnings("deprecation")
	public String escuchar(String mensaje) {

		if(mensaje.contains(name))
		{
			if(mensaje.contains("gracias"))
				return "No es nada, @"+ USUARIO;
			
			if(mensaje.contains("hora"))
			{
				int horas = FECHA_HORA.getHours();
				int mins = FECHA_HORA.getMinutes();
				char aop = horas<12 ? 'A':'P';
				horas = horas!=0 ? (horas>12 ? horas-12 : horas): 12;
				return String.format("@%s son las %d:%d %cM", 
						USUARIO, horas, mins, aop);
			}
			
			if(mensaje.contains("fecha")||mensaje.contains("qué día"))
			{
				if(mensaje.contains("semana")&&!mensaje.contains("fecha"))
					return "@" + USUARIO + " hoy es " + diasDeLaSemana[FECHA_HORA.getDay()];
				
				return "@" + USUARIO + " hoy es 1 de abril de 2018";
			}

			for(String saludo : saludos)
				if(mensaje.contains(saludo))
					return "¡Hola, @"+ USUARIO +"!";
		}

		return "Disculpa... no entiendo el pedido, @" + USUARIO + " ¿podrías repetirlo?";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
