package jenkins;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF02Tests {

	public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void agradecimiento() {
		String[] mensajes = {
				"¡Muchas gracias, @jenkins!",
				"@jenkins gracias",
				"gracias @jenkins"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"No es nada, @" + USUARIO,
					jenkins.escuchar(mensaje)
			);
		}
	}
	
}
