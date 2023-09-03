package com.jdc.mkt.entity;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Times {
	
	private LocalDateTime createTimes;
	private LocalDateTime updateTimes;
	private LocalDateTime removeTimes;
	
		
}
