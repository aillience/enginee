package com.yfl.library.util.images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yfl.library.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

//===============================
// 千万不要出现 B U G ，出现就 G G
//===============================
// @author:yfl
// @date: 2019-03-15
// @description:就是一个普通类
//因为图片都是网络中获取到的，不知道其具体大小，因此一开始采用target来实现
//===============================
public class MySimpleTarget extends SimpleTarget<Drawable> {

    private ImageView imageView;
    //暂时不用
    private RequestOptions options;
    private Object tag;
//    private boolean isLongPic = false;
    public MySimpleTarget(ImageView view) {
        this.imageView = view;
//        this.options = new RequestOptions();
    }
    public MySimpleTarget(RequestOptions options, ImageView view){
        this.imageView = view;
        this.options = options;
    }
    public MySimpleTarget(Object tag, ImageView view){
        this.imageView = view;
        this.tag = tag;
    }
//    public MySimpleTarget(RequestOptions options,ImageView view,boolean isLong){
//        this.imageView = view;
//        this.options = options;
//        this.isLongPic = isLong;
//    }
    /**
     * The method that will be called when the resource load has finished.
     *
     * @param resource   the loaded resource.
     * @param transition
     * 这里可以做一些统一图片加载处理
     */
    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//        int imageWidth=resource.getWidth();
//        int imageHeight=resource.getHeight();
//        //计算比例,以高度为准
//        float dx = imageWidth / imageHeight;
//        int height = imageHeight;
//        int width = height * imageHeight / imageWidth;
//        ViewGroup.LayoutParams params=imageView.getLayoutParams();

//        params.width=R.dimen.dimen_25;
//        params.height= (imageHeight * params.width) / imageWidth;
//        imageView.setImageBitmap(resource);
//        Bitmap bitmap = compressImage(resource);
//        Bitmap bitmap = compressImage(resource);
        if(tag != null){
            if(!tag.equals(imageView.getTag(R.id.image_url))){
                return;
            }
        }

        if(options != null){
//            MyLog.i("这里是有设置大小的:"+options.getOverrideWidth());
            Glide.with(imageView.getContext()).asDrawable().load(resource).apply(options).into(imageView);
        }else {
            imageView.setImageDrawable(resource);
        }
        //add by yfl on 20190611 加载成功后，清除它的tag,试试能不能解决网络卡顿下图片错位问题
        imageView.setTag(R.id.image_url,"");

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        imageView.setImageDrawable(errorDrawable);
    }

    //压缩图片，将图片压缩后显示，后台可能会有大图
    //质量压缩 图片的大小是没有变的，因为质量压缩不会减少图片的像素，
    // 它是在保持像素的前提下改变图片的位深及透明度等，来达到压缩图片的目的，
    // 这也是为什么该方法叫质量压缩方法。那么，图片的长，宽，像素都不变，那么bitmap所占内存大小是不会变的。
    private static Bitmap compressImage(Bitmap image) {
        if (image == null) {
            return null;
        }
        if(image.getByteCount() < 200 * 1024){
            //小于200K的不压缩
            return image;
        }
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
//            ByteArrayInputStream isBm = new ByteArrayInputStream(bytes);
//            return BitmapFactory.decodeStream(isBm);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
