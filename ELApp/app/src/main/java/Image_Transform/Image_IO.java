package Image_Transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存图片的类
 */
public class Image_IO {

    private final static String CACHE = "/css";

    /**
     * @param context:The context to include the imageView
     * @param source      :The source in the  sdcard/pictures/...
     * @param imageView   :the ImageView that contains the picture
     */
    public static void SetImage(Context context, String source, ImageView imageView) {
        //placeHolder  error.  image waiting
        File file = new File(Environment.getExternalStorageDirectory()
                , "/pictures/" + source);

        Picasso.with(context)
                .load(file)
                .transform(getTransformation(imageView))
                .into(imageView);
    }

    /**
     * @param context   :The context to include the imageView
     * @param R_Source  :The source in the R file
     * @param imageView :The ImageView Target to place the image
     */

    public static void SetImage(Context context, int R_Source, ImageView imageView) {
        Picasso.with(context)
                .load(R_Source)
                .transform(getTransformation(imageView))
                .into(imageView);
    }

    public static void SetImage_Circlize(Context context, String source, ImageView imageView) {
        File file = new File(Environment.getExternalStorageDirectory()
                , "/pictures/" + source);

        Picasso.with(context)
                .load(file)
                .transform(getTransformation(imageView))
                .transform(new CircleTransform())
                .into(imageView);
    }

    public static void SetImage_Circlize(Context context, int R_Source, ImageView imageView) {
        Picasso.with(context)
                .load(R_Source)
                .transform(getTransformation(imageView))
                .transform(new CircleTransform())
                .into(imageView);

    }

    /**
     * 保存图片的方法 保存到sdcard
     *
     * @throws Exception
     */
    public static void saveImage(Bitmap bitmap, String imageName)
            throws Exception {
        String filePath = isExistsFilePath();
        FileOutputStream fos = null;
        File file = new File(filePath, imageName);
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return SDcardDic
     */
    public static File getSDcardDic() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile();// 获取根目录
        } else {
            Log.e("ERROR", "没有内存卡");
        }
        return sdDir;
    }

    /**
     * 获取sd卡的缓存路径， 一般在卡中sdCard就是这个目录
     *
     * @return SDPath
     */
    public static String getSDPath() {
        return getSDcardDic().getAbsolutePath();
    }

    /**
     * 获取缓存文件夹目录 如果不存在创建 否则则创建文件夹
     *
     * @return filePath
     */
    private static String isExistsFilePath() {
        String filePath = getSDPath() + CACHE;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return filePath;
    }

    private static Transformation getTransformation(final ImageView view) {
        return new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = view.getWidth();

                //返回原图
                if (source.getWidth() == 0 || source.getWidth() < targetWidth) {
                    return source;
                }

                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight == 0 || targetWidth == 0) {
                    return source;
                }
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
    }

}
