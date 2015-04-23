/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;

public class CustomerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892904874716952545L;
	private String name;
	private String email;
	private String password;
	
	public CustomerBean () {}

	public String getName() {
		return name;
	}

	public void setWebId(String webId) {
		this.name = webId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
