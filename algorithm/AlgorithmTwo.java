package com.app.jkko;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AlgorithmTwo {
	private HashMap<String, Set<String>> route = new HashMap<>();;
	private HashMap<String, Set<String>> stop = new HashMap<>();;

	public void addStop(String idRoute, String idStop) {
		route.computeIfAbsent(idRoute, k -> new HashSet<>()).add(idStop);
		stop.computeIfAbsent(idStop, k -> new HashSet<>()).add(idRoute);
	}

	public void deleteStop(String idRoute, String idStop) {
		if (route.containsKey(idRoute)) {
			route.get(idRoute).remove(idStop);
		}

		if (stop.containsKey(idStop)) {
			stop.get(idStop).remove(idRoute);
		}
	}

	public Set<String> routeStop(String idStop) {
		return stop.getOrDefault(idStop, new HashSet<>());
	}

	public Set<String> stopRoute(String idRoute) {
		return route.getOrDefault(idRoute, new HashSet<>());
	}

}

/**
 *
 *El uso de HashMap y HashSet asegura que todas las operaciones se realicen en tiempo constante promedio,
 * lo que hace que esta solución sea eficiente y escalable.
 *
 */

