package sampleapplication.example.com.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

public abstract class ArcView extends View implements View.OnTouchListener{
    private float centerX;
    private float centerY;
    private float radius;
    public int color;
    private float stepAngle;
    private float startAngle;
    private boolean isDragging;
    Paint paint = new Paint();

    public ArcView(Context context, float radius, float x, float y, int color) {
        super(context);
        this.centerX = x;
        this.centerY= y;
        this.radius = radius;
        this.color = color;
        setOnTouchListener(this);

        /*setOnTouchListener(new OnTouchListener() {
            private float startAngle;
            private boolean isDragging;


            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Log.d("TAGGOW", "onTouch: " + "touched");
                stepAngle = 1;
                float touchX = event.getX();
                float touchY = event.getY();
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        startAngle = touchAngle(touchX, touchY);
//                        isDragging = isInDiscArea(touchX, touchY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                            float touchAngle = touchAngle(touchX, touchY);
                            float deltaAngle = (360 + touchAngle - startAngle + 180) % 360 - 180;
//                        if (isDragging) {
//                            if (Math.abs(deltaAngle) > stepAngle) {
                                int offset = (int) deltaAngle / (int) stepAngle;
                                startAngle = touchAngle;
                                onRotate(offset);
                                int value = 0;
                                Log.d("TAGGOW", "onTouch: " + String.valueOf(value += offset));
//                            }
//                        }
                        break;
                }
                return false;
            }
        });*/
    }

   /* private boolean isInDiscArea(float touchX, float touchY) {
        float dX2 = (float) Math.pow(centerX - touchX, 2);
        float dY2 = (float) Math.pow(centerY - touchY, 2);
        float distToCenter = (float) Math.sqrt(dX2 + dY2);
        float baseDist = Math.min(centerX, centerY);
        float minDistToCenter = radius * baseDist;
        float maxDistToCenter = radius * baseDist;
        return distToCenter >= minDistToCenter && distToCenter <= maxDistToCenter;
    }*/

    /*public void setStepAngle(float angle) {
        stepAngle = Math.abs(angle % 180);
    }*/

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
//        float radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2f;

        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setXfermode(null);
        canvas.drawCircle(centerX, centerY, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        super.onDraw(canvas);
    }

    private float touchAngle(float touchX, float touchY) {
            float dX = touchX - centerX;
            float dY = centerY - touchY;
            return (float) (270 - Math.toDegrees(Math.atan2(dY, dX))) % 360 - 180;
    }

    protected abstract void onRotate(int offset);

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        stepAngle = 1;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                startAngle = touchAngle(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                float touchAngle = touchAngle(touchX, touchY);
                float deltaAngle = (360 + touchAngle - startAngle + 180) % 360 - 180;
                    if (Math.abs(deltaAngle) > stepAngle) {
                        int offset = (int) deltaAngle / (int) stepAngle;
                        startAngle = touchAngle;
                        onRotate(offset);
//                        int valuein = 78;
//                        int offsetValue = valuein += offset;
                       /* if((offsetValue >= 50 || offsetValue <= 100)) {
                            Log.d("TAGGOW", "onTouch: " + " 50 to 100");
                            int MAX_VALUE = 255;
                            int MULTIPLIER = 5;

                            int red;
                            int blue;

                            int value = offset - 50;
                            Log.d("TAGGOW", "value: " + value);

                            red = MULTIPLIER * value;
                            blue = MAX_VALUE - red;

                            int color = Color.rgb(red, 0, blue);
//                            LinearGradient linearGradient = new LinearGradient(
//                                    radius, 0, radius, radius, color, color, Shader.TileMode.CLAMP);
//                            paint.setShader(linearGradient);
                            paint.setColor(color);
                            this.color = color;
                            invalidate();
                        }*/
//                        int value = 78;
//                        Log.d("TAGGOW", "onTouch: " + String.valueOf(value += offset));
                    }
//                }
                break;
        }
        return true;
    }

}
