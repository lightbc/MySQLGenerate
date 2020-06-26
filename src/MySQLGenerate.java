import utils.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:MySQL代码生成工具
 * Package:PACKAGE_NAME
 *
 * @author lightbc
 * Date:2020/6/14 18:43
 * @version 1.0
 */
public class MySQLGenerate {
    private JPanel mainPanel;//主面板
    private JLabel dbUrl;//数据库地址
    private JLabel dbUserName;//数据库账号
    private JLabel dbPwd;//数据库密码
    private JLabel dbTable;//数据库表名
    private JTextField url;//地址
    private JTextField userName;//账号
    private JTextField pwd;//密码
    private JPanel argPanel;//参数面板
    private JPanel doPanel;//控制面板
    private JButton conn;//测试链接
    private JButton getTableName;//获取表名
    private JButton createObj;//创建实体对象
    private JButton saveConfig;//保存配置信息
    private JComboBox tableName;//表名列表
    private JLabel searchTableName;//快速查询表名
    private JTextField textField;//搜索框
    private String msg;// 消息
    private String ul;//数据库地址
    private String ur;//数据库账号
    private String pd;//数据库密码
    private boolean optsFlag;//重新操作判断

    public static void main(String[] args) {
        MySQLGenerate mg = new MySQLGenerate();
        JFrame frame = new JFrame("Author By lightbc V1.0");// 作者信息
        frame.setResizable(false);// 禁止最大化
        frame.setContentPane(mg.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);// 居中显示

        mg.addEventListeners();//事件监听
        frame.setVisible(true);//显示窗体内容
        mg.addConfigInfo();//添加默认配置信息
    }

    /**
     * 空值判断
     * @return
     */
    public boolean isEmpty(){
        boolean flag = false;
        ul = url.getText();
        ur = userName.getText();
        pd = pwd.getText();
        if (Constants.EMPTY_STR.equals(ul) || Constants.EMPTY_STR.equals(ur) || Constants.EMPTY_STR.equals(pd)){
            msg = Constants.DB_VALUE_EMPTY;
        }else {
            flag = true;
        }
        return flag;
    }

    /**
     * 测试连接
     * @return
     */
    public boolean isConnection(){
        boolean flag = false;
        boolean is = false;
        msg = Constants.DB_CONNECT_FAIL;
        if (isEmpty()){
            flag = Connect.getInstance().testConnection(ul,ur,pd);
        }
        if (flag){
            msg = Constants.DB_CONNECT_SUCCESS;
            is = true;
        }
        return is;
    }

    /**
     * 事件监听
     */
    public void addEventListeners(){
        //连接测试
        conn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean flag = isConnection();
                if (flag){
                    JOptionPane.showMessageDialog(mainPanel,msg,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.INFORMATION_MESSAGE);
                    optsFlag = true;
                }else {
                    JOptionPane.showMessageDialog(mainPanel,msg,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                    optsFlag = false;
                }
            }
        });

        //获取表名
        getTableName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    if (optsFlag){
                        try {
                            FieldProcess fp = new FieldProcess();
                            List tbNames = fp.getTableNames(fp.getDbName(ul));
                            tableName.removeAllItems();
                            tableName.setMaximumRowCount(4);
                            tableName.addItem(Constants.GET_ALL_TABLE_NAMES);
                            for (int i=0;i<tbNames.size();i++){
                                tableName.addItem(tbNames.get(i));
                            }
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(mainPanel,Constants.GET_TABLE_ERROR,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                            ex.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(mainPanel,Constants.COMMON_DB_WARNING,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                    }
                }
        });

        // 表名快速查找
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String field = textField.getText();
                tableName = new SearchTableName().getNewComboBox(tableName,field);
            }
        });

        //生成实体类文件java文件
        createObj.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (optsFlag){
                    String oTableName = tableName.getSelectedItem().toString();
                    try {
                        String pojoMsg = PojoUtil.getInstance().createPojoFile(oTableName,new FieldProcess().getDbName(ul),optsFlag);
                        JOptionPane.showMessageDialog(mainPanel,pojoMsg,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanel,Constants.CREATE_OBJ_EXCEPTION,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(mainPanel,Constants.COMMON_DB_WARNING,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //保存配置信息
        saveConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (optsFlag){
                Map map = new HashMap();
                List list = new ArrayList();
                map.put(Constants.DB_URL,ul);
                map.put(Constants.DB_USER,ur);
                map.put(Constants.DB_PWD,pd);
                list.add(Constants.DB_URL);
                list.add(Constants.DB_USER);
                list.add(Constants.DB_PWD);
                boolean isSave = ConfigProcess.getInstance().savePropFile(map,list);
                if (isSave){
                    JOptionPane.showMessageDialog(mainPanel,Constants.SAVE_CONFIG_INFO_SUCCESS,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(mainPanel,Constants.SAVE_CONFIG_INFO_FAIL,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(mainPanel,Constants.COMMON_DB_WARNING,Constants.MESSAGE_DIALOG_TITLE,JOptionPane.WARNING_MESSAGE);
            }
                }
        });
    }

    /**
     * 添加默认配置信息
     */
    public void addConfigInfo(){
        Map map = ConfigProcess.getInstance().readPropFile();
        if (map != null && map.containsKey(Constants.DB_URL) && map.containsKey(Constants.DB_USER) && map.containsKey(Constants.DB_PWD)){
            url.setText(map.get(Constants.DB_URL).toString());
            userName.setText(map.get(Constants.DB_USER).toString());
            pwd.setText(map.get(Constants.DB_PWD).toString());
        }
    }
}
