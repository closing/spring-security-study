package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority,Serializable {

	@Id()
	private Integer authorityId;

	private String authority;

	private static final long serialVersionUID = 1L;

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer id) {
		this.authorityId = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority == null ? null : authority.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Authority other = (Authority) that;
		return (this.getAuthorityId() == null ? other.getAuthorityId() == null : this.getAuthorityId().equals(other.getAuthorityId()))
				&& (this.getAuthority() == null ? other.getAuthority() == null
						: this.getAuthority().equals(other.getAuthority()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAuthorityId() == null) ? 0 : getAuthorityId().hashCode());
		result = prime * result + ((getAuthority() == null) ? 0 : getAuthority().hashCode());
		return result;
	}
}
