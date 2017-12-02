package mobileagent.render;

import com.ibm.aglet.InvalidAgletException;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import mobileagent.bean.Host;

public class HostTableModel extends AbstractTableModel {

    private String[] tableHeader;
    private ArrayList<Host> listHost;
    private JTable table;

    public HostTableModel(JTable table, ArrayList<Host> listAgent) {
        this.tableHeader = new String[]{"PC-Name", "IP Address", "Platform"};
        this.table = table;
        this.listHost = listAgent;
        loadTable();
    }

    public void loadTable() {
        this.table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
        this.table.setRowHeight(18);
        ((JComponent) this.table.getDefaultRenderer(Boolean.class)).setOpaque(true);
        this.table.setModel(this);

        this.table.getColumnModel().getColumn(0).setPreferredWidth(450);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(400);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(250);
    }

    public int getRowCount() {
        return listHost.size();
    }

    public String getColumnName(int column) {
        return tableHeader[column];
    }

    public int getColumnCount() {
        return tableHeader.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listHost.get(rowIndex).getName();
            case 1:
                return listHost.get(rowIndex).getIp();
            case 2:
                if (listHost.get(rowIndex).getPlatform() == 1) {
                    return "Installed";
                }
                return "";
        }
        return null;
    }

    public Host getObject(int index) {
        return listHost.get(index);
    }

    public void addRow(Host ipA) {
        listHost.add(ipA);
        fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount() - 1, 0, true));
    }

    public void updateRow(int index, Host ipA) {
        listHost.set(index, ipA);
        fireTableDataChanged();
    }

    public void delRow(int index) throws InvalidAgletException {
        listHost.remove(index);
        fireTableDataChanged();
    }

    public void updateInfo(Host ipA) {
        for (int i = 0; i < listHost.size(); i++) {
            if ((((Host) listHost.get(i)).getIp()).equals(ipA.getIp())) {
                Host ip = (Host) listHost.get(i);
                ip.setName(ipA.getName());
                ip.setPlatform(ipA.getPlatform());
            }
        }
        fireTableDataChanged();
    }

    public void clear() {
        listHost.removeAll(listHost);
    }
}
