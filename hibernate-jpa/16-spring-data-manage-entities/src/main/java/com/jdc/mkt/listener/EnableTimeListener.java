package com.jdc.mkt.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.jdc.mkt.entity.Times;

public class EnableTimeListener {

	@PrePersist
	public void prePresist(Object obj) {
		if(obj instanceof EnableTimes enitity) {
			var times = enitity.getTimes();
			
			if(null == times) {
				
				times = new Times();
				times.setCreateTimes(LocalDateTime.now());
			}
			times.setCreateTimes(LocalDateTime.now());
			enitity.setTimes(times);
			}
	}
	
	@PreUpdate
	public void preUpdate(Object obj) {
		
		if(obj instanceof EnableTimes entity) {
			var times = entity.getTimes();
			
			if(null == times) {
				times = new Times();
				times.setUpdateTimes(LocalDateTime.now());
			}
			times.setUpdateTimes(LocalDateTime.now());
			entity.setTimes(times);
		}
	}
	
	@PreRemove
	public void preRemove(Object obj) {
		if(obj instanceof EnableTimes entity) {
			var times = entity.getTimes();
			
			if(null == times) {
				times = new Times();
				times.setRemoveTimes(LocalDateTime.now());
			}
			times.setRemoveTimes(LocalDateTime.now());
			entity.setTimes(times);
		}
	}
}
