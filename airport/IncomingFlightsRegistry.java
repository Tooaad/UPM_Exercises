package aed.airport;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Pair;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;

/**
 * A registry which organizes information on airplane arrivals.
 */
public class IncomingFlightsRegistry {

	private PriorityQueue<Long, String> incomingFlights;
	private Map<String, Entry<Long, String>> flightMap;

	/**
	 * Constructs an class instance.
	 */
	public IncomingFlightsRegistry() {
		incomingFlights = new SortedListPriorityQueue<>();
		flightMap = new HashTableMap<String, Entry<Long, String>>();
	}

	/**
	 * A flight is predicted to arrive at an arrival time (in seconds).
	 */
	public void arrivesAt(String flight, long time) {
		Entry<Long, String> flightEntry = flightMap.get(flight);
		if (flightEntry == null) {
			flightEntry = incomingFlights.enqueue(time, flight);
			flightMap.put(flight, flightEntry);
		}
		else {
			incomingFlights.replaceKey(flightEntry, time);
		}
	}

	/**
	 * A flight has been diverted, i.e., will not arrive at the airport.
	 */
	public void flightDiverted(String flight) {
		Entry<Long, String> flightEntry = flightMap.get(flight);
		if (flightEntry != null) {
			incomingFlights.remove(flightEntry);
			flightMap.remove(flight);
		}
	}

	/**
	 * Returns the arrival time of the flight.
	 * 
	 * @return the arrival time for the flight, or null if the flight is not
	 *         predicted to arrive.
	 */
	public Long arrivalTime(String flight) {
		Entry<Long, String> flightEntry = flightMap.get(flight);
		if (flightEntry == null)
			return null;
		else
			return flightEntry.getKey();
	}

	/**
	 * Returns a list of "soon" arriving flights, i.e., if any is predicted to
	 * arrive at the airport within nowTime+180 then adds the predicted earliest
	 * arriving flight to the list to return, and removes it from the registry.
	 * Moreover, also adds to the returned list, in order of arrival time, any other
	 * flights arriving withinfirstArrivalTime+120; these flights are also removed
	 * from the queue of incoming flights.
	 * 
	 * @return a list of soon arriving flights.
	 */
	public PositionList<FlightArrival> arriving(long nowTime) {
		PositionList<FlightArrival> pos = new NodePositionList<>();
		if (!incomingFlights.isEmpty()) {
			Entry<Long, String> flightEntry = incomingFlights.first();
			if (flightEntry.getKey() <= nowTime + 180) {
				FlightArrival f1 = new FlightArrival(flightEntry.getValue(), flightEntry.getKey());
				pos.addLast(f1);
				incomingFlights.remove(flightEntry);
				flightMap.remove(flightEntry.getValue());
				boolean out = false;
				while (!out && !incomingFlights.isEmpty()) {
					Entry<Long, String> secondEntry = incomingFlights.first();
					if (flightEntry.getKey() < secondEntry.getKey() - 120)
						out = true;
					else {
						FlightArrival f2 = new FlightArrival(secondEntry.getValue(), secondEntry.getKey());
						pos.addLast(f2);
						incomingFlights.remove(secondEntry);
						flightMap.remove(secondEntry.getValue());
					}
				}
			}
		}
		return pos;
	}
}
