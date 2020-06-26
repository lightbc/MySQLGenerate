package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:处理名称:表名/字段名
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/20 21:27
 * @version 1.0
 */
public class NamesProcess {
    private static NamesProcess name;

    private NamesProcess(){}

    /**
     * 获取实例
     * @return
     */
    public static NamesProcess getInstance(){
        if (name == null){
            name = new NamesProcess();
        }
        return name;
    }

    /**
     * 处理名称字符串
     * @param name
     */
    public String doName(String name){
        StringBuilder builder = new StringBuilder(name);
        StringBuilder addBuilder = new StringBuilder();
        String upper;
        List<Integer> list = getSeparatorIndex(name);
        for (int i=0;i<builder.length();i++){
            upper = String.valueOf(builder.charAt(i));
            for (int j=0;j<list.size();j++){
                if (i != (list.get(j)+1)){
                    continue;
                }
                upper = String.valueOf(builder.charAt(i)).toUpperCase();
            }
            if (i == 0){
                upper = String.valueOf(builder.charAt(i)).toUpperCase();
            }
            addBuilder.append(upper);
        }
        return addBuilder != null && addBuilder.length() > 0?addBuilder.toString():null;
    }

    /**
     * 获取分隔符下标值
     * @param name
     * @return
     */
    public List<Integer> getSeparatorIndex(String name){
        StringBuilder builder = new StringBuilder(name);
        List<Integer> list = new ArrayList();
        for(int i=0;i<builder.length();i++){
            if (getSeparator(name).equals(String.valueOf(builder.charAt(i)))){
                list.add(i);
            }
        }
        return list != null && list.size() > 0?list:null;
    }

    /**
     * 判断是否存在分隔符
     * @param name
     * @return
     */
    public boolean isSeparator(String name){
        String[] spt = Constants.TABLE_NAME_SEPARATOR.split(Constants.SYSTEM_SEPARATOR);
        for (int i=0;i<spt.length;i++){
            if (name.indexOf(spt[i]) != -1){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取分隔符
     * @param name
     * @return
     */
    public String getSeparator(String name){
        if (isSeparator(name)){
            String[] spt = Constants.TABLE_NAME_SEPARATOR.split(Constants.SYSTEM_SEPARATOR);
            for (int i=0;i<spt.length;i++){
                if (name.indexOf(spt[i]) != -1){
                    return spt[i];
                }
            }
        }
        return Constants.EMPTY_STR;
    }

    /**
     * 获取新名称
     * @param name
     * @return
     */
    public String getNewName(String name){
        String tn ;
        StringBuilder builder;
        StringBuilder newName = null;
        if (name != null && !Constants.EMPTY_STR.equals(name)){
            newName = new StringBuilder();
            if (!Constants.EMPTY_STR.equals(getSeparator(name))){
                tn = doName(name);
                builder = new StringBuilder(tn);
                for (int i=0;i<builder.length();i++){
                    if (getSeparator(name).equals(String.valueOf(builder.charAt(i)))){
                        continue;
                    }
                    newName.append(builder.charAt(i));
                }
            }else {
                newName.append(name.substring(0,1).toUpperCase() + name.substring(1));
            }
        }
        return newName != null && newName.length() > 0?newName.toString():null;
    }

    /**
     * 获取新名称组
     * @param names
     * @return
     */
    public List<String> getNewNames(List<String> names){
        String tn ;
        StringBuilder builder;
        StringBuilder newName = null;
        List list = null;
        if (names != null && names.size()>0){
            newName = new StringBuilder();
            list = new ArrayList();
            for (int i=0;i<names.size();i++){
                if (!Constants.EMPTY_STR.equals(getSeparator(names.get(i)))){
                    tn = doName(names.get(i));
                    builder = new StringBuilder(tn);
                    for (int j=0;j<builder.length();j++){
                        if (getSeparator(names.get(i)).equals(String.valueOf(builder.charAt(i)))){
                            continue;
                        }
                        newName.append(builder.charAt(i));
                        list.add(newName);
                    }
                }else {
                    newName.append(names.get(i).substring(0,1).toUpperCase() + names.get(i).substring(1));
                    list.add(newName);
                }
            }
        }
        return list != null && list.size()>0?list:null;
    }
}
