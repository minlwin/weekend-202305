package com.jdc.mkt.model.repo.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T,ID> extends JpaRepository<T, ID> {

	List<T> search(String jpql, Map<String, Object> params);
}
