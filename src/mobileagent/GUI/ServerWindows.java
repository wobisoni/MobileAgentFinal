package mobileagent.GUI;

import com.ibm.aglet.AgletContext;
import com.ibm.aglet.AgletID;
import com.ibm.aglet.AgletProxy;
import com.ibm.aglet.Message;
import mobileagent.render.AgentTableModel;
import mobileagent.render.HostTableModel;
import mobileagent.agent.AgentServer;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import static java.awt.EventQueue.invokeLater;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.UIManager.setLookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import mobileagent.bean.Agent;
import mobileagent.bean.Host;
import mobileagent.library.LibConfig;
import mobileagent.library.ScanHostIP;
import mobileagent.library.SendNoti;

public class ServerWindows extends JFrame {

    private transient AgentServer serverAgent;
    private static ArrayList<Agent> listAgent;
    private static ArrayList<Host> listHost;
    private AgentTableModel agentTableModel;
    private HostTableModel hostTableModel;
    private ScanHostIP scanIp;
    private String serverIp;

    public ServerWindows() {
    }

    public ServerWindows(AgentServer aglet) {
        this.serverAgent = aglet;

        initComponents();
        this.setLocationRelativeTo(null);

        listAgent = new ArrayList();
        listHost = new ArrayList();
        serverIp = LibConfig.getMyIp();

        hostTableModel = new HostTableModel(tbListHost, listHost);
        hostTableModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                try {
                    setSystemInfo(hostTableModel.getObject(hostTableModel.getRowCount() - 1));
                } catch (Exception ex) {

                }
            }
        });

        agentTableModel = new AgentTableModel(tbListAgent, listAgent);
        agentTableModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                try {
                    setAgentInfo(agentTableModel.getObject(agentTableModel.getRowCount() - 1));
                } catch (Exception ex) {

                }
            }
        });

        scanIp = new ScanHostIP(serverIp, serverAgent, hostTableModel);
        try {
            scanIp.startScan();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcPath = new javax.swing.JFileChooser();
        panel1 = new javax.swing.JPanel();
        panel11 = new javax.swing.JPanel();
        panel111 = new javax.swing.JPanel();
        spListAgent = new javax.swing.JScrollPane();
        tbListAgent = new javax.swing.JTable();
        panel112 = new javax.swing.JPanel();
        spListHost = new javax.swing.JScrollPane();
        tbListHost = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        panel13 = new javax.swing.JPanel();
        btnChat = new javax.swing.JButton();
        btnReDesktop = new javax.swing.JButton();
        btnCapture = new javax.swing.JButton();
        btnNoti = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnSystem = new javax.swing.JButton();
        panel12 = new javax.swing.JPanel();
        panel121 = new javax.swing.JPanel();
        panel1211 = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        lbAID = new javax.swing.JLabel();
        panel1212 = new javax.swing.JPanel();
        lb2 = new javax.swing.JLabel();
        lbAName = new javax.swing.JLabel();
        panel1213 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        lbALocal = new javax.swing.JLabel();
        panel1215 = new javax.swing.JPanel();
        lb5 = new javax.swing.JLabel();
        lbATime = new javax.swing.JLabel();
        panel1216 = new javax.swing.JPanel();
        lb6 = new javax.swing.JLabel();
        lbAStatus = new javax.swing.JLabel();
        panel122 = new javax.swing.JPanel();
        panel1221 = new javax.swing.JPanel();
        lb7 = new javax.swing.JLabel();
        lbSName = new javax.swing.JLabel();
        panel1222 = new javax.swing.JPanel();
        lb8 = new javax.swing.JLabel();
        lbSIP = new javax.swing.JLabel();
        panel1223 = new javax.swing.JPanel();
        lb9 = new javax.swing.JLabel();
        lbSOS = new javax.swing.JLabel();
        panel1224 = new javax.swing.JPanel();
        lb10 = new javax.swing.JLabel();
        lbSArch = new javax.swing.JLabel();
        panel1225 = new javax.swing.JPanel();
        lb11 = new javax.swing.JLabel();
        lbSVersion = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        lbbottom = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mClose = new javax.swing.JMenuItem();
        menuSettings = new javax.swing.JMenu();
        mPathConf = new javax.swing.JMenuItem();

        fcPath.setName("fcPath"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mobile Agent Application");
        setName("Form"); // NOI18N
        setResizable(false);

        panel1.setName("panel1"); // NOI18N
        panel1.setLayout(new java.awt.BorderLayout());

        panel11.setEnabled(false);
        panel11.setMaximumSize(new java.awt.Dimension(838, 439));
        panel11.setMinimumSize(new java.awt.Dimension(838, 439));
        panel11.setName("panel11"); // NOI18N
        panel11.setLayout(new java.awt.BorderLayout());

        panel111.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List Agent", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 0, 0))); // NOI18N
        panel111.setMaximumSize(new java.awt.Dimension(550, 433));
        panel111.setMinimumSize(new java.awt.Dimension(550, 433));
        panel111.setName("panel111"); // NOI18N
        panel111.setPreferredSize(new java.awt.Dimension(550, 433));

        spListAgent.setBackground(new java.awt.Color(255, 255, 255));
        spListAgent.setName("spListAgent"); // NOI18N

        tbListAgent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbListAgent.setMaximumSize(new java.awt.Dimension(450, 96));
        tbListAgent.setName("tbListAgent"); // NOI18N
        tbListAgent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListAgentMouseClicked(evt);
            }
        });
        spListAgent.setViewportView(tbListAgent);

        org.jdesktop.layout.GroupLayout panel111Layout = new org.jdesktop.layout.GroupLayout(panel111);
        panel111.setLayout(panel111Layout);
        panel111Layout.setHorizontalGroup(
            panel111Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(spListAgent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );
        panel111Layout.setVerticalGroup(
            panel111Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(spListAgent, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panel11.add(panel111, java.awt.BorderLayout.CENTER);

        panel112.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List Host", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 0, 0))); // NOI18N
        panel112.setName("panel112"); // NOI18N

        spListHost.setBackground(new java.awt.Color(255, 255, 255));
        spListHost.setName("spListHost"); // NOI18N

        tbListHost.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "PC-Name", "IP", "Platform"
            }
        ));
        tbListHost.setName("tbListHost"); // NOI18N
        tbListHost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListHostMouseClicked(evt);
            }
        });
        spListHost.setViewportView(tbListHost);

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\update.png")); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUpdate.setMaximumSize(new java.awt.Dimension(133, 59));
        btnUpdate.setMinimumSize(new java.awt.Dimension(133, 59));
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.setPreferredSize(new java.awt.Dimension(135, 59));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panel112Layout = new org.jdesktop.layout.GroupLayout(panel112);
        panel112.setLayout(panel112Layout);
        panel112Layout.setHorizontalGroup(
            panel112Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(spListHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panel112Layout.createSequentialGroup()
                .add(0, 141, Short.MAX_VALUE)
                .add(btnUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        panel112Layout.setVerticalGroup(
            panel112Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel112Layout.createSequentialGroup()
                .add(spListHost, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 343, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 6, Short.MAX_VALUE))
        );

        panel11.add(panel112, java.awt.BorderLayout.LINE_END);

        panel13.setName("panel13"); // NOI18N

        btnChat.setBackground(new java.awt.Color(255, 255, 255));
        btnChat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnChat.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\Chat.png")); // NOI18N
        btnChat.setText("Chat");
        btnChat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnChat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnChat.setMaximumSize(new java.awt.Dimension(133, 59));
        btnChat.setMinimumSize(new java.awt.Dimension(133, 59));
        btnChat.setName("btnChat"); // NOI18N
        btnChat.setPreferredSize(new java.awt.Dimension(135, 59));
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });
        panel13.add(btnChat);

        btnReDesktop.setBackground(new java.awt.Color(255, 255, 255));
        btnReDesktop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReDesktop.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\Remote-Desktop.png")); // NOI18N
        btnReDesktop.setText("Remote");
        btnReDesktop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReDesktop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnReDesktop.setMaximumSize(new java.awt.Dimension(133, 59));
        btnReDesktop.setMinimumSize(new java.awt.Dimension(133, 59));
        btnReDesktop.setName("btnReDesktop"); // NOI18N
        btnReDesktop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReDesktopActionPerformed(evt);
            }
        });
        panel13.add(btnReDesktop);

        btnCapture.setBackground(new java.awt.Color(255, 255, 255));
        btnCapture.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapture.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\camera.png")); // NOI18N
        btnCapture.setText("Capture");
        btnCapture.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCapture.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCapture.setMaximumSize(new java.awt.Dimension(133, 59));
        btnCapture.setMinimumSize(new java.awt.Dimension(133, 59));
        btnCapture.setName("btnCapture"); // NOI18N
        btnCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaptureActionPerformed(evt);
            }
        });
        panel13.add(btnCapture);

        btnNoti.setBackground(new java.awt.Color(255, 255, 255));
        btnNoti.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNoti.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\noti.png")); // NOI18N
        btnNoti.setText("Notification");
        btnNoti.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNoti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNoti.setMaximumSize(new java.awt.Dimension(133, 59));
        btnNoti.setMinimumSize(new java.awt.Dimension(133, 59));
        btnNoti.setName("btnNoti"); // NOI18N
        btnNoti.setPreferredSize(new java.awt.Dimension(135, 59));
        btnNoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotiActionPerformed(evt);
            }
        });
        panel13.add(btnNoti);

        btnRemove.setBackground(new java.awt.Color(255, 255, 255));
        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRemove.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\Recycle_Bin.png")); // NOI18N
        btnRemove.setText("Remove");
        btnRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRemove.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRemove.setMaximumSize(new java.awt.Dimension(133, 59));
        btnRemove.setMinimumSize(new java.awt.Dimension(133, 59));
        btnRemove.setName("btnRemove"); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        panel13.add(btnRemove);

        btnSystem.setBackground(new java.awt.Color(255, 255, 255));
        btnSystem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSystem.setIcon(new javax.swing.ImageIcon("C:\\aglets\\public\\mobileagent\\icon\\system-settings-icon.png")); // NOI18N
        btnSystem.setText("System");
        btnSystem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSystem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSystem.setMaximumSize(new java.awt.Dimension(133, 59));
        btnSystem.setMinimumSize(new java.awt.Dimension(133, 59));
        btnSystem.setName("btnSystem"); // NOI18N
        btnSystem.setPreferredSize(new java.awt.Dimension(135, 59));
        btnSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSystemActionPerformed(evt);
            }
        });
        panel13.add(btnSystem);

        panel11.add(panel13, java.awt.BorderLayout.SOUTH);

        panel1.add(panel11, java.awt.BorderLayout.CENTER);

        panel12.setName("panel12"); // NOI18N
        panel12.setPreferredSize(new java.awt.Dimension(320, 400));
        panel12.setLayout(new java.awt.GridLayout(2, 1));

        panel121.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agent Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 0, 0))); // NOI18N
        panel121.setName("panel121"); // NOI18N
        panel121.setPreferredSize(new java.awt.Dimension(250, 155));
        panel121.setLayout(new java.awt.GridLayout(5, 1));

        panel1211.setName("panel1211"); // NOI18N
        panel1211.setLayout(new java.awt.GridLayout(1, 2));

        lb1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb1.setText("   ID");
        lb1.setName("lb1"); // NOI18N
        panel1211.add(lb1);

        lbAID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAID.setName("lbAID"); // NOI18N
        panel1211.add(lbAID);

        panel121.add(panel1211);

        panel1212.setName("panel1212"); // NOI18N
        panel1212.setLayout(new java.awt.GridLayout(1, 2));

        lb2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb2.setText("   Name");
        lb2.setName("lb2"); // NOI18N
        panel1212.add(lb2);

        lbAName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAName.setName("lbAName"); // NOI18N
        panel1212.add(lbAName);

        panel121.add(panel1212);

        panel1213.setName("panel1213"); // NOI18N
        panel1213.setLayout(new java.awt.GridLayout(1, 2));

        lb3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb3.setText("   Locate");
        lb3.setName("lb3"); // NOI18N
        panel1213.add(lb3);

        lbALocal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbALocal.setName("lbALocal"); // NOI18N
        panel1213.add(lbALocal);

        panel121.add(panel1213);

        panel1215.setName("panel1215"); // NOI18N
        panel1215.setLayout(new java.awt.GridLayout(1, 2));

        lb5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb5.setText("   Time create");
        lb5.setName("lb5"); // NOI18N
        panel1215.add(lb5);

        lbATime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbATime.setName("lbATime"); // NOI18N
        panel1215.add(lbATime);

        panel121.add(panel1215);

        panel1216.setName("panel1216"); // NOI18N
        panel1216.setLayout(new java.awt.GridLayout(1, 2));

        lb6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb6.setText("   Status");
        lb6.setName("lb6"); // NOI18N
        panel1216.add(lb6);

        lbAStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbAStatus.setName("lbAStatus"); // NOI18N
        panel1216.add(lbAStatus);

        panel121.add(panel1216);

        panel12.add(panel121);

        panel122.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13), new java.awt.Color(255, 0, 0))); // NOI18N
        panel122.setName("panel122"); // NOI18N
        panel122.setLayout(new java.awt.GridLayout(5, 1));

        panel1221.setName("panel1221"); // NOI18N
        panel1221.setLayout(new java.awt.GridLayout(1, 2));

        lb7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb7.setText("   PC-Name");
        lb7.setName("lb7"); // NOI18N
        panel1221.add(lb7);

        lbSName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSName.setName("lbSName"); // NOI18N
        panel1221.add(lbSName);

        panel122.add(panel1221);

        panel1222.setName("panel1222"); // NOI18N
        panel1222.setLayout(new java.awt.GridLayout(1, 2));

        lb8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb8.setText("   IP Address");
        lb8.setName("lb8"); // NOI18N
        panel1222.add(lb8);

        lbSIP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSIP.setName("lbSIP"); // NOI18N
        panel1222.add(lbSIP);

        panel122.add(panel1222);

        panel1223.setName("panel1223"); // NOI18N
        panel1223.setLayout(new java.awt.GridLayout(1, 2));

        lb9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb9.setText("   OS");
        lb9.setName("lb9"); // NOI18N
        panel1223.add(lb9);

        lbSOS.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSOS.setName("lbSOS"); // NOI18N
        panel1223.add(lbSOS);

        panel122.add(panel1223);

        panel1224.setName("panel1224"); // NOI18N
        panel1224.setLayout(new java.awt.GridLayout(1, 2));

        lb10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb10.setText("   Architecture");
        lb10.setName("lb10"); // NOI18N
        panel1224.add(lb10);

        lbSArch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSArch.setName("lbSArch"); // NOI18N
        panel1224.add(lbSArch);

        panel122.add(panel1224);

        panel1225.setName("panel1225"); // NOI18N
        panel1225.setLayout(new java.awt.GridLayout(1, 2));

        lb11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb11.setText("   Version");
        lb11.setName("lb11"); // NOI18N
        panel1225.add(lb11);

        lbSVersion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbSVersion.setName("lbSVersion"); // NOI18N
        panel1225.add(lbSVersion);

        panel122.add(panel1225);

        panel12.add(panel122);

        panel1.add(panel12, java.awt.BorderLayout.LINE_END);

        getContentPane().add(panel1, java.awt.BorderLayout.CENTER);

        panel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel2.setName("panel2"); // NOI18N

        lbbottom.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        lbbottom.setText("Design and code by Nguyen Van Cao & Doan Minh Cuong");
        lbbottom.setName("lbbottom"); // NOI18N
        panel2.add(lbbottom);

        getContentPane().add(panel2, java.awt.BorderLayout.SOUTH);

        menuBar.setName("menuBar"); // NOI18N

        menuFile.setText("File");
        menuFile.setName("menuFile"); // NOI18N

        mClose.setText("Close");
        mClose.setName("mClose"); // NOI18N
        mClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCloseActionPerformed(evt);
            }
        });
        menuFile.add(mClose);

        menuBar.add(menuFile);

        menuSettings.setText("Settings");
        menuSettings.setName("menuSettings"); // NOI18N

        mPathConf.setText("Edit File Path");
        mPathConf.setName("mPathConf"); // NOI18N
        mPathConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPathConfActionPerformed(evt);
            }
        });
        menuSettings.add(mPathConf);

        menuBar.add(menuSettings);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int index = tbListAgent.getSelectedRow();
        if (index != -1) {
            try {
                agentTableModel.getObject(index).getaProxy().sendOnewayMessage(new Message("dispose"));
                agentTableModel.delRow(index);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select one aglet!");
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnReDesktopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReDesktopActionPerformed
        int indexIP = tbListHost.getSelectedRow();
        if (indexIP != -1) {
            Host ipA = hostTableModel.getObject(indexIP);
            if (ipA.getPlatform() == 1) {
                try {
                    String create_time = LibConfig.getCurrentTime();
                    String remoteIP = ipA.getIp();
                    AgletContext context = serverAgent.getAgletContext();
                    URL url = LibConfig.createAgletURL(ipA.getIp());
                    System.out.println(url);
                    int port = 6969 + Integer.parseInt(remoteIP.substring(remoteIP.lastIndexOf(".") + 1, remoteIP.length()));
                    String agentName = "Aglet-" + port + " (Remote)";
                    Object object[] = new Object[]{serverAgent.getProxy(), remoteIP, port};
                    AgletProxy aProxy = context.createAglet(serverAgent.getCodeBase(), "mobileagent.agent.AgentRemoteServer", object);
                    AgletID aID = aProxy.getAgletID();
                    Agent objAgent = new Agent(aID, aProxy, agentName, create_time, aProxy.isActive() ? "Active" : "Invalid", ipA.getIp());
                    agentTableModel.addRow(objAgent);
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Platform is not setup");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose a host!");
        }
    }//GEN-LAST:event_btnReDesktopActionPerformed

    private void btnCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaptureActionPerformed
        int indexIP = tbListHost.getSelectedRow();
        if (indexIP != -1) {
            Host ipA = hostTableModel.getObject(indexIP);
            if (ipA.getPlatform() == 1) {
                try {
                    //Thông tin Aglet
                    String agentName = "Aglet-4434(Capture)";
                    String create_time = LibConfig.getCurrentTime();
                    //Tạo thể hiện contex
                    AgletContext context = serverAgent.getAgletContext();
                    //Tạo mới Aglet
                    URL url = LibConfig.createAgletURL(ipA.getIp());
                    System.out.println(url);
                    AgletProxy aProxy = context.createAglet(serverAgent.getCodeBase(), "mobileagent.agent.AgentCapture", serverAgent.getProxy());
                    AgletProxy dispatchProxy = aProxy.dispatch(url);
                    AgletID aID = aProxy.getAgletID();
                    //Lưu vào mảng
                    Agent objAgent = new Agent(aID, dispatchProxy, agentName, create_time, aProxy.isActive() ? "Active" : "Invalid", ipA.getIp());
                    agentTableModel.addRow(objAgent);
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Platform is not setup");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose a host!");
        }
    }//GEN-LAST:event_btnCaptureActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
        int indexIP = tbListHost.getSelectedRow();
        if (indexIP != -1) {
            final Host ipA = hostTableModel.getObject(indexIP);
            if (ipA.getPlatform() == 1) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("atp://" + ipA.getIp() + ":" + LibConfig.AGLET_DEFAULT_PORT);
                            AgletProxy ap = serverAgent.getAgletContext().createAglet(serverAgent.getCodeBase(), "mobileagent.agent.AgentChatServer", url);
                        } catch (Exception ex) {
                            System.err.println(ex);
                        }
                    }
                }.start();
            } else {
                JOptionPane.showMessageDialog(this, "Platform is not setup");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose a host!");
        }
    }//GEN-LAST:event_btnChatActionPerformed

    private void btnSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSystemActionPerformed
        int indexIP = tbListHost.getSelectedRow();
        if (indexIP != -1) {
            Host ipA = hostTableModel.getObject(indexIP);
            if (ipA.getPlatform() == 1) {
                String[] action = {"Shutdown", "Restart", "Logout"};
                String input = (String) JOptionPane.showInputDialog(this, "Choose an action", " System ", JOptionPane.QUESTION_MESSAGE, null, action, -1);
                System.out.println(input);
                if (input != null) {
                    int i = 0;
                    for (; i < action.length; i++) {
                        if (input.equals(action[i])) {
                            break;
                        }
                    }
                    AgletContext ct = serverAgent.getAgletContext();
                    try {
                        AgletProxy ap = ct.createAglet(serverAgent.getCodeBase(), "mobileagent.agent.AgentController", String.valueOf(i));
                        ap.dispatch(LibConfig.createAgletURL(ipA.getIp()));
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Platform is not setup");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose a host!");
        }
    }//GEN-LAST:event_btnSystemActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        scanIp.stopScan();
        try {
            scanIp.startScan();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotiActionPerformed
        JTextArea taNoti = new JTextArea(12, 35);
        switch (JOptionPane.showConfirmDialog(this, new JScrollPane(taNoti), "Message", JOptionPane.CANCEL_OPTION)) {
            case JOptionPane.OK_OPTION:
                String noti = taNoti.getText().trim();
                System.out.println("'" + noti + "'");
                SendNoti sendNoti = new SendNoti(serverAgent, hostTableModel);
                sendNoti.startSend(noti);
                break;
        }
    }//GEN-LAST:event_btnNotiActionPerformed

    private void tbListAgentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListAgentMouseClicked
        int index = tbListAgent.getSelectedRow();
        Agent agent = agentTableModel.getObject(index);
        for (Host host : listHost) {
            if (agent.getaIp().equals(host.getIp())) {
                setSystemInfo(host);
            }
        }
        setAgentInfo(agent);
    }//GEN-LAST:event_tbListAgentMouseClicked

    private void tbListHostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListHostMouseClicked
        tbListAgent.scrollRectToVisible(tbListAgent.getCellRect(0, 0, true));
        int index = tbListHost.getSelectedRow();
        Host host = hostTableModel.getObject(index);
        setSystemInfo(host);
    }//GEN-LAST:event_tbListHostMouseClicked

    private void mCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mCloseActionPerformed

    private void mPathConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPathConfActionPerformed
        fcPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fcPath.setDialogTitle("Chọn đường dẫn lưu ảnh");
        fcPath.setAcceptAllFileFilterUsed(false);
        if (fcPath.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fcPath.getSelectedFile();
            LibConfig.IMAGE_PATH = file.getPath();
            System.out.println(LibConfig.IMAGE_PATH);
        } else {
            System.out.println("Chưa chọn đường dẫn");
        }
    }//GEN-LAST:event_mPathConfActionPerformed

    public static void main(String args[]) {
        try {
            setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }

        invokeLater(new Runnable() {
            public void run() {
                new ServerWindows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapture;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnNoti;
    private javax.swing.JButton btnReDesktop;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSystem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JFileChooser fcPath;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb10;
    private javax.swing.JLabel lb11;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JLabel lb9;
    private javax.swing.JLabel lbAID;
    private javax.swing.JLabel lbALocal;
    private javax.swing.JLabel lbAName;
    private javax.swing.JLabel lbAStatus;
    private javax.swing.JLabel lbATime;
    private javax.swing.JLabel lbSArch;
    private javax.swing.JLabel lbSIP;
    private javax.swing.JLabel lbSName;
    private javax.swing.JLabel lbSOS;
    private javax.swing.JLabel lbSVersion;
    private javax.swing.JLabel lbbottom;
    private javax.swing.JMenuItem mClose;
    private javax.swing.JMenuItem mPathConf;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel11;
    private javax.swing.JPanel panel111;
    private javax.swing.JPanel panel112;
    private javax.swing.JPanel panel12;
    private javax.swing.JPanel panel121;
    private javax.swing.JPanel panel1211;
    private javax.swing.JPanel panel1212;
    private javax.swing.JPanel panel1213;
    private javax.swing.JPanel panel1215;
    private javax.swing.JPanel panel1216;
    private javax.swing.JPanel panel122;
    private javax.swing.JPanel panel1221;
    private javax.swing.JPanel panel1222;
    private javax.swing.JPanel panel1223;
    private javax.swing.JPanel panel1224;
    private javax.swing.JPanel panel1225;
    private javax.swing.JPanel panel13;
    private javax.swing.JPanel panel2;
    private javax.swing.JScrollPane spListAgent;
    private javax.swing.JScrollPane spListHost;
    private javax.swing.JTable tbListAgent;
    private javax.swing.JTable tbListHost;
    // End of variables declaration//GEN-END:variables

    public void setAgentInfo(Agent agent) {
        lbAID.setText(": " + agent.getaId().toString()); // id
        lbAName.setText(": " + agent.getaName());  // name
        lbALocal.setText(": " + agent.getaIp());//"Server");
        lbATime.setText(": " + agent.getaTime());//" time");
        lbAStatus.setText(": " + agent.getaStatus());//" status");
    }

    public void setSystemInfo(Host host) {
        lbSName.setText(": " + host.getName());
        lbSOS.setText(": " + host.getOs());
        lbSArch.setText(": " + host.getArch());
        lbSVersion.setText(": " + host.getVersion());
        lbSIP.setText(": " + host.getIp());
    }

    public boolean checkClick() {
        int index = tbListAgent.getSelectedRow();
        if (index != -1) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Select one agent!");
        return false;
    }
 
    public synchronized void add(Host objHost) {
        hostTableModel.addRow(objHost);
    }
}
