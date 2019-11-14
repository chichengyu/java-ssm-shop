package cm.xiaochi.ssm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtils {

    /**
     * 上传文件重名称
     * @param fileName 文件名称
     * @return
     */
    public static String renameFileName(String fileName){
        String suff = fileName.substring(fileName.lastIndexOf("."));
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ new Random().nextInt(100) + suff;
    }
}
