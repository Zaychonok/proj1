package model;

import window.Dlg;
import window.DlgProduct;

public class Product extends AnyData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public int Price;
	public int numSklad;

	public Product(String name, int price, int numSklad) {
		super(name);
		this.name = name;
		Price = price;
		this.numSklad = numSklad;
	}

	@Override
	public Dlg showDialog(boolean b) {
		DlgProduct n = new DlgProduct(this);
		n.setEditable(b);
		n.setVisible(true);
		return n;
	}

	@Override
	public Dlg showSonDialog() {
		DlgProduct n = new DlgProduct();
		n.setVisible(true);
		return null;
	}

	public boolean getNum() {
		boolean b = true;
		return b;
	}

}
