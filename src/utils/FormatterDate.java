package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:日期格式类
 * Package:utils
 *
 * @author lightbc
 * Date:2020/6/19 22:28
 * @version 1.0
 */
public class FormatterDate {
    private static FormatterDate fd;

    private FormatterDate(){}

    /**
     * 获取实例
     * @return
     */
    public static FormatterDate getInstance(){
        if (fd == null){
            fd = new FormatterDate();
        }
        return fd;
    }

    /**
     * 格式化时间:yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public String formatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

    }
}
