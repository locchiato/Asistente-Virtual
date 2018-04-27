package jenkins;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF03Tests {

	public final static String USUARIO = "delucas";
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void hora() {
		String[] mensajes = {
				"�qu� hora es, @jenkins?",
				"@jenkins, la hora por favor",
				"me dec�s la hora @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
				"@delucas son las 3:15 PM",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void fecha() {
		String[] mensajes = {
				"�qu� d�a es, @jenkins?",
				"@jenkins, la fecha por favor",
				"me dec�s la fecha @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es 1 de Abril de 2018",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void diaDeLaSemana() {
		String[] mensajes = {
				"�qu� d�a de la semana es hoy, @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es domingo",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
}
