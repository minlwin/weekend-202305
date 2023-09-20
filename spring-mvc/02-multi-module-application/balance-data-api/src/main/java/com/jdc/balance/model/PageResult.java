package com.jdc.balance.model;

import java.util.List;

public record PageResult<T>(
		List<T> list,
		int current,
		int limit,
		long count
		) {

}
