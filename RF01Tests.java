package jenkins;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF01Tests {

	public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void saludo() {
		String[] mensajes = {
				"�Hola, @jenkins!",
				"@jenkins hola!",
				"buen d�a @jenkins",
				"@jenkins, buenas tardes",
				"hey @jenkins"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"�Hola, @delucas!",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
}
