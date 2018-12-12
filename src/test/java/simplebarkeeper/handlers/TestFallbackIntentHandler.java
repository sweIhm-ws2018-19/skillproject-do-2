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

public class TestFallbackIntentHandler {

	private FallbackIntentHandler testHandler;

	@Before
	public void setup() {
		testHandler = new FallbackIntentHandler();
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
		String responseMessage = sr.append(
				"Tut mir leid, ich habe dich nicht verstanden. Bei Fragen steht Ihnen die Hilfe gerne zur Verfügung")
				.toString();
		assertTrue(response.getOutputSpeech().toString().contains(responseMessage));
	}
}
