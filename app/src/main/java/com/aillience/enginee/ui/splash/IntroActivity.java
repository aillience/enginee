package com.aillience.enginee.ui.splash;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.aillience.enginee.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override
                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.btn_login_pressed)
                .title("第一页")
                .description("继续吧")
                .build());

        addSlide(new CustomSlide());

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.btn_login_pressed)
                        .buttonsColor(R.color.btn_register_pressed)
                        .possiblePermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS})
                        .neededPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
                        .image(R.drawable.img_error)
                        .title("点击事件")
                        .description("要不要试试？")
                        .build(),
                new MessageButtonBehaviour(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("哈哈，真点了啊!");
                    }
                }, "点击看看"));

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.btn_logout_normal)
                .buttonsColor(R.color.btn_register_pressed)
                .image(R.drawable.progress_bkg)
                .title("最后一页了哟")
                .description("开启软件之旅吧！")
                .build());
    }

    @Override
    public void onFinish() {
        super.onFinish();
        Toast.makeText(this, "引导完毕!)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}