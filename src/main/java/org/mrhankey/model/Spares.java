package org.mrhankey.model;

import java.io.Serializable;

public class Spares implements Serializable {
	private String articul;
	private String name;
	private String price;
	private String weight;
	private String production;
	private String subgroup;
	private String urlImg;
	private String applicability;

	public String getArticul() {
		return articul;
	}

	public void setArticul(String articul) {
		this.articul = articul;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String string) {
		this.price = string;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(String subgroup) {
		this.subgroup = subgroup;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
	
		builder.append(articul);
		
		builder.append(name);
		
		builder.append(price);
		
		builder.append(weight);
		
		builder.append(production);
		
		builder.append(subgroup);
		
		builder.append(urlImg);
		
		builder.append(applicability);
		
		return builder.toString();
	}
	

}
