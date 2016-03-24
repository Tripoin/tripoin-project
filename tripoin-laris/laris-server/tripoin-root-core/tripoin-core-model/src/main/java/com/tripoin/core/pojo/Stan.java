package com.tripoin.core.pojo;

import java.io.Serializable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class Stan implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3827964825652089559L;
	private Long id;
	private Long stanCounter;
	private Long stanMax;
	private Long userId;

	public Stan() {}
	
	public Stan(Long id, Long stanCounter, Long stanMax, Long userId) {
		this.id = id;
		this.stanCounter = stanCounter;
		this.stanMax = stanMax;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStanCounter() {
		return stanCounter;
	}

	public void setStanCounter(Long stanCounter) {
		this.stanCounter = stanCounter;
	}

	public Long getStanMax() {
		return stanMax;
	}

	public void setStanMax(Long stanMax) {
		this.stanMax = stanMax;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Stan [id=" + id + ", stanCounter=" + stanCounter + ", stanMax="
				+ stanMax + ", userId=" + userId + "]";
	}
}
