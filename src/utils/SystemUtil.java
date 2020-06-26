package utils;

import java.net.URISyntaxException;

/**
 * Description:系统工具类
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/22 21:29
 * @version 1.0
 */
public class SystemUtil {
    private static SystemUtil sys;

    private SystemUtil(){}

    /**
     * 获取实例
     * @return
     */
    public static SystemUtil getInstance(){
        if (sys == null){
            sys = new SystemUtil();
        }
        return sys;
    }

    /**
     * 获取当前项目所在路径
     * @return
     */
    public String getDirs(){
        String projectPath = "";
        try {
            projectPath = ConfigProcess.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            projectPath = projectPath.substring(1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return projectPath;
    }
}
