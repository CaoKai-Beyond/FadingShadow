package com.tencent.topbar;

/**
 * Author：caokai on 2018/11/28 16:22
 * <p>
 * email：caokai@11td.com
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.view.View;

public class FadingShadow {
    public static final int POSITION_BOTTOM = 1;
    public static final int POSITION_TOP = 0;
    private Matrix mShadowMatrix = new Matrix();
    private Paint mShadowPaint = new Paint();
    private Shader mShadowShader;

    public FadingShadow(int i) {
        float[] fArr = new float[8];
        int[] iArr = new int[8];
        int i2 = i & 16777215;
        int alpha = Color.alpha(i);
        for (int i3 = 0; i3 < 8; i3++) {
            float f = ((float) i3) / 7.0f;
            float f2 = (1.0f - (2.2f * f)) + ((1.8f - (0.6f * f)) * (f * f));
            fArr[i3] = f;
            iArr[i3] = (Math.round(((float) alpha) * f2) << 24) | i2;
        }
        this.mShadowShader = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, iArr, fArr, TileMode.CLAMP);
    }

    public void drawShadow(View view, Canvas canvas, int i, float f, float f2) {
        float max = Math.max(0.0f, Math.min(1.0f, f2)) * f;
        if (max >= 1.0f) {
            int scrollX = view.getScrollX();
            int right = scrollX + view.getRight();
            int scrollY;
            if (i == 1) {
                scrollY = (view.getScrollY() + view.getBottom()) - view.getTop();
                this.mShadowMatrix.setScale(1.0f, max);
                this.mShadowMatrix.postRotate(180.0f);
                this.mShadowMatrix.postTranslate((float) scrollX, (float) scrollY);
                this.mShadowShader.setLocalMatrix(this.mShadowMatrix);
                this.mShadowPaint.setShader(this.mShadowShader);
                canvas.drawRect((float) scrollX, ((float) scrollY) - max, (float) right, (float) scrollY, this.mShadowPaint);
            } else if (i == 0) {
                scrollY = view.getScrollY();
                this.mShadowMatrix.setScale(1.0f, max);
                this.mShadowMatrix.postTranslate((float) scrollX, (float) scrollY);
                this.mShadowShader.setLocalMatrix(this.mShadowMatrix);
                this.mShadowPaint.setShader(this.mShadowShader);
                canvas.drawRect((float) scrollX, (float) scrollY, (float) right, ((float) scrollY) + max, this.mShadowPaint);
            }
        }
    }
}
