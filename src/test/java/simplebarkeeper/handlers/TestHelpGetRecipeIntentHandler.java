package simplebarkeeper.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import simplebarkeeper.handlers.help.HelpGetRecipeIntentHandler;

public class TestHelpGetRecipeIntentHandler {

	private HelpGetRecipeIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new HelpGetRecipeIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput mockInput = Mockito.mock(HandlerInput.class);
		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandle() {
		final HandlerInput mockInput = TestUtil.mockHandlerInput(null, null, null, null);
		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();

		StringBuilder sr = new StringBuilder();
		String responseMessage = sr.append("Um das Rezept eines Drinks abzurufen, sag einfach: ")
                .append("Wie lautet das Rezept eines BeispielDrinks? ")
                .append("Um zum nächsten Schritt zu gelangen, sage einfach weiter. ")
                .append(" Um zum vorigen Schritt zu gelangen, sage zurück. Um einen schritt zu wiederholen, sag: nochmal").toString();

		assertTrue(response.getOutputSpeech().toString().contains(responseMessage));
	}
}
