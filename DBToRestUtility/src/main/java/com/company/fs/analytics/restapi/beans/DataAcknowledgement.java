package com.company.fs.analytics.restapi.beans;

/**
 * The application bean class that encapsulate the objects into a single object.
 * It returns tran_id+tran_desc+tran_ts as String
 */

public class DataAcknowledgement {
	
	public DataAcknowledgement(String tran_id, String tran_desc, String tran_ts) {
		super();
		this.tran_id = tran_id;
		this.tran_desc = tran_desc;
		this.tran_ts= tran_ts;
	}

	private final String tran_id;
	private final String tran_desc;
	private final String tran_ts;

	public String getTran_id() {
		return tran_id;
	}

	public String getTran_desc() {
		return tran_desc;
	}

	public String getTran_ts() {
		return tran_ts;
	}

	public String toString() {
		return tran_id+tran_desc+tran_ts;
	}
}
