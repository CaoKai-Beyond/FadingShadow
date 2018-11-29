package com.tencent.topbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：caokai on 2018/11/28 16:21
 * <p>
 * email：caokai@11td.com
 */
public class FadingShadowView extends View {
    private FadingShadow mFadingShadow;
    private int mPosition;
    private float mStrength;

    public FadingShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FadingShadowView);
        int color=obtainStyledAttributes.getColor(R.styleable.FadingShadowView_Color,getResources().getColor(R.color.enhanced_bookmark_app_bar_shadow_color));
        this.mFadingShadow = new FadingShadow(color);
        mPosition=obtainStyledAttributes.getInt(R.styleable.FadingShadowView_Position,0);
        mStrength=obtainStyledAttributes.getFloat(R.styleable.FadingShadowView_Strength,0.0f);
    }

    public void init(int i, int i2) {
        this.mFadingShadow = new FadingShadow(i);
        this.mPosition = i2;
        postInvalidateOnAnimation(this);
    }

    public void setStrength(float f) {
        if (this.mStrength != f) {
            this.mStrength = f;
            postInvalidateOnAnimation(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mFadingShadow != null) {
            this.mFadingShadow.drawShadow(this, canvas, this.mPosition, (float) getHeight(), this.mStrength);
        }
    }

    public void postInvalidateOnAnimation(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

}
