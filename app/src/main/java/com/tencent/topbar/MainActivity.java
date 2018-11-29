package com.tencent.topbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUITopBar;


public class MainActivity extends AppCompatActivity {

    FadingShadowView  mShadow;
    NestedScrollView  mScrollView;
    private float mFullShadowScrollDistancePxInv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mShadow = (FadingShadowView) findViewById(R.id.shadow);
        this.mFullShadowScrollDistancePxInv = 1.0f / getResources().getDimension(R.dimen.enhanced_bookmark_full_shadow_scroll_distance);

        mScrollView=findViewById(R.id.scroll);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                float f ;
                if (mShadow != null) {
                    f = Math.min(1.0f, (i1) *mFullShadowScrollDistancePxInv);
                    mShadow.setStrength(f);
                }

            }
        });
    }
}
