package com.jdc.mkt.entity;

import com.jdc.mkt.entity.Product.Size;

public record ProductDto(String name,int price,Size size,String cat) {}
