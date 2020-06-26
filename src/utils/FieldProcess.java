package utils;

import java.sql.Connection;
import java.util.List;

/**
 * Description:处理字符串
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/16 21:37
 * @version 1.0
 */
public class FieldProcess {

    /**
     * 获取表名
     * @param dbName
     * @return
     */
    public List getTableNames(String dbName){
        Connection connection = Connect.getInstance().getConnection();
        List tbNames = null;
        if (connection != null){
            tbNames = Connect.getInstance().getTableNames(Constants.SELECT_TABLE_NAMES + "\'" + dbName + "\'");
        }
        return tbNames;
    }

    /**
     * 获取数据库名称
     * @param url
     * @return
     */
    public String getDbName(String url){
        String dbName = "";
        if (url != null && !Constants.EMPTY_STR.equals(url)){
            if (url.indexOf("?") != -1){
                dbName = url.substring(url.lastIndexOf("/")+1,url.indexOf("?"));
            }else {
                dbName = url.substring(url.lastIndexOf("/")+1);
            }
        }
        return dbName;
    }

    /**
     * 获取完整带参数据库地址
     * @param url
     * @return
     */
    public String getFullUrl(String url){
        String fullUrl = "";
        if (url != null && !Constants.EMPTY_STR.equals(url)){
            if (url.indexOf("?") != -1 && url.lastIndexOf("&") != -1){
                fullUrl = url;
            }else if (url.indexOf("?") == -1){
                fullUrl = url + Constants.URL_PARAMS;
            }else if (url.substring(url.indexOf("?")).length() == 1){
                fullUrl = url.substring(0,url.indexOf("?")) + Constants.URL_PARAMS;
            }
        }
        return fullUrl;
    }
}
