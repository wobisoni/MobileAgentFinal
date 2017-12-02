package mobileagent.render;

import mobileagent.bean.Agent;
import com.ibm.aglet.InvalidAgletException;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class AgentTableModel extends AbstractTableModel {

    private String[] tableHeader;
    private ArrayList<Agent> listAgent;
    private JTable table;

    public AgentTableModel(JTable table, ArrayList<Agent> listAgent) {
        this.tableHeader = new String[]{"ID", "Name", " IP Address", "Create time"};
        this.listAgent = listAgent;
        this.table = table;
        loadTable();
    }

    public void loadTable() {
        this.table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
        this.table.setRowHeight(18);
        ((JComponent) this.table.getDefaultRenderer(Boolean.class)).setOpaque(true);
        this.table.setModel(this);

        this.table.getColumnModel().getColumn(0).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }

    public int getRowCount() {
        return listAgent.size();
    }

    public String getColumnName(int column) {
        return tableHeader[column];
    }

    public int getColumnCount() {
        return tableHeader.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object object = null;
        switch (columnIndex) {
            case 0:
                return listAgent.get(rowIndex).getaId();
            case 1:
                return listAgent.get(rowIndex).getaName();
            case 2:
                return listAgent.get(rowIndex).getaIp();
            case 3:
                return listAgent.get(rowIndex).getaTime();
        }
        return object;
    }

    public Agent getObject(int index) {
        return listAgent.get(index);
    }

    public void addRow(Agent agent) {
        listAgent.add(agent);
        fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount() - 1, 0, true));
    }

    public void updateRow(int index, Agent agent) {
        listAgent.set(index, agent);
        fireTableDataChanged();
    }

    public void delRow(int index) throws InvalidAgletException {
        listAgent.remove(index);
        fireTableDataChanged();
    }

    public void updateInfo(Agent agent) {
        for (int i = 0; i < listAgent.size(); i++) {
            if (((Agent) listAgent.get(i)).getaId().equals(agent.getaId())) {
                Agent ag = (Agent) listAgent.get(i);
                ag.setaIp(agent.getaIp());
                ag.setaProxy(agent.getaProxy());
                ag.setaStatus(agent.getaStatus());
            }
        }
        fireTableDataChanged();
    }
}
