package simplebarkeeper.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class TestGetFavouriteIntentHandler {

	private GetFavouriteIntentHandler testHandler;
	private SetFavouriteIntentHandler testSetter;

	@Before
	public void setup() {
		testHandler = new GetFavouriteIntentHandler();
		testSetter = new SetFavouriteIntentHandler();
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
		Map<String, Object> persAtt = new HashMap<String, Object>();
		final HandlerInput mockInputSetter = TestUtil.mockHandlerInputFavourite(mockDrink, null, persAtt, null);
		testSetter.handle(mockInputSetter);
		final HandlerInput mockInput = TestUtil.mockHandlerInputFavourite(null, null, persAtt, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString()
				.contains("Dein Lieblingsdrink ist"));

	}
	
	@Test
	public void testHandleNoneSavedYet() {

		Map<String, Object> persAtt = new HashMap<String, Object>();
		final HandlerInput mockInput = TestUtil.mockHandlerInputFavourite(null, null, persAtt, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString()
				.contains("Es ist noch kein Favorit abgespeichert"));

	}
}
