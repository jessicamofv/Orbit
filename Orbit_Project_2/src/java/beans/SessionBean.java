/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;

public class SessionBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4330131385161045244L;
	private CustomerBean custBean;
	
	public SessionBean() { }

	public CustomerBean getProfBean() {
		return custBean;
	}

	public void setProfBean(CustomerBean custBean) {
		this.custBean = custBean;
	}
	
	
}
