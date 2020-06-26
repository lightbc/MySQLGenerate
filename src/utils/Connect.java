package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:和MySQL数据库进行连接，返回连接对象
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/14 21:15
 * @version 1.0
 */
public class Connect {
    private static Connect connect;
    private Connection connection;// 连接对象
    private Integer tableNameIndex = 1;

    private Connect(){}

    /**
     * 获取实例
     * @return
     */
    public static Connect getInstance(){
        if (connect == null){
            connect = new Connect();
        }
        return connect;
    }

    /**
     * 测试数据库连接
     * @param url
     * @param user
     * @param pwd
     * @return
     */
    public boolean testConnection(String url,String user,String pwd){
        boolean flag;
        try {
            Class.forName(Constants.DB_DRIVER);
            url = getFullUrl(url);
            connection = DriverManager.getConnection(url,user,pwd);
            flag = true;
        } catch (ClassNotFoundException e) {
            flag = false;
            e.printStackTrace();
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取预处理对象PreparedStatement
     * @param sql
     * @return
     */
    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement pstmt = null;
        try {
            if (connection != null){
                pstmt = connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 获取未处理的数据库表名
     * @param sql
     * @return
     */
    public List getTableNames(String sql){
        List list = new ArrayList();
        PreparedStatement pstmt = getPreparedStatement(sql);
        ResultSet rs;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()){
                list.add(rs.getString(tableNameIndex));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取各列的信息
     * @param sql
     * @return
     */
    public List<Map> getColumnNamesInfo(String sql){
        List list = new ArrayList();
        Map columnMap = new HashMap();
        Map javaTypeMap = new HashMap();
        PreparedStatement pstmt = getPreparedStatement(sql);
        ResultSet rs;
        ResultSetMetaData data;
        try {
            rs = pstmt.executeQuery();
            data = rs.getMetaData();
            for (int i=0;i<data.getColumnCount();i++){
                String columnName = data.getColumnName(i+1);//列名
                String columnClassName = data.getColumnClassName(i+1);//对应数据类型
                columnMap.put(i,columnName);
                javaTypeMap.put(columnName,columnClassName);
            }
            list.add(columnMap);
            list.add(javaTypeMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size() > 0?list:null;
    }

    /**
     * 获取完整带参数据库地址
     * @param url
     * @return
     */
    public String getFullUrl(String url){
        return new FieldProcess().getFullUrl(url);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
