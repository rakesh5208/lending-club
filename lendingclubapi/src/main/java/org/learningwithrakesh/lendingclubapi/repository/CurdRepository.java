package org.learningwithrakesh.lendingclubapi.repository;

import java.util.List;

public interface CurdRepository<T> {
	public List<T> getAll();

	public T save(T data);

	public T getById(Long id);
}
