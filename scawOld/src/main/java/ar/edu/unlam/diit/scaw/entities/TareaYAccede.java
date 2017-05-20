package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class TareaYAccede extends Tarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20L;
	private AccedeTarea accedeTareaUsuaroEn;
	
	public TareaYAccede() {
		// TODO Auto-generated constructor stub
	}

	public AccedeTarea getAccedeTareaUsuaroEn() {
		return accedeTareaUsuaroEn;
	}

	public void setAccedeTareaUsuaroEn(AccedeTarea accedeTareaUsuaroEn) {
		this.accedeTareaUsuaroEn = accedeTareaUsuaroEn;
	}
	
	
	
}
