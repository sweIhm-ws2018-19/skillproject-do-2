package simplebarkeeper.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import simplebarkeeper.States;

public class TestGetDrinkChooseOptionIntentHandler {

	private GetDrinkChooseOptionIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetDrinkChooseOptionIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_OPTION);

		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleFlavour() {
		String answer = "geschmack";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_OPTION);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseOption(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Wie soll der Drink schmecken?"));
	}

	@Test
	public void testHandleIngredients() {
		String answer = "zutat";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_OPTION);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseOption(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Welche Zutat soll der Drink enthalten?"));
	}

	@Test
	public void testHandleRandom() {
		String answer = "zufall";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_OPTION);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseOption(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Dein Barkeeper empfiehlt dir"));
	}
	

	@Test
	public void testHandleDidNotUnderstand() {
		String answer = "sein oder dicht sein, das ist hier die Frage!";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CONTAINS_ALCOHOL);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseOption(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Ich habe dich leider nicht verstanden."));
	}
	// TODO Implement missing test classes
}
