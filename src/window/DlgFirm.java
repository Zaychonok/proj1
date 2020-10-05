package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Firm;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgFirm extends Dlg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_Name;
	private JTextField textField_nameDirFirm;
	private JTextField textField_numFilial;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgFirm dialog = new DlgFirm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgFirm() {
		setBounds(100, 100, 381, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textField_Name = new JTextField();
			textField_Name.setColumns(10);
		}
		textField_nameDirFirm = new JTextField();
		textField_nameDirFirm.setColumns(10);
		textField_numFilial = new JTextField();
		textField_numFilial.setColumns(10);
		label = new JLabel("\u041D\u0430\u0437\u0432\u0430 \u0444\u0456\u0440\u043C\u0438");
		lblNewLabel = new JLabel(
				"\u0406\u043C'\u044F \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430 \u0444\u0456\u0440\u043C\u0438");
		lblNewLabel_1 = new JLabel(
				"\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0444\u0456\u043B\u0456\u0430\u043B\u0456\u0432");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(label)
								.addComponent(lblNewLabel).addComponent(lblNewLabel_1))
						.addGap(41)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_numFilial, Alignment.LEADING)
								.addComponent(textField_Name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 125,
										Short.MAX_VALUE)
								.addComponent(textField_nameDirFirm, GroupLayout.PREFERRED_SIZE, 158,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(5)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_nameDirFirm, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_numFilial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addContainerGap(24, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok = true;
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ok = false;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public Object createObject() {
		if (!ok)
			return null;
		String name = textField_Name.getText();
		String nameDirFirm = textField_nameDirFirm.getText();
		int numFilial = Integer.parseInt(textField_numFilial.getText());
		return new Firm(name, nameDirFirm, numFilial);
	}

	public DlgFirm(Object data) {
		this();
		Firm f = (Firm) data;
		textField_Name.setText(f.getName());
		textField_nameDirFirm.setText(f.nameDirFirm);
		textField_numFilial.setText(String.valueOf(f.numFilial));
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
