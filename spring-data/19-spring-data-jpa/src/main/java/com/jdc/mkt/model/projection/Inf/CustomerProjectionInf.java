package com.jdc.mkt.model.projection.Inf;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerProjectionInf {

	String getName();
	String getPhone();
	String getEmail();
	@Value("#{target.name+'   '+target.phone+'  '+target.email}")
	String showDisplay();
}
