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
    public static final String DB_URL_IP = "mysql.connect.url.ip";//IP
    public static final String DB_URL_PORT = "mysql.connect.url.port";//端口
    public static final String DB_URL_DBNAME = "mysql.connect.url.dbname";//数据库名
    public static final String DB_URL_PREFIX="jdbc:mysql://";//数据库地址前缀
    public static final String DB_USER = "mysql.connect.username";//数据库用户名
    public static final String DB_PWD = "mysql.connect.password";//数据库密码
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";//数据库驱动
    public static final String URL_PARAMS = "?characterEncoding=utf8&useSSL=false";//数据库连接参数
    public static final String SELECT_TABLE_NAMES = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA=";//查询表名SQL
    public static final String COMMON_DB_WARNING = "请先连接数据库！";
    public static final String MESSAGE_DIALOG_TITLE = "消息提示";
    public static final String DB_IP_EMPTY = "IP地址为空！";
    public static final String DB_PORT_EMPTY = "端口为空！";
    public static final String DB_NAME_EMPTY = "数据库为空！";
    public static final String DB_USERNAME_EMPTY = "用户名为空！";
    public static final String DB_PASSWORD_EMPTY = "密码为空！";
    public static final String DB_CONNECT_SUCCESS = "数据库连接成功！";
    public static final String DB_CONNECT_FAIL = "数据库连接失败！";
    public static final String SAVE_CONFIG_INFO_SUCCESS = "配置信息保存成功！";
    public static final String SAVE_CONFIG_INFO_FAIL = "配置信息保存失败！";
    public static final String CREATE_OBJ_SUCCESS = "创建实体类文件成功！";
    public static final String CREATE_OBJ_FAIL = "创建实体类文件失败！";
    public static final String CREATE_OBJ_EXCEPTION = "创建实体类文件异常！";
    public static final String GET_TABLE_ERROR = "表名获取失败!";
    public static final String GET_ALL_TABLE_NAMES = "选择全部";
    public static final String CONFIG_FILE_NAME = "config.properties";//配置文件
    public static final String PATH_SEPARATOR = "/";//路径分隔符
    public static final String POJO_FILE_EXTENSION = ".java";//实体类文件拓展名
    public static final String TABLE_NAME_SEPARATOR = "_";//表名分割符
    public static final String SYSTEM_SEPARATOR = ",";//系统分隔符
    public static final String EMPTY_STR = "";//空字符
    public static final String WHITE_SPACE = " ";//空格
    public static final String CODE_SEPARATOR_LEFT = "{";
    public static final String CODE_SEPARATOR_RIGHT = "}";
    public static final String CRLF = "\n";//换行符
    public static final String TAB = "\t";//制表符
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static final String TIP_URL="请输入IP地址";
    public static final String TIP_USER_NAME="请输入账号";
    public static final String TIP_PWD="请输入密码";
    public static final String TIP_SEARCH="请输入表名";
    public static final String TIP_PORT="请输入端口号";
    public static final String TIP_DB="请输入数据库名";
}
