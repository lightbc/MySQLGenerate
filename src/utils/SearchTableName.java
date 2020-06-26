package utils;

import javax.swing.*;

/**
 * Description:表名快速查找
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/20 21:56
 * @version 1.0
 */
public class SearchTableName {
    /**
     * 获取新的组件:将符合查找规则的表名优先显示
     * @param comboBox
     * @param in
     * @return
     */
    public JComboBox getNewComboBox(JComboBox comboBox, String in){
        if (comboBox != null && comboBox.getItemCount()>0 && in != null && !Constants.EMPTY_STR.equals(in)){
            Integer count = comboBox.getItemCount();
            for (int i=0;i<count;i++){
                String item = comboBox.getItemAt(i).toString();
                if (item.toLowerCase().startsWith(in.toLowerCase())){
                    comboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
        return comboBox;
    }
}
