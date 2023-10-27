package aed.airport;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;


public class Tests {
	
	@Test
	public void arrivesAt() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("AA0000", 1000);
		
		assertEquals(1000, airport.arrivalTime(new String ("AA0000")));
	}
	
	@Test
	public void flightDiverted() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("AA0000", 1000);
		airport.flightDiverted("AA000");
		
		assertEquals(null, airport.arrivalTime("AA000"));
	}
	
	@Test
	public void arrivalTime() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("AA0000", 1000);
		
		assertEquals(1000, airport.arrivalTime(new String ("AA0000")));
	}
	
	@Test
	public void arriving() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("AA0000", 1000);
		airport.arrivesAt("AA1111", 180);
		airport.arrivesAt("AA2222", 500);
		
		
		PositionList<FlightArrival> vuelos = airport.arriving(180);
		Position<FlightArrival> cursorVuelos = vuelos.first();
		
		while(cursorVuelos.element().equals(null)) {
			assertEquals(180, cursorVuelos.element().arrivalTime());
			cursorVuelos = vuelos.next(cursorVuelos);
		}
	}
}