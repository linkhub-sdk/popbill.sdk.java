package com.popbill.api.easyfin;

/**
 * Class for EasyFin Summary 
 * @author John
 * @version 1.0.0
 */

public class EasyFinBankSummary {
	
	private long count;
	private long cntAccIn;
	private long cntAccOut;
	private long totalAccIn;
	private long totalAccOut;
	
	public long getCount() {
		return count;
	}
	public long getCntAccIn() {
		return cntAccIn;
	}
	public long getCntAccOut() {
		return cntAccOut;
	}
	public long getTotalAccIn() {
		return totalAccIn;
	}
	public long getTotalAccOut() {
		return totalAccOut;
	}
	
	
}
