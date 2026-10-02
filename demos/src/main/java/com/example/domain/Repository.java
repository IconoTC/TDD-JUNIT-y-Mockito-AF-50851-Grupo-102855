package com.example.domain;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
	List<E> read();
	Optional<E> read(K key);
	E save(E item);
	void delete(E item);
	void deleteById(K key);
}
