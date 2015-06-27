package jrat.plugin.window.client;

import iconlib.IconUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jrat.api.Client;
import jrat.api.net.EmptyPacketBuilder;
import jrat.api.ui.BaseControlPanel;

@SuppressWarnings("serial")
public class WindowPanel extends BaseControlPanel implements NewWindowListener {
	
	public static final String COLUMN_WINDOW_TITLE = "Window Title";
	public static final String COLUMN_HANDLE = "Handle";
	public static final String COLUMN_VISIBLE = "Visible";

	private TableModel model;
	private JTable table;
	
	public WindowPanel(Client client) {
		super(client);
		
		setLayout(new BorderLayout(0, 0));
		
		model = new TableModel();
		
		table = new JTable();
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new TableRenderer());
		
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(table);
		
		add(pane, BorderLayout.CENTER);
		
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem mntmReload = new JMenuItem("Reload Windows");
		mntmReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					client.addToSendQueue(new EmptyPacketBuilder(WindowPlugin.HEADER_LIST, client));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menu.add(mntmReload);
		
		addPopup(table, menu);
		addPopup(pane, menu);
		
		ListPacketListener.LISTENERS.add(this);
	}
	
	@Override
	public void onClose() {
		ListPacketListener.LISTENERS.remove(this);
	}

	private class TableModel extends DefaultTableModel {
		
		public TableModel() {
			addColumn(COLUMN_WINDOW_TITLE);
			addColumn(COLUMN_HANDLE);
			addColumn(COLUMN_VISIBLE);
		}
		
		@Override
		public boolean isCellEditable(int i, int i1) {
			return false;
		}
	}
	
	private class TableRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			NativeWindow window = (NativeWindow) table.getValueAt(row, 0);
			
			String colname = table.getColumnName(column);
			
			label.setIcon(null);
			label.setForeground(Color.black);
			
			if (colname == COLUMN_WINDOW_TITLE) {
				if (window.getIcon() != null) {
					label.setIcon(window.getIcon());
				} else if (window.isVisible()) {
					label.setIcon(IconUtils.getIcon("window", WindowPanel.class));
				} else {
					label.setIcon(IconUtils.getIcon("window-disabled", WindowPanel.class));
				}
				
				if (window.getTitle().length() == 0) {
					label.setForeground(isSelected ? Color.white : Color.gray.brighter());	
					label.setText("No Title");
				} else {
					label.setText(window.getTitle());
				}
			} else if (colname == COLUMN_HANDLE) {
				label.setText(window.getHandle() + "");
			} else if (colname == COLUMN_VISIBLE) {
				label.setText(window.isVisible() + "");
			}
			
			return label;
		}
	}

	@Override
	public void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	@Override
	public void windowAdded(NativeWindow window) {
		model.insertRow(window.isVisible() ? 0 : model.getRowCount(), new Object[] { window });
	}
	
	public static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
