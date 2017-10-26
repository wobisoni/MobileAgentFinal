package mobileagent.render;


import com.ibm.aglet.InvalidAgletException;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import mobileagent.bean.Host;

public class HostTableModel extends AbstractTableModel{
    String[] tableHeader;
    ArrayList<Host> listAgent;
    JTable table;

    public HostTableModel(JTable table, ArrayList<Host> listAgent) {
        this.tableHeader = new String [] {"PC-Name", "IP Address", "Platform"};
        this.table = table;
        this.listAgent = listAgent;
        loadTable();
    }
    
    public void loadTable() {
        this.table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
        this.table.setRowHeight(18);
        ((JComponent)this.table.getDefaultRenderer(Boolean.class)).setOpaque(true);
        this.table.setModel(this);
        
        this.table.getColumnModel().getColumn(0).setPreferredWidth(450);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(400);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(250);
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
        switch(columnIndex){
            case 0:
                return listAgent.get(rowIndex).getName();
            case 1:
                return listAgent.get(rowIndex).getIp();
            case 2:
                if(listAgent.get(rowIndex).getPlatform()==1){
                    return "Installed";
                }
                return "";
        }
        return null;
    }
    
    public Host getObject(int index){
        return listAgent.get(index);
    }
    
    public void addRow(Host ipA){
        listAgent.add(ipA);
        fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount()-1, 0, true));
    }
    
    public void updateRow(int index, Host ipA){
        listAgent.set(index, ipA);
        fireTableDataChanged();
    }
    
    public void delRow(int index) throws InvalidAgletException{
        listAgent.remove(index);
        fireTableDataChanged();
    }

    public void updateInfo(Host ipA) {
        for (int i = 0; i < listAgent.size(); i++){
            if((((Host)listAgent.get(i)).getIp()).equals(ipA.getIp())){
               Host ip = (Host)listAgent.get(i);
               ip.setName(ipA.getName());
               ip.setPlatform(ipA.getPlatform());
            }
        }
        fireTableDataChanged();
    }

    public void clear() {
        listAgent = new ArrayList();
    }
}
