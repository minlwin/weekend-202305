package com.jdc.onestop.weekend.products.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jdc.onestop.weekend.products.model.entity.Product.Status;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PRODUCT_HISTORY")
@EntityListeners(value = AuditingEntityListener.class)
public class ProductHistory {
	
	@EmbeddedId
	private ProductHistoryPK id;

	@ManyToOne(optional = false)
	@JoinColumn(insertable = false, updatable = false)
	private Product product;
	
	@Column(nullable = false)
	private String name;
	private int price;
	private String description;
	@ManyToMany
	private List<Category> categories = new ArrayList<>();
	@ElementCollection
	@MapKeyColumn(name = "feature_name")
	private Map<String, String> features = new LinkedHashMap<>();
	
	private Status status;
	
	private String remark;
	
	@CreatedDate
	private LocalDateTime createAt = LocalDateTime.now();
	
	public ProductHistory(Product product) {
		this.product = product;
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.categories = product.getCategories();
		this.features = product.getFeatures();
		this.status = product.getStatus();
		this.id = new ProductHistoryPK();
		this.id.setProductId(product.getId());
	}

}
