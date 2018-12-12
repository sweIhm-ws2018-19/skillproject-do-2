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

public class TestListIngredientIntentHandler {

	private ListIngredientsIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new ListIngredientsIntentHandler();
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
		final HandlerInput mockInput = TestUtil.mockHandlerInput(mockDrink, null, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains(
				"sex on the beach enthält folgende Zutaten: 4 Zentiliter granini Orange, 3 Zentiliter granini Pfirsich, 3 Zentiliter granini Ananas, 3 Zentiliter granini Cranberry und 4 Zentiliter Wodka"));

	}

	@Test
	public void testHandleUnknownDrink() {
		String mockDrink = "mockito spezial";
		final HandlerInput mockInput = TestUtil.mockHandlerInput(mockDrink, null, null, null);

		final Optional<Response> res = testHandler.handle(mockInput);

		assertTrue(res.isPresent());
		final Response response = res.get();
		assertTrue(response.getOutputSpeech().toString().contains("Dieser Drink ist mir leider nicht bekannt"));

	}
}