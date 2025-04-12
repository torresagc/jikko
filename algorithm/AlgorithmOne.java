package com.app.jkko;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class AlgorithmOne {

		public static List<String> topClient(List<Transaction> transactionList, LocalDateTime startTime, LocalDateTime endTime) {
			Map<String, Integer> topClients = new HashMap<>();
			for (Transaction tx : transactionList) {
				if (!tx.getPurchaseDate().isBefore(startTime) && !tx.getPurchaseDate().isAfter(endTime)) {
					String clientId = tx.getClientId();
					topClients.put(clientId, topClients.getOrDefault(clientId, 0) + 1);
				}
			}
			PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(Map.Entry.comparingByValue());

			for (Map.Entry<String, Integer> entry : topClients.entrySet()) {
				heap.offer(entry);
				if (heap.size() > 10) {
					heap.poll();
				}
			}
            return heap.stream()
					.sorted((a, b) -> b.getValue().compareTo(a.getValue()))
					.map(Map.Entry::getKey)
					.collect(Collectors.toList());
		}

	static class Transaction {
		private LocalDateTime purchaseDate;
		private String clientId;
		private double amount;

		public LocalDateTime getPurchaseDate() {
			return purchaseDate;
		}

		public String getClientId() {
			return clientId;
		}

		public double getAmount() {
			return amount;
		}
	}
}

/**
 * n = número total de transacciones
 * m = número de clientes únicos map
 * k = número de top clientes (10)
 *Tiempo
 * O(n) -> recorrer la lista de entrada
 * O(m log k) -> obtener el top 10
 *
 * espacial
 * HashMap topClients:
 * Almacena un conteo por cliente → hasta m entradas → O(m)
 * PriorityQueue heap:
 * Tamaño máximo de 10 → O(k)
 * Resultado final (lista de 10 Strings):
 *
 * O(k)
 */

