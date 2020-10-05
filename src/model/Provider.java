package model;

import window.Dlg;
import window.DlgProvider;
import window.DlgProduct;

public class Provider extends AnyData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String numberPh;
	public String address;

	

	public Provider(String name, String numberPh, String address) {
		super(name);
		this.name = name;
		this.numberPh = numberPh;
		this.address = address;
	}

	@Override
	public Dlg showDialog(boolean b) {
		DlgProvider c = new DlgProvider(this);
		c.setEditable(b);
		c.setVisible(true);
		return c;
	}

	@Override
	public Dlg showSonDialog() {
		DlgProduct n = new DlgProduct();
		n.setVisible(true);
		return n;
	}

	

}
