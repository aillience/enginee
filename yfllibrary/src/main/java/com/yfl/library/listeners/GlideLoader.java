package com.yfl.library.listeners;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yfl.library.R;

//用于图片选择器的，在意见反馈中使用的
public class GlideLoader implements ImageLoader {

	private static final long serialVersionUID = 1L;

	@Override
    public void displayImage(Context context, String path, ImageView imageView) {
        RoundedCorners roundedCorners= new RoundedCorners(12);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .placeholder(R.drawable.img_error)	//加载成功之前占位图 icon_about_myy_logo
                .error(R.drawable.img_error)	//加载错误之后的错误图
                .fallback(R.drawable.img_error)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        //add by yfl 设置控件属性 centerCrop和圆角会冲突
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView.setTag(R.id.image_url,(path == null) ?"":path);
        if( path!= null && path.endsWith(".gif")){
            //Glide GIF显示
            Glide.with(context)
                    .asGif()
                    .load(path)
                    .apply(options)
                    .into(imageView);
        }else {
            //Glide 加载图片简单用法
            Glide.with(context)
                    .asDrawable()
                    .load(path)
                    .apply(options)
                    .into(imageView);
        }
//        Glide.with(context)
//                .load(path)
//                .placeholder(R.drawable.global_img_default)
//                .centerCrop()
//                .into(imageView);
    }

}
