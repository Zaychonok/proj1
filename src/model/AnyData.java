package model;

import java.io.Serializable;

import window.Dlg;

public abstract class AnyData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;

	public AnyData(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract Dlg showDialog(boolean b);

	public abstract Dlg showSonDialog();

	@Override
	public String toString() {
		return name;
	}

}