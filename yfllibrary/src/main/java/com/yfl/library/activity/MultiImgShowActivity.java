package com.yfl.library.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yfl.library.R;
import com.yfl.library.listeners.ImageLoader;
import com.yfl.library.widget.ZoomImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//图片展示界面

/**
 * 用法：
 *     Intent intent = new Intent(mContext, MultiImgShowActivity.class);
 *     ArrayList<String> arrayList = new ArrayList<>(stringArrayList);
 *     intent.putStringArrayListExtra("photos", arrayList);
 *     intent.putExtra("position", holder.getPostion());
 *     //新增网络图片读取
 *     intent.putExtra("isNetWork",false);
 *     intent.putExtra("imageLoader",imageLoader);
 *     //设置进入动画
 *     Activity ac = (Activity) mContext;
 *     ac.startActivity(intent);
 *     ac.overridePendingTransition(R.anim.zoom_in, 0);
 *
 *    Intent intent = new Intent(getActivity(), MultiImgShowActivity.class);
 *    ArrayList<String> arrayList = new ArrayList<>(imgUrls);
 *    intent.putStringArrayListExtra("photos", arrayList);
 *    intent.putExtra("position", position);
 *     //新增网络图片读取
 *    intent.putExtra("isNetWork",true);
 *    intent.putExtra("imageLoader",new GlideLoader());
 *    startActivity(intent);
 */
public class MultiImgShowActivity extends Activity implements ZoomImageView.OnIsSingleListener {
    private ViewPager viewPager;
    private List<String> imgList;
    private ZoomImageView[] mImageView;
    private TextView numText;
    /**
     * 记录当前页卡
     */
    private int current = 0;
    //是否网络图片，网络图片需要使用 imageLoader
    private boolean isNetWork;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        viewPager = (ViewPager) findViewById(R.id.show_img_viewPager);
        numText = (TextView) findViewById(R.id.showimg_text);

        // 图片地址
        imgList = getIntent().getStringArrayListExtra("photos");
        imgList.remove("none");
        current = getIntent().getIntExtra("position", 0);

        //网络图片加载
        isNetWork = getIntent().getBooleanExtra("isNetWork",isNetWork);
        if(isNetWork){
            imageLoader = (ImageLoader) getIntent().getSerializableExtra("imageLoader");
        }

        mImageView = new ZoomImageView[imgList.size()];
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ZoomImageView imageView = new ZoomImageView(MultiImgShowActivity.this);

                if(!isNetWork){
                    String path = imgList.get(position);
                    Bitmap bitmap = buffers.get(path);
                    if (bitmap == null) {
                        try {
                            buffers.put(path, revitionImageSize(path));
                        }
                        catch (Exception ex) {
                            buffers.put(path, BitmapFactory.decodeFile(path));
                        }
                        bitmap = buffers.get(path);
                    }
                    imageView.setImageBitmap(bitmap);
                }else {
                    imageLoader.displayImage(getApplicationContext(), imgList.get(position), imageView);
                }

                container.addView(imageView);
                setListener(imageView);
                mImageView[position] = imageView;

                return imageView;
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mImageView[position]);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mImageView.length;
            }
        });
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                current = arg0;
                numText.setText(arg0 + 1 + "/" + imgList.size());
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });


        //设置当前选中项
        viewPager.setCurrentItem(current, true);
        numText.setText(current + 1 + "/" + imgList.size());
    }


    @Override
    public void onSingleClick() {
        this.finish();
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.zoom_out);
    }

    public void setListener(ZoomImageView imageView) {
        imageView.setOnIsSingleListener(this);
    }


    //图片缓存
    private Map<String, Bitmap> buffers = new HashMap<>();

    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000)
                    && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

}
