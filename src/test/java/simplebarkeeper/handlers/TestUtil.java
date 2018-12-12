package simplebarkeeper.handlers;

import static org.mockito.Mockito.when;

import java.util.Map;

import org.mockito.Mockito;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

public class TestUtil {

	public static HandlerInput mockHandlerInput(String input, Map<String, Object> sessionAttributes,
			Map<String, Object> persistentAttributes, Map<String, Object> requestAttributes) {

		final AttributesManager mockAttributesManager = Mockito.mock(AttributesManager.class);
		when(mockAttributesManager.getSessionAttributes()).thenReturn(sessionAttributes);
		when(mockAttributesManager.getPersistentAttributes()).thenReturn(persistentAttributes);
		when(mockAttributesManager.getRequestAttributes()).thenReturn(requestAttributes);

		final RequestEnvelope mockRequestEnvelope = RequestEnvelope.builder()
				.withRequest(IntentRequest.builder()
						.withIntent(Intent.builder()
								.putSlotsItem("Drink",
										Slot.builder().withName("Drink").withValue(input).build())
								.build())
						.build())
				.build();

		final HandlerInput mockHandlerInput = Mockito.mock(HandlerInput.class);
		when(mockHandlerInput.getAttributesManager()).thenReturn(mockAttributesManager);
		when(mockHandlerInput.getResponseBuilder()).thenReturn(new ResponseBuilder());
		when(mockHandlerInput.getRequestEnvelope()).thenReturn(mockRequestEnvelope);

		return mockHandlerInput;

	}
}
