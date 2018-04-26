package jenkins;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF06Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void calculos() {
		Assert.assertEquals(
				"@delucas 3",
				jenkins.escuchar("@jenkins cu�nto es 1 + 2")
			);
		
		Assert.assertEquals(
				"@delucas 1",
				jenkins.escuchar("@jenkins cu�nto es 5 - 2 * 2")
			);
		
		Assert.assertEquals(
				"@delucas 10",
				jenkins.escuchar("@jenkins cu�nto es el 10% de 100")
			);
		
		Assert.assertEquals(
				"@delucas 42",
				jenkins.escuchar("@jenkins cu�nto es el 17 + 5 ^ 2")
			);
		
		// agregar otros casos
	}
	
	@Test
	public void calculosCompuestos() {
		Assert.assertEquals(
				"@delucas -6",
				jenkins.escuchar("@jenkins cu�nto es (4-8)*2 + 4 / ( 1 + 1)")
			);
		
		// agregar otros casos
	}

}
