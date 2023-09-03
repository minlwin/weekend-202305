package com.jdc.mkt.listener;

import java.io.Serializable;

import com.jdc.mkt.entity.Times;

public interface EnableTimes extends Serializable{

	void setTimes(Times date);
	Times getTimes();
	
}
