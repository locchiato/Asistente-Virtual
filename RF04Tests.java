package jenkins;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF04Tests {

	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void diaDentroDe() {
		Assert.assertEquals(
				"@delucas será el martes 3 de abril de 2018",
				jenkins.escuchar("@jenkins qué día será dentro de 2 días?")
			);
		
		Assert.assertEquals(
				"@delucas será el viernes 1 de junio de 2018",
				jenkins.escuchar("@jenkins qué día será dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas será el miércoles 1 de abril de 2020",
				jenkins.escuchar("@jenkins qué día será dentro de 2 años?")
			);
	}
	
	@Test
	public void diaHace() {
		Assert.assertEquals(
				"@delucas fue sábado 31 de marzo de 2018",
				jenkins.escuchar("@jenkins qué día fue ayer?")
			);
		
		Assert.assertEquals(
				"@delucas fue jueves 29 de marzo de 2018",
				jenkins.escuchar("@jenkins qué día fue hace 3 días?")
			);
		
		Assert.assertEquals(
				"@delucas fue el jueves 1 de febrero de 2018",
				jenkins.escuchar("@jenkins qué día fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas fue el viernes 1 de abril de 2016",
				jenkins.escuchar("@jenkins qué día fue hace 2 años?")
			);
	}
	
	@Test
	public void tiempoDesde() {
		Assert.assertEquals(
				"@delucas entre el 1 de abril de 2017 y el 1 de abril de 2018 pasaron 365 días",
				jenkins.escuchar("@jenkins cuántos días pasaron desde el 1 de abril de 2017?")
			);
		
		// agregar casos de prueba
	}
	
	@Test
	public void tiempoHasta() {
		Assert.assertEquals(
				"@delucas faltan 9 días",
				jenkins.escuchar("@jenkins cuántos días faltan para el 10 de abril?")
			);
		
		// agregar casos de prueba
	}
	
}
