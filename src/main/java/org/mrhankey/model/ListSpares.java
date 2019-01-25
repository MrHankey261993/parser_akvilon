package org.mrhankey.model;

import java.util.List;

public class ListSpares {
 private List<Spares> spares;

public List<Spares> getSpares() {
	return spares;
}

public void setSpares(List<Spares> spares) {
	this.spares = spares;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(" ");
	builder.append(spares);
	builder.append(" ");
	return builder.toString();
}
 
}
