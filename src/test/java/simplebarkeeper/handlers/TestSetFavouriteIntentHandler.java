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

public class TestSetFavouriteIntentHandler {

	private SetFavouriteIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new SetFavouriteIntentHandler();
	}

	@Test
	public void testCanHandle() {
		final HandlerInput mockInput = Mockito.mock(HandlerInput.class);
		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleKnownDrink() {
		String mockDrink = "sex on the beach";
		final HandlerInput mockInput = TestUtil.mockHandlerInputFavourite(mockDrink, null, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString()
				.contains("sex on the beach wurde als dein neuer Lieblingsdrink gespeichert"));

	}

	@Test
	public void testHandleUnknownDrink() {
		String mockDrink = "unbekannter Drink";
		final HandlerInput mockInput = TestUtil.mockHandlerInputFavourite(mockDrink, null, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Dieser Drink ist mir leider nicht bekannt"));

	}
}
