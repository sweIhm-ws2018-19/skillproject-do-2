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

import simplebarkeeper.handlers.help.HelpSetFavouriteIntentHandler;

public class TestHelpSetFavouriteIntentHandler {

	private HelpSetFavouriteIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new HelpSetFavouriteIntentHandler();

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
		String responseMessage = sr.append("Um einen Drink als Favoriten abzuspeichern, sag einfach: ")
				.append("Speicher bitte den Drink BeispielDrink als Favoriten ab!").toString();

		assertTrue(response.getOutputSpeech().toString().contains(responseMessage));
	}
}
