package fing.proy.test.coapserver;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP.Type;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;


public class CoAPServer extends CoapResource {	 

	String mValorOtro = "[localhost]";
	
	public CoAPServer(String name) {
		super(name);
		setObservable(true); // enable observing
		setObserveType(Type.CON); // configure the notification type to CONs
		getAttributes().setObservable(); // mark observable in the Link-Format
		
		// schedule a periodic update task, otherwise let events call changed()
		Timer timer = new Timer();
		timer.schedule(new UpdateTask(), 0, 10000);
		
		if(!name.contains("network") && !name.contains("status"))
			mValorOtro = "1";
	}
	
	private class UpdateTask extends TimerTask {
		@Override
		public void run() {
			// .. periodic update of the resource
			
			if(!mValorOtro.contains("localhost")){
				Random random = new Random();
				mValorOtro = String.valueOf( random.nextInt(100) );
			}
			changed(); // notify all observers
		}
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.setMaxAge(1); // the Max-Age value should match the update interval
		exchange.respond(mValorOtro);
	}
	
	@Override
	public void handleDELETE(CoapExchange exchange) {
		delete(); // will also call clearAndNotifyObserveRelations(ResponseCode.NOT_FOUND)
		exchange.respond("delete");
	}
	
	@Override
	public void handlePUT(CoapExchange exchange) {
		// ...
		mValorOtro = exchange.getRequestText();
		exchange.respond("change");
		changed(); // notify all observers
	}
	
	public static void main(String[] args) {
		CoapServer server = new CoapServer();//bbbb::212:7402:2:202
		server.add(new CoAPNetworkStatus("network"));
		server.add(new CoAPServer("temperature"));
		server.add(new CoAPServer("humedad"));
		server.start();
		
//		 Resource res = server.getRoot();
//         List<Endpoint> endpoints = server.getEndpoints();
//         
//         String a = "";
		
//         server.destroy();
	}
	
	

public static class CoAPNetworkStatus extends CoapResource {
	
	String mValorOtro = "[localhost]";
	
	public CoAPNetworkStatus(String name) {
		super(name);
		setObservable(true); // enable observing
		setObserveType(Type.CON); // configure the notification type to CONs
		getAttributes().setObservable(); // mark observable in the Link-Format
		add(new CoAPServer("status"));
		
		// schedule a periodic update task, otherwise let events call changed()
		Timer timer = new Timer();
		timer.schedule(new UpdateTask(), 0, 10000);
	}
	
	private class UpdateTask extends TimerTask {
		@Override
		public void run() {
			// .. periodic update of the resource
			changed(); // notify all observers
		}
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.setMaxAge(1); // the Max-Age value should match the update interval
//		exchange.respond("{bbbb::212:7409:9:909;bbbb::212:7401:1:101}");
		exchange.respond(mValorOtro);
	}
	
	@Override
	public void handleDELETE(CoapExchange exchange) {
		delete(); // will also call clearAndNotifyObserveRelations(ResponseCode.NOT_FOUND)
		exchange.respond("delete");
	}
	
	@Override
	public void handlePUT(CoapExchange exchange) {
		// ...
		mValorOtro = exchange.getRequestText();
		exchange.respond("change");
		changed(); // notify all observers
	}
	
	
}


}

