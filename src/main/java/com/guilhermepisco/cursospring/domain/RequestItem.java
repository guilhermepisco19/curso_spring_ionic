package com.guilhermepisco.cursospring.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RequestItem  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private RequestedItemPK id = new RequestedItemPK();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public RequestItem() {
		
	}
	
	public RequestItem(Request request, Product product, Double discount, Integer quantity, Double price) {
		super();
		id.setProduct(product);
		id.setRequest(request);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}
	
	/* Put "get" on the method's name, so JSON recognize it and serialize the value */
	public double getSubTotal() {
		return (price - discount) * quantity;
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	@JsonIgnore
	public Request getRequest() {
		return id.getRequest();
	}
	
	public void setRequest(Request request) {
		id.setRequest(request);
	}
	
	public RequestedItemPK getId() {
		return id;
	}

	public void setId(RequestedItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestItem other = (RequestItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(", Qty: ");
		builder.append(getQuantity());
		builder.append(", Unitary price: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
	
}
