package sampleapplication.example.com.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    RelativeLayout arcButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width=dm.widthPixels;
        float height=dm.heightPixels;

        final int arcRadius = (int)(width/1.2);

        final float xCoor = width / 2;
        final float yCoor = height;
        final int color = Color.rgb(140, 0, 115);
        setContentView(new RelativeLayout(this) {
                           private int value = 78;
                           private TextView textView;
            {
                addView(new ArcView(getContext(), arcRadius, xCoor, yCoor, color) {

                    @Override
                    protected void onRotate(int offset) {
                        Log.d("TAGGOW", "onRotate: " + String.valueOf(value += offset));
                        if(!(value <= 50 || value >= 100)) {
                            textView.setText(String.valueOf(value += offset));
                                Log.d("TAGGOW", "onTouch: " + " 50 to 100");
                                int MAX_VALUE = 255;
                                int MULTIPLIER = 5;

                                int red;
                                int blue;

                                int value2 = value - 50;
                                red = MULTIPLIER * value2;
                                blue = MAX_VALUE - red;

                                int color = Color.rgb(red, 0, blue);
                                paint.setColor(color);
                                this.color = color;
                                invalidate();

                        }
                    }
                }, new RelativeLayout.LayoutParams(0,0){
                    {
                        width = MATCH_PARENT;
                        height = MATCH_PARENT;
                        addRule(RelativeLayout.ALIGN_BOTTOM);
                    }
                });
                addView(textView = new android.support.v7.widget.AppCompatTextView(getContext()){
                    {
                        setText(Integer.toString(value));
                        setVisibility(View.VISIBLE);
                        setTextColor(Color.BLACK);
                        setTextSize(30);
                    }
                }, new RelativeLayout.LayoutParams(0,0){
                    {
                        width = WRAP_CONTENT;
                        height = WRAP_CONTENT;
                        topMargin = 100;
                        leftMargin = 450;
                        addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    }
                });
            }
        });
        super.onCreate(savedInstanceState);

//        arcButton = (RelativeLayout) findViewById(R.id.arcButton);



//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
//
//        arcButton.setLayoutParams(params);
//        View arcView = new ArcView(this, arcRadius, xCoor, yCoor, Color.BLUE);
//        arcButton.addView(arcView);
    }
}
