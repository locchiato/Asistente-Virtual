package jenkins;

import java.util.Date;
import java.util.GregorianCalendar;


public class Asistente {

	private String name;
	
	private String [] saludos = {"hola","buen día","buenas tardes","hey"};
	
	public final static String USUARIO = "delucas"; 
	
	public final static Date FECHA_HORA = 
			new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	
	private String [] diasDeLaSemana = {"domingo", "lunes", "martes", "miercoles", 
										"jueves", "viernes", "sabado"};
	private String [] mesesDelAño = {"Enero", "Febrero", "Marzo", "Abril", "Junio", 
									"Julio", "Agosto", "Septiembre", "Octubre", 
									"Noviembre", "Diciembre"};
	
	private char [] signos = {'+','-','*','/','(',')','%','^'};
	
	
	public Asistente(){
		
	}
	
	public Asistente(String name){
		this.setName(name);
		
	}

	@SuppressWarnings("deprecation")
	public String escuchar(String mensaje) {

		// me fijo si se dirije a jenkins..
		if(mensaje.contains(name))
		{
			if(mensaje.contains("cuánto es"))
			{
				int i=0;
				while(!esDigito(mensaje.charAt(i)) && mensaje.charAt(i) != '(' && i<mensaje.length())
					i++;
				if(i<mensaje.length())
				{
					return "@" + USUARIO + " " + resolver(mensaje.substring(i));
				}
				return "Disculpa... no entiendo el pedido, @" + USUARIO + " ¿podrías repetirlo?";
			}
			
			// busco la palabra hora (RF03)
			if(mensaje.contains("hora"))
			{
				int horas = 
						FECHA_HORA.getHours();
				int mins = 
						FECHA_HORA.getMinutes();
				char aop = 
						horas<12 ? 'A':'P';
				
				horas = horas!=0 ? (horas>12 ? horas-12 : horas): 12;
				
				return String.format(
						"@%s son las %d:%d %cM", 
							USUARIO, horas, mins, aop);
			}
			
			//compruebo si habla del día (RF03)
			if(mensaje.contains("fecha")||mensaje.contains("qué día"))
			{
				String cin = "@" + USUARIO + " hoy es ";
				
				if(mensaje.contains("semana")&&!mensaje.contains("fecha"))
					return cin + diasDeLaSemana[FECHA_HORA.getDay()];
				
				int año = 1900 + FECHA_HORA.getYear();
				
				return cin + FECHA_HORA.getDate() +
						" de " + mesesDelAño[FECHA_HORA.getMonth()] + " de " + año;
			}

			// busco la palabra gracias (RF02)
			if(mensaje.contains("gracias"))
				return "No es nada, @"+ USUARIO;
						
			// compruebo si esta saludando (RF01)
			for(String saludo : saludos)
				if(mensaje.contains(saludo))
					return "¡Hola, @"+ USUARIO +"!";
		}

		// no se lograron coincidencias
		return "Disculpa... no entiendo el pedido, @" + USUARIO + " ¿podrías repetirlo?";
	}

	private Integer resolver(String mensaje) {
		
		int l = mensaje.length();
		if(l==1)
			return (int)mensaje.charAt(0)-48;
		
		int i = 0;
		int aux = 0;
		int val = 0;

		char op = '\0';	
		
		if(mensaje.charAt(i) == '(')
		{
			i++;
			val = resolver(mensaje.substring(i));
			while(mensaje.charAt(i++) != ')') {}
		}
		else
			if(esDigito(mensaje.charAt(i)))
				val = getValor(mensaje);
				
		while(i<l && !esSimbolo(mensaje.charAt(i)))
			i++;
	
		if(!(i<l))
			return val;
		
		if(mensaje.charAt(i)==')' || !esSimbolo(mensaje.charAt(i)))
			return val;
		
		op = mensaje.charAt(i++);
		
		while(i<l && !esSimbolo(mensaje.charAt(i)) && !esDigito(mensaje.charAt(i)))
			i++;
		
		if(i==l || mensaje.charAt(i)==')')
			return val;
		
		System.out.println("|val: " + val);
		
		if(mensaje.charAt(i) == '(')
		{
			i++;
			aux = resolver(mensaje.substring(i));
			while(mensaje.charAt(++i) != ')') {}
		}
		else
			if(esDigito(mensaje.charAt(i)))
					aux = resolver(mensaje.substring(i));
		
		System.out.println("|aux: " + aux);
		
		return resultado(val,aux,op);
	}

	private Integer getValor(String cad) {
		int i = 0,
			val = 0;
		while(i<cad.length() && esDigito(cad.charAt(i)))
		{
			val = val*10 + ((int)cad.charAt(i) -48);
			i++;
		}
		return val;
	}

	private boolean esSimbolo(char charAt) {
		 
		for(char c : signos)
			if(c == charAt)
				return true;
		return false;
	}

	private boolean esDigito(char charAt) {
		
		return charAt<=57 && charAt>=48;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int resultado(int val, int aux, char op)
	{
		if(op == '+')
			return val + aux;	
		if(op == '-')
			return val - aux;
		if(op == '*')
			return val * aux;
		if(op == '/')
			return val / aux;
		if(op == '%')
			return (val * aux)/100;
		if(op == '^')
			return (int) Math.pow(val, aux);
		return val;
	}
}
