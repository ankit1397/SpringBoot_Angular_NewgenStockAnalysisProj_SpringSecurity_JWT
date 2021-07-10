package com.springjwt.payload.request;

public class DateRangeRequest {
 private String start;
 private String end;
 
 
public DateRangeRequest(String start, String end) {
	super();
	this.start = start;
	this.end = end;
}

public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getEnd() {
	return end;
}
public void setEnd(String end) {
	this.end = end;
}
 
}
