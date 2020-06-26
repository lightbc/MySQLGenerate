package utils;

import java.io.*;
import java.util.*;

/**
 * Description:配置文件处理
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/18 21:10
 * @version 1.0
 */
public class ConfigProcess {
    private static ConfigProcess configProcess;
    private Properties prop = new Properties();
    private String config = "config";

    private ConfigProcess(){}

    /**
     * 获取实例
     * @return
     */
    public static ConfigProcess getInstance(){
        if (configProcess == null){
            configProcess = new ConfigProcess();
        }
        return configProcess;
    }

    /**
     * 创建目录列表
     * @param dirPath
     * @return
     */
    public boolean isCreateDirs(String dirPath){
        boolean flag = false;
        try {
            File file = new File(dirPath);
            if (!file.exists()){
                file.mkdirs();
            }
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建文件
     * @param dirPath
     * @param filePath
     */
    public File createPropFile(String dirPath,String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            if (isCreateDirs(dirPath)){
                file = new File(filePath);
            }
        }
        return file;
    }

    /**
     * 获取完整文件路径
     * @return
     */
    public String getCompleteFilePath(){
        return SystemUtil.getInstance().getDirs() + config + Constants.PATH_SEPARATOR + Constants.CONFIG_FILE_NAME;
    }

    /**
     * 获取属性文件
     * @return
     */
    public File getPropFile(){
        String projectPath = SystemUtil.getInstance().getDirs();
        String dirPath = projectPath + config;
        String completeConfigPath = dirPath + Constants.PATH_SEPARATOR + Constants.CONFIG_FILE_NAME;
        return createPropFile(dirPath,completeConfigPath);
    }

    /**
     * 读取配置文件信息
     * @return
     */
    public Map<String, String> readPropFile() {
        File file = new File(getCompleteFilePath());
        Map<String, String> map = null;
        if (file.exists()) {
            InputStream is;
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
                is = new BufferedInputStream(fis);
                prop.load(is);
                Iterator<String> it = prop.stringPropertyNames().iterator();
                map = new HashMap();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = prop.getProperty(key);
                    map.put(key, value);
                }
                is.close();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    /**
     * 写入配置文件信息
     * @param dbInfo
     * @param dbKeys
     * @return
     */
    public boolean savePropFile(Map dbInfo, List<String> dbKeys) {
        File file = getPropFile();
        boolean flag = false;
        if (dbInfo != null && dbInfo.size() > 0) {
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                for (int i=0;i<dbKeys.size();i++){
                    prop.setProperty(dbKeys.get(i),dbInfo.get(dbKeys.get(i)).toString());
                }
                prop.store(fos,FormatterDate.getInstance().formatDate(new Date()));
                fos.flush();
                fos.close();
                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
