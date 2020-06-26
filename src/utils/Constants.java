package utils;

/**
 * Description:常量
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/16 22:44
 * @version 1.0
 */
public class Constants {
    public static final String DB_URL = "mysql.connect.url";
    public static final String DB_USER = "mysql.connect.username";
    public static final String DB_PWD = "mysql.connect.password";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL_PARAMS = "?characterEncoding=utf8&useSSL=false";
    public static final String SELECT_TABLE_NAMES = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA=";
    public static final String COMMON_DB_WARNING = "请先连接数据库！";
    public static final String MESSAGE_DIALOG_TITLE = "消息提示";
    public static final String DB_VALUE_EMPTY = "地址/用户名/密码不能为空！";
    public static final String DB_CONNECT_SUCCESS = "数据库连接成功！";
    public static final String DB_CONNECT_FAIL = "数据库连接失败！";
    public static final String SAVE_CONFIG_INFO_SUCCESS = "配置信息保存成功！";
    public static final String SAVE_CONFIG_INFO_FAIL = "配置信息保存失败！";
    public static final String CREATE_OBJ_SUCCESS = "创建实体类文件成功！";
    public static final String CREATE_OBJ_FAIL = "创建实体类文件失败！";
    public static final String CREATE_OBJ_EXCEPTION = "创建实体类文件异常！";
    public static final String GET_TABLE_ERROR = "表名获取失败!";
    public static final String GET_ALL_TABLE_NAMES = "选择全部";
    public static final String CONFIG_FILE_NAME = "config.properties";
    public static final String PATH_SEPARATOR = "/";
    public static final String POJO_FILE_EXTENSION = ".java";
    public static final String TABLE_NAME_SEPARATOR = "_";
    public static final String SYSTEM_SEPARATOR = ",";
    public static final String EMPTY_STR = "";
    public static final String WHITE_SPACE = " ";
    public static final String CODE_SEPARATOR_LEFT = "{";
    public static final String CODE_SEPARATOR_RIGHT = "}";
    public static final String CRLF = "\n";
    public static final String TAB = "\t";
    public static final String SEMICOLON = ";";
}
