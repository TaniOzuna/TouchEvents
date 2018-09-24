package com.example.touchevents.touchevents;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.util.DisplayMetrics;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class MainActivity extends Activity implements
        GestureDetector.OnContextClickListener,
        GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private GestureDetectorCompat mDetector;
    private static final String gestureTag = "Debug";
    private ConstraintLayout view;
    private TextView tvMessage;
    private Button btnRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this, this);

        mDetector.setOnDoubleTapListener(this);
        this.view = (ConstraintLayout) findViewById(R.id.home_view);
        this.view.setBackgroundColor(Color.BLUE);

    }

    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        TextView textView = findViewById(R.id.hello_world);
        textView.setTextSize(16);
        textView.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onContextClick(MotionEvent event) {
        Log.d(gestureTag, "onSingleTapConfirmed: ");
        this.view.setBackgroundColor(Color.WHITE);
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(gestureTag, "onSingleTapConfirmed");
        this.view.setBackgroundColor(Color.YELLOW);
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(gestureTag, "onDoubleTap: ");
        this.view.setBackgroundColor(Color.RED);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(gestureTag, "onDoubleTapEvent: ");
        this.view.setBackgroundColor(Color.BLACK);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(gestureTag, "onDown: ");
        this.view.setBackgroundColor(Color.MAGENTA);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(gestureTag, "onShowPress: ");
        this.view.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(gestureTag, "onSingleTapUp: ");
        this.view.setBackgroundColor(Color.CYAN);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        this.view.setBackgroundColor(Color.BLUE);
        Log.d(gestureTag, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(gestureTag, "onLongPress: ");
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(gestureTag, "onFling: " + event1.toString() + event2.toString());
        this.view.setBackgroundColor(Color.CYAN);
        return true;
    }


    public void onRepeatAnimation(View v) {
        // Setup scale Y
        ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(btnRepeat, "scaleY", 1.0f, 1.5f);
        scaleAnimY.setDuration(9000);
        scaleAnimY.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimY.setRepeatMode(ValueAnimator.REVERSE);
        // Setup scale X
        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(btnRepeat, "scaleX", 1.0f, 1.5f);
        scaleAnimX.setDuration(9000);
        scaleAnimX.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimX.setRepeatMode(ValueAnimator.REVERSE);
        // Setup animation set
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleAnimX, scaleAnimY);
        set.start();
    }

    // Slide message from button up to display, then later slide out
    public void onSlideMessage(View v) {
        tvMessage.setTranslationY(tvMessage.getHeight());
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.animate().translationY(0).setDuration(9000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tvMessage.animate().translationY(tvMessage.getHeight()).setDuration(9000).setStartDelay(9000);
            }
        });
    }
}



