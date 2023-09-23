package com.jdc.mkt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailsPk {

	@Column(name="sales_id")
	private int salesId;
	@Column(name = "product_id")
	private int productId;
}
