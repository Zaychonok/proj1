package window;

import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import model.AnyData;
import model.Firm;
import model.Product;
import model.Provider;
import model.Network;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main_Window {
	private JFrame frame;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					tree.setModel(getStartModel());
					for (int i = 0; i < tree.getRowCount(); i++)
						tree.expandRow(i);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setTitle("РГР \"Торгова мережа\"");
		frame.setBounds(100, 100, 450, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Store");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tree.getModel() == null)
					return;
				JFileChooser fileChooser = new JFileChooser("Серіалізація моделі дерева");
				fileChooser.showSaveDialog(frame);
				try {
					File f = fileChooser.getSelectedFile();
					String fName = f.getAbsolutePath();
					FileOutputStream fileStream = new FileOutputStream(fName);
					ObjectOutputStream out = new ObjectOutputStream(fileStream);
					out.writeObject(tree.getModel());
					out.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(tree, "Помилка відкриття файлу", "Збереження дерева у файлі",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Restore");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(frame);
				fileDialog.setMode(FileDialog.LOAD);
				fileDialog.setVisible(true);
				String dr = fileDialog.getDirectory();
				String fn = fileDialog.getFile();
				if (dr == null || fn == null)
					return;
				String fName = dr + fn;
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName));
					TreeModel model = (TreeModel) in.readObject();
					tree.setModel(model);
					in.close();
				} catch (Exception arg0) {
					JOptionPane.showMessageDialog(tree, "Помилка десеріалізації дерева", "Десеріалізація",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				expandAll();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("About");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntm_tz = new JMenuItem("Tz");
		mntm_tz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dlg_Tz tz = new Dlg_Tz();
				tz.setLocationRelativeTo(frame);
				tz.setVisible(true);
				tz.dispose();
			}

		});
		mnNewMenu_1.add(mntm_tz);

		JMenuItem mntm_dia = new JMenuItem("Diagram");
		mntm_dia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dlg_Diagram diagram = new Dlg_Diagram();
				diagram.setLocationRelativeTo(frame);
				diagram.setVisible(true);
				diagram.dispose();
			}
		});

		mnNewMenu_1.add(mntm_dia);

		tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				onMouseCliced(arg0);
			}

			private void onMouseCliced(MouseEvent arg0) {
				if (arg0.getClickCount() != 3 || arg0.getButton() != MouseEvent.BUTTON3)
					return;
				DefaultMutableTreeNode node = getSelectedNode();
				if (node == null)
					return;
				AnyData data = (AnyData) node.getUserObject();
				Dlg dlg = data.showDialog(false);
				((JDialog) dlg).dispose();
			}
		});

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tree, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panel,
										GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(11).addComponent(tree,
										GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
						.addContainerGap()));

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode parent = getSelectedNode();
				if (parent == null)
					return;
				AnyData parentData = (AnyData) parent.getUserObject();
				Dlg dlg = parentData.showSonDialog();
				if (dlg == null)
					return;
				Object obj;
				try {
					obj = dlg.createObject();
				} catch (Exception arg) {
					JOptionPane.showMessageDialog(tree, arg.getMessage(), frame.getTitle(), JOptionPane.ERROR_MESSAGE);
					return;
				}
				((JDialog) dlg).dispose();
				if (obj == null)
					return;
				DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(obj);
				parent.add(newSon);
				tree.updateUI();
				expandAll();
			}

		});

		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode node = getSelectedNode();
				if (node == null)
					return;
				node.removeFromParent();
				tree.setSelectionPath(null);
				tree.updateUI();
			}
		});

		JButton btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = getSelectedNode();
				if (node == null)
					return;
				AnyData data = (AnyData) node.getUserObject();
				Dlg dlg = data.showDialog(true);
				Object obj;
				try {
					obj = dlg.createObject();
				} catch (Exception arg0) {
					JOptionPane.showMessageDialog(tree, arg0.getMessage(), frame.getTitle(), JOptionPane.ERROR_MESSAGE);
					return;
				}
				((JDialog) dlg).dispose();
				if (obj == null)
					return;
				node.setUserObject(obj);
				tree.updateUI();
			}

		});

		JButton btnNewButton_5 = new JButton("Find/Calculate");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addGap(27).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addComponent(btnNewButton_1)
						.addGap(39).addComponent(btnNewButton_2).addGap(32).addComponent(btnNewButton_5).addGap(26)));

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnNewButton_5, popupMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Пошук фірми з найбільшою кількістю філіалів");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = getSelectedNode();
				if (node == null)
					return;
				int MaxFil = 0;
				DefaultMutableTreeNode max = null;
				@SuppressWarnings("unchecked")
				Enumeration<DefaultMutableTreeNode> enm = node.postorderEnumeration();
				while (enm.hasMoreElements()) {
					DefaultMutableTreeNode currentNode = enm.nextElement();
					Object data = currentNode.getUserObject();
					if (!(data instanceof Firm))
						continue;
					int fil = ((Firm) data).numFilial;
					if (fil > MaxFil) {
						MaxFil = fil;
						max = currentNode;
					}
				}
				selectNode(max);
				((AnyData) max.getUserObject()).showDialog(false);
			}
		});
		popupMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem(
				"Підрахунок та виведення кількості товарів у певного постачальника");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode node = getSelectedNode();
				if (node == null)
					return;
				int n = 0;
				DefaultMutableTreeNode num = null;
				@SuppressWarnings("unchecked")
				Enumeration<DefaultMutableTreeNode> enm = node.postorderEnumeration();
				while (enm.hasMoreElements()) {
					DefaultMutableTreeNode currentNode = enm.nextElement();
					Object data = currentNode.getUserObject();
					if (!(data instanceof Product))
						continue;
					boolean check = ((Product) data).getNum();
					if (check) {
						n += 1;
						num = currentNode;
					}
				}
				selectNode(num);
				JOptionPane.showMessageDialog(tree, "Кількість товарів у певного постачальника: " + n);

			}

		});
		popupMenu.add(mntmNewMenuItem_1);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}

	protected TreeModel getStartModel() {
		Network n = new Network("Торгова мережа №1", "Панченко А.У.", "Чернігів");
		Firm f = new Firm("Шпунтик", "Камаровський В. К.", 3);
		Provider p = new Provider("Бондаренко С. М.", "380990000000", "вул. Мстиславська 4");
		Product pr = new Product("Гайка", 3, 2000);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(n);
		DefaultMutableTreeNode fnod = new DefaultMutableTreeNode(f);
		DefaultMutableTreeNode pnod = new DefaultMutableTreeNode(p);
		DefaultMutableTreeNode prnod = new DefaultMutableTreeNode(pr);
		root.add(fnod);
		fnod.add(pnod);
		pnod.add(prnod);
		return (new JTree(root)).getModel();
	}

	private DefaultMutableTreeNode getSelectedNode() {
		Object selectNode = tree.getLastSelectedPathComponent();
		if (selectNode == null) {
			JOptionPane.showMessageDialog(tree, "Node was not selected", frame.getTitle(), JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return (DefaultMutableTreeNode) selectNode;
	}

	private void expandAll() {
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
	}

	private void selectNode(DefaultMutableTreeNode node) {
		int n = 0;
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> enm = root.children();
		while (enm.hasMoreElements()) {
			DefaultMutableTreeNode nod = enm.nextElement();
			if (nod == node) {
				tree.setSelectionRow(n);
				return;
			}
			n++;
		}

	}

	public JTree getTree() {
		return tree;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				showMenu(e);
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
