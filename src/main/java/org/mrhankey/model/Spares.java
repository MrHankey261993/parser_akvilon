package org.mrhankey.model;

import java.io.Serializable;

public class Spares implements Serializable {
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articul;
     private String name;
     private String Price;
     private String Weight;
     private String Applicability;
     private String Subgroup;
     private String Production;
     
     
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
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getWeight() {
		return Weight;
	}
	public void setWeight(String weight) {
		Weight = weight;
	}
	public String getApplicability() {
		return Applicability;
	}
	public void setApplicability(String applicability) {
		Applicability = applicability;
	}
	public String getSubgroup() {
		return Subgroup;
	}
	public void setSubgroup(String subgroup) {
		Subgroup = subgroup;
	}
	public String getProduction() {
		return Production;
	}
	public void setProduction(String production) {
		Production = production;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(articul);
		builder.append(" ");
		builder.append(name);
		builder.append(" ");
		builder.append(Price);
		builder.append(" ");
		builder.append(Weight);
		builder.append(" ");
		builder.append(Applicability);
		builder.append(" ");
		builder.append(Subgroup);
		builder.append(" ");
		builder.append(Production);
		builder.append(" ");
		return builder.toString();
	}

     
     
     
}
