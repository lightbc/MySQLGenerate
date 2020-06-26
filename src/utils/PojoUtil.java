package utils;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Description:实体类生成工具
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/22 21:24
 * @version 1.0
 */
public class PojoUtil {
    private static PojoUtil pojo;
    private String entity = "pojo";
    private static String classModifier = "public class";
    private static String pojoFieldModifier = "private";

    private PojoUtil(){}

    /**
     * 获取实例
     * @return
     */
    public static PojoUtil getInstance(){
        if (pojo == null){
            pojo = new PojoUtil();
        }
        return pojo;
    }

    /**
     * 判断路径是否存在
     * @param dirPath
     * @return
     */
    public boolean isExistsDirPath(String dirPath){
        return new File(dirPath).exists();
    }

    /**
     * 判断文件是否存在
     * @param dirPath
     * @return
     */
    public boolean isExistsFilePath(String dirPath,String fileName){
        return new File(getPojoFilePath(dirPath,fileName)).exists();
    }

    /**
     * 获取实体类的目录路径
     * @return
     */
    public String getPojoDirPath(){
        return SystemUtil.getInstance().getDirs() + Constants.PATH_SEPARATOR + entity;
    }

    /**
     * 获取实体类文件完整路径
     * @param dirPath
     * @param fileName
     * @return
     */
    public String getPojoFilePath(String dirPath,String fileName){
        return dirPath + Constants.PATH_SEPARATOR + fileName + Constants.POJO_FILE_EXTENSION;
    }

    /**
     * 判断是否创建实体类对象文件
     * @param fileName
     * @return
     */
    public boolean isCreatePojoFile(String fileName){
        String dirPath = getPojoDirPath();
        if (!isExistsDirPath(dirPath)){
            new File(dirPath).mkdirs();
        }
        if (!isExistsFilePath(dirPath,fileName) && isExistsDirPath(dirPath)){
            try {
                new File(getPojoFilePath(dirPath,fileName)).createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (isExistsFilePath(dirPath,fileName)){
            return true;
        }
        return false;
    }

    /**
     * 生成实体类文件
     * @param oTableName
     * @param dbName
     * @param connected
     * @return
     */
    public String createPojoFile(String oTableName, String dbName, boolean connected){
        String msg = Constants.CREATE_OBJ_FAIL;
        String fileName = getFileName(oTableName);
        if (isCreatePojoFile(fileName)){
            String content = getContent(oTableName,dbName,connected);
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
                fos = new FileOutputStream(new File(getPojoFilePath(getPojoDirPath(),fileName)));
                bos = new BufferedOutputStream(fos);
                bos.write(content.getBytes());
                bos.flush();
                msg = Constants.CREATE_OBJ_SUCCESS;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if ( bos != null){
                        bos.close();
                    }
                    if (fos != null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }

    /**
     * 获取处理后的表名作为实体类类名
     * @param tableName
     * @return
     */
    public String getFileName(String tableName){
        return NamesProcess.getInstance().getNewName(tableName);
    }

    /**
     * 组装生成文件的内容信息
     * @param oTableName
     * @param dbName
     * @param connected
     * @return
     */
    public String getContent(String oTableName, String dbName, boolean connected){
        StringBuilder builder = null;
        if (connected){
            String sql = "SELECT * FROM "+dbName+"."+oTableName+" LIMIT 1";
            List list = Connect.getInstance().getColumnNamesInfo(sql);
            Map columnMap = (Map) list.get(0);
            Map javaTypeMap = (Map) list.get(1);
            builder = new StringBuilder();
            builder.append(classModifier+Constants.WHITE_SPACE+getFileName(oTableName)+Constants.CODE_SEPARATOR_LEFT+"\n");
            for (int i=0;i<columnMap.size();i++){
                String column = columnMap.get(i).toString();
                String javaType = javaTypeMap.get(column).toString();
                builder.append(Constants.TAB+pojoFieldModifier+Constants.WHITE_SPACE+javaType.substring(javaType.lastIndexOf(".")+1)+Constants.WHITE_SPACE+NamesProcess.getInstance().getNewName(column)+Constants.SEMICOLON+Constants.CRLF+Constants.CRLF);
            }
            builder.append(Constants.CODE_SEPARATOR_RIGHT+Constants.CRLF);
        }
        return builder.toString();
    }
}
