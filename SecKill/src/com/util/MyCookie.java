package com.util;

import java.util.Date;

import org.apache.http.cookie.Cookie;

public class MyCookie implements Cookie {

	private String comment = null;

	private String commentURL = null;

	private String domain = null;

	private Date expiryDate = null;

	private String name = null;

	private String path = null;

	private int[] ports = null;

	private String value = null;

	private int version = 0;

	private boolean isPersistent = false;

	private boolean isSecure = false;

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return this.comment;
	}

	@Override
	public String getCommentURL() {
		// TODO Auto-generated method stub
		return this.commentURL;
	}

	@Override
	public String getDomain() {
		// TODO Auto-generated method stub
		return this.domain;
	}

	@Override
	public Date getExpiryDate() {
		// TODO Auto-generated method stub
		return this.expiryDate;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}

	@Override
	public int[] getPorts() {
		// TODO Auto-generated method stub
		return this.ports;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return this.version;
	}

	@Override
	public boolean isExpired(Date date) {
		// TODO Auto-generated method stub
		return this.expiryDate.before(date);
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return this.isPersistent;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return this.isSecure;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCommentURL(String commentURL) {
		this.commentURL = commentURL;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPorts(int[] ports) {
		this.ports = ports;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setPersistent(boolean isPersistent) {
		this.isPersistent = isPersistent;
	}

	public void setSecure(boolean isSecure) {
		this.isSecure = isSecure;
	}
}