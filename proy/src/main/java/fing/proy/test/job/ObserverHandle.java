package fing.proy.test.job;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.springframework.stereotype.Service;

@Service
public class ObserverHandle implements CoapHandler {

	public void onError() {
		System.out.print("Observable Response Error\n");
	}

	public void onLoad(CoapResponse coapResponse) {
		String payloadString = coapResponse.advanced().getPayloadString();
		String source1 = coapResponse.advanced().getSource().getCanonicalHostName();
		String source2 = coapResponse.advanced().getSource().getHostName();
		String source3 = coapResponse.advanced().getSource().getHostAddress();

		System.out.print("Observable Response: " + payloadString + ", Source 1: " + source1 + ", Source 2: " + source2 + ", Source3: " + source3 + "\n");

	}

}
