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

public class TestLaunchRequestIntentHandler {

	private LaunchRequestHandler testHandler;

	@Before
	public void setup() {
		testHandler = new LaunchRequestHandler();
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
		assertTrue(response.getOutputSpeech().toString().contains("Hallo. Wie kann ich dir behilflich sein?"));
		assertTrue(response.getReprompt().toString().contains("Falls du Hilfe brauchst, sag einfach: hilf mir"));
	}
}
