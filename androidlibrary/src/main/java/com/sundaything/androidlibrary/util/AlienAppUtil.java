package com.sundaything.androidlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * @Title: Helloio
 * @Package com.sundaything.androidlibrary.util
 * @Description:
 * @Author Y
 * @Email bellsong@foxmail.com
 * @Date 16/8/4 15:48
 */
public class AlienAppUtil {

    private AlienAppUtil() {
        throw new UnsupportedOperationException("You can not new this Object, because all method is static.");
    }

    /**
     * 安装指定路径下的Apk
     * 根据路径名是否符合和文件是否存在判断是否安装成功
     * @param context
     * @param filePath 需要安装apk的绝对路径
     * @return 是否安装成功
     */
    public static boolean installApp(Context context, String filePath) {
        if (!TextUtils.isEmpty(filePath)
                && filePath.length() > 4
                && filePath.toLowerCase().substring(filePath.length() - 4).equals(".apk")){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            File file = new File(filePath);
            if (file.exists() && file.isFile() && file.length() > 0){
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return true;
            }
        }
        return false;
    }
}
