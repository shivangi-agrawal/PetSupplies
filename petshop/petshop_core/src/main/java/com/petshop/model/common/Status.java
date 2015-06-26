package com.petshop.model.common;

public enum Status {

	SUBMITTED("Submitted"), REJECTED("Rejected"), APPROVED("Approved");

	private String label;

	private Status(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}