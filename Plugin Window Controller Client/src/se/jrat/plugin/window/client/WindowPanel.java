package se.jrat.plugin.window.client;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jrat.api.Client;
import jrat.api.ui.BaseControlPanel;

@SuppressWarnings("serial")
public class WindowPanel extends BaseControlPanel {
	
	public static final String COLUMN_WINDOW_TITLE = "Window Title";
	
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
	}

	private class TableModel extends DefaultTableModel {
		
		public TableModel() {
			addColumn(COLUMN_WINDOW_TITLE);
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
			
			if (colname == COLUMN_WINDOW_TITLE) {
				
			}
			
			return label;
		}
	}
}
