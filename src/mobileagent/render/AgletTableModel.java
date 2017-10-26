package mobileagent.render;

import mobileagent.bean.Agent;
import com.ibm.aglet.InvalidAgletException;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class AgletTableModel extends AbstractTableModel{
    String[] header;
    ArrayList<Agent>  arAgent;
    JTable table;

    public AgletTableModel(JTable table, ArrayList<Agent> arAgent) {
         this.header = new String [] { "ID","Name"," IP Address", "Create time" };
         this.arAgent = arAgent;
         this.table = table;
    }
    
    public void loadTable() {
        this.table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
        this.table.setRowHeight(18);
        ((JComponent)this.table.getDefaultRenderer(Boolean.class)).setOpaque(true);
        this.table.setModel(this);
        
        this.table.getColumnModel().getColumn(0).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(300);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    
    public int getRowCount() {
         return arAgent.size();
    }

    public String getColumnName(int column) {
         return header[column];
    }

    public int getColumnCount() {
         return header.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object object = null;
        switch(columnIndex){
            case 0:
                return arAgent.get(rowIndex).getaId();
            case 1:
                return arAgent.get(rowIndex).getaName();
            case 2:
                return arAgent.get(rowIndex).getaIp();
            case 3:
                return arAgent.get(rowIndex).getaTime();
        }
        return object;
    }
    
    public Agent getObject(int index){
        return arAgent.get(index);
    }
    
    public void addRow(Agent agent){
         arAgent.add(agent);
         fireTableDataChanged();
         table.scrollRectToVisible(table.getCellRect(this.getRowCount()-1, 0, true));
    }
    
    public void updateRow(int index, Agent agent){
         arAgent.set(index, agent);
         fireTableDataChanged();
    }
    
    public void delRow(int index) throws InvalidAgletException{
         arAgent.remove(index);
         fireTableDataChanged();
    }

    public void updateInfo(Agent agent) {
        for (int i = 0; i < arAgent.size(); i++){
            if(((Agent)arAgent.get(i)).getaId().equals(agent.getaId())){
                Agent ag = (Agent)arAgent.get(i);
                ag.setaIp(agent.getaIp());
                ag.setaProxy(agent.getaProxy());
                ag.setaStatus(agent.getaStatus());
            }
        }
        fireTableDataChanged();
    }
}
