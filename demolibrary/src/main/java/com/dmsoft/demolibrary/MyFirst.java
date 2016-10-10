package com.dmsoft.demolibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Dushyant on 10/10/2016.
 */
public class MyFirst extends View {
    int color;
    float radius;
    float width, height;
    Paint paint;
    int resId;
    Bitmap bitmap;
    RectF rectf;
    Path path;


    public MyFirst(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MyFirst(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyFirst);
        try {
            color = array.getColor(R.styleable.MyFirst_background_color, Color.GREEN);
            radius = array.getFloat(R.styleable.MyFirst_radius, 50);
            resId = array.getResourceId(R.styleable.MyFirst_src, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        rectf = new RectF();
        path = new Path();
        if (resId != 0) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rectf.set(0, 0, radius, radius);
        path.arcTo(rectf,0,360);
        canvas.clipPath(path);
        if(resId!=0)
        canvas.drawBitmap(bitmap,null,rectf,paint);
    }
}

