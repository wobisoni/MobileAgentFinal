package mobileagent.render;


import com.ibm.aglet.InvalidAgletException;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import mobileagent.bean.Host;

public class IpTableModel extends AbstractTableModel{
    String[] header;
    ArrayList<Host> arIp;
    JTable table;

    public IpTableModel(JTable table, ArrayList<Host> arIP) {
         this.header = new String [] {"PC-Name", "IP Address", "Platform"};
         this.table = table;
         this.arIp = arIP;
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
        return arIp.size();
    }

    public String getColumnName(int column) {
        return header[column];
    }

    public int getColumnCount() {
        return header.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return arIp.get(rowIndex).getName();
            case 1:
                return arIp.get(rowIndex).getIp();
            case 2:
                if(arIp.get(rowIndex).getPlatform()==1){
                    return "Installed";
                }
                return "";
        }
        return null;
    }
    
    public Host getObject(int index){
        return arIp.get(index);
    }
    
    public void addRow(Host ipA){
        arIp.add(ipA);
        fireTableDataChanged();
        table.scrollRectToVisible(table.getCellRect(this.getRowCount()-1, 0, true));
    }
    
    public void updateRow(int index, Host ipA){
        arIp.set(index, ipA);
        fireTableDataChanged();
    }
    
    public void delRow(int index) throws InvalidAgletException{
        arIp.remove(index);
        fireTableDataChanged();
    }

    public void updateInfo(Host ipA) {
        for (int i = 0; i < arIp.size(); i++){
            if((((Host)arIp.get(i)).getIp()).equals(ipA.getIp())){
               Host ip = (Host)arIp.get(i);
               ip.setName(ipA.getName());
               ip.setPlatform(ipA.getPlatform());
            }
        }
        fireTableDataChanged();
    }

    public void clear() {
        arIp = new ArrayList();
    }
}
