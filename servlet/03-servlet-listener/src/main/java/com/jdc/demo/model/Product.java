package com.jdc.demo.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private final String name;
	private final int price;
	
	private static int IDS;
	
	{
		id = ++ IDS;
	}
}
