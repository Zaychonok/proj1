package model;

import window.Dlg;
import window.DlgNetwork;
import window.DlgFirm;

public class Network extends AnyData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public String NameDir;
	public String City;

	public Network(String name, String nameDir, String city) {
		super(name);
		this.name = name;
		NameDir = nameDir;
		City = city;
	}

	@Override
	public Dlg showDialog(boolean b) {
		DlgNetwork baza = new DlgNetwork(this);
		baza.setEditable(b);
		baza.setVisible(true);
		return baza;
	}

	@Override
	public Dlg showSonDialog() {
		DlgFirm t = new DlgFirm();
		t.setVisible(true);
		return t;
	}

}
