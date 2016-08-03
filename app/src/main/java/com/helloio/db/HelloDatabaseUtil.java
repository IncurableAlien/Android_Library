package com.helloio.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.helloio.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Y
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class HelloDatabaseUtil {

    private static final String TAG = "HelloDatabaseUtil";
    private Context context;
    public final static String DATABASE_NAME = "helloio.db";   // 数据库的名字
    private static String DATABASE_PATH;                        //目录是准备放 SQLite 数据库的地方，也是 Android 程序默认的数据库存储目录

    public HelloDatabaseUtil(Context context) {
        this.context = context;
        String packageName = context.getPackageName();
        DATABASE_PATH = "/data/data/" + packageName + "/databases/";
    }

    /**
     * 判断数据库是否存在
     *
     * @return false or true
     */
    public boolean checkDataBase() {
        SQLiteDatabase db = null;
        try {
            String databaseFilename = DATABASE_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(databaseFilename, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return db != null ? true : false;
    }

    /**
     * 复制数据库到手机指定文件夹下
     *
     * @throws IOException
     */
    public void copyDataBase() {
        String databaseFilenames = DATABASE_PATH + DATABASE_NAME;
        File dir = new File(DATABASE_PATH);
        if (!dir.exists())// 判断文件夹是否存在，不存在就新建一个
            dir.mkdir();
        FileOutputStream os = null;// 得到数据库文件的写入流
        InputStream is = null;
        try {
            os = new FileOutputStream(databaseFilenames);
            is = context.getResources().openRawResource(R.raw.helloio);// 得到数据库文件的数据流
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyDataBaseToPhone(Context context) {
        HelloDatabaseUtil util = new HelloDatabaseUtil(context);
        // 判断数据库是否存在
        boolean dbExist = util.checkDataBase();
        if (dbExist) {
            Log.i(TAG, "The database is exist.");
        } else {// 不存在就把raw里的数据库写入手机
            util.copyDataBase();
        }
    }

    public static boolean copyDataBaseFromAssert(Context context) {
        // 检查 SQLite 数据库文件是否存在
        if ((new File(DATABASE_PATH + DATABASE_NAME)).exists() == false) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
            File f = new File(DATABASE_PATH);
            // 如 database 目录不存在，新建该目录
            if (!f.exists()) {
                f.mkdir();
            }

            try {
                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
                InputStream is = context.getAssets().open(DATABASE_NAME);
                // 输出流
                OutputStream os = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

                // 文件写入
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                // 关闭文件流
                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @return
     */
    public static boolean testCopyResult() {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + DATABASE_NAME, null);
        Cursor cursor = database.rawQuery("select * from article", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String title = cursor.getString(2);
            Log.i(TAG, " id: " + id + " title:"+ title);
        }
        cursor.close();
        return false;
    }

    /**
     *
     *
     * 另一篇:
     android从assets复制数据库到/databases的替代方法
     经常我们需要使用一些事先做好内容的数据库，比如字典等， 这就要加载已有的数据库文件，通常把它们放在assets 或者raw中，在程序运行时copy到程序内部空间：/data/data/package/my_app/databases， 详细的做法看这里。

     但是这样有个缺点，当文件较大时会占用较长的时间，用户难免会有点小着急。

     我们可以这样HACK来把copy的环节省去：

     1 把数据库文件（如MYDB.db) 放到PROJECT/libs/armeabi/ 改名为libMYDB.db.so（注意前面必需加”lib"）。

     2 用这样的方式打开：
     Java代码  收藏代码
     DATABASE_PATH = "/data/data/" + packageName + "/lib/";
     SQLiteDatabase db = SQLiteDatabase.openDatabase(DATABASE_PATH + libMYDB.db.so, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

     这样等程序一安装完就可以使用数据库了，其原理是把数据库文件伪装成lib库文件，程序在安装时会自动copy到/data/data/package/lib/，省去了我们自己的copy过程。

     目前我测试了读操作，写操作还有待测试。
     *
     *
     */
}
