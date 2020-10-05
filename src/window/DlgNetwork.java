package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Network;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DlgNetwork extends Dlg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_Name;
	private JTextField textField_nameDir;
	private JTextField textField_City;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgNetwork dialog = new DlgNetwork();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgNetwork() {
		setBounds(100, 100, 389, 199);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textField_Name = new JTextField();
			textField_Name.setColumns(10);
		}

		textField_nameDir = new JTextField();
		textField_nameDir.setColumns(10);

		textField_City = new JTextField();
		textField_City.setColumns(10);

		JLabel label = new JLabel(
				"\u041D\u0430\u0437\u0432\u0430 \u0442\u043E\u0440\u0433\u043E\u0432\u043E\u0457 \u043C\u0435\u0440\u0435\u0436\u0456");

		JLabel label_1 = new JLabel("\u0406\u043C'\u044F \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430");

		JLabel label_2 = new JLabel(
				"\u041C\u0456\u0441\u0442\u043E \u0440\u043E\u0437\u043C\u0456\u0449\u0435\u043D\u043D\u044F");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(label).addComponent(label_1))
										.addGap(37)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_Name, GroupLayout.DEFAULT_SIZE, 161,
														Short.MAX_VALUE)
												.addComponent(textField_nameDir, GroupLayout.DEFAULT_SIZE, 161,
														Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
										.addComponent(textField_City, GroupLayout.PREFERRED_SIZE, 161,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(44)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_nameDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
						.addGap(18, 18, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(textField_City, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
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

	public Object createObject() {
		if (!ok)
			return null;
		String name = textField_Name.getText();
		String nameDir = textField_nameDir.getText();
		String city = textField_City.getText();
		return new Network(name, nameDir, city);
	}

	public DlgNetwork(Object data) {
		this();
		Network n = (Network) data;
		textField_Name.setText(n.getName());
		textField_nameDir.setText(n.NameDir);
		textField_City.setText(n.City);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
