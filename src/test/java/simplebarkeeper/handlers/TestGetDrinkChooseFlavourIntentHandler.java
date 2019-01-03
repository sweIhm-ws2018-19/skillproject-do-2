package simplebarkeeper.handlers;

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

public class TestGetDrinkChooseFlavourIntentHandler {

	private GetDrinkChooseFlavourIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new GetDrinkChooseFlavourIntentHandler();
	}

	@Test
	public void testCanHandle() {
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_FLAVOUR);

		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseAlcohol(null, sessAtt, null, null);

		when(mockInput.matches(any())).thenReturn(true);
		assertTrue(testHandler.canHandle(mockInput));
	}

	@Test
	public void testHandleSweet() {
		String answer = "süß";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_FLAVOUR);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseFlavour(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("Dein Barkeeper empfiehlt dir:") || response.toString()
				.contains("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt"));
	}

	@Test
	public void testHandleBitter() {
		String answer = "bitter";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_FLAVOUR);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseFlavour(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("Dein Barkeeper empfiehlt dir:") || response.toString()
				.contains("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt"));
	}

	@Test
	public void testHandleSour() {
		String answer = "sauer";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "false");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_FLAVOUR);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseFlavour(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("Dein Barkeeper empfiehlt dir:") || response.toString()
				.contains("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt"));
	}

	@Test
	public void testHandleUnknownFlavour() {
		String answer = "Katachanga";
		Map<String, Object> sessAtt = new HashMap<String, Object>();
		sessAtt.put(States.GET_DRINK_CONTAINS_ALCOHOL_KEY, "true");
		sessAtt.put(States.GET_DRINK_STATE_KEY, States.GET_DRINK_CHOOSE_FLAVOUR);
		final HandlerInput mockInput = TestUtil.mockHandlerInputChooseFlavour(answer, sessAtt, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.toString().contains("Dieser Geschmack ist mir leider nicht bekannt"));
	}

}
