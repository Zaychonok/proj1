package model;

import window.Dlg;
import window.DlgProvider;
import window.DlgFirm;

public class Firm extends AnyData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public String nameDirFirm;
	public int numFilial;

	public Firm(String name, String nameDirFirm, int numFilial) {
		super(name);
		this.name = name;
		this.nameDirFirm = nameDirFirm;
		this.numFilial = numFilial;
	}

	@Override
	public Dlg showDialog(boolean b) {
		DlgFirm t = new DlgFirm(this);
		t.setEditable(b);
		t.setVisible(true);
		return t;
	}

	@Override
	public Dlg showSonDialog() {
		DlgProvider c = new DlgProvider();
		c.setVisible(true);
		return c;
	}

	public int getNumFilial() {
		return numFilial;
	}

}
