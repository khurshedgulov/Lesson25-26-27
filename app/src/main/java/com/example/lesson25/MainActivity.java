package com.example.lesson25;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button rotateX;
    Button rotateY;
    Button rotateLeft;
    Button rotateRight;
    Button translateX;
    Button translateY;
    Button fadeIn;
    Button fadeOut;
    Button zoomIn;
    Button zoomOut;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);

        rotateX = findViewById(R.id.rotateX);
        rotateY = findViewById(R.id.rotateY);
        rotateLeft = findViewById(R.id.rotateLeft);
        rotateRight = findViewById(R.id.rotateRight);
        translateX = findViewById(R.id.translateX);
        translateY = findViewById(R.id.translateY);
        fadeIn = findViewById(R.id.fadeIn);
        fadeOut = findViewById(R.id.fadeOut);
        zoomIn = findViewById(R.id.zoomIn);
        zoomOut = findViewById(R.id.zoomOut);


    }

    public void animatorSet(View view)
    {
        ValueAnimator rotateLeft = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() - 45);
        rotateLeft.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setRotation((float) animation.getAnimatedValue());
            }
        });
//        rotateLeft.start();

        ValueAnimator fi = ValueAnimator.ofFloat(0, 1);
        fi.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setAlpha((float) animation.getAnimatedValue());
            }
        });
//        fi.start();

        ValueAnimator zi = ValueAnimator.ofFloat(1, 3);
//                zi.setInterpolator(new CycleInterpolator(3));
        zi.setDuration(3000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setScaleX((float) animation.getAnimatedValue());
                image.setScaleY((float) animation.getAnimatedValue());
            }
        });
//        zi.start();

        ValueAnimator rY = ValueAnimator.ofInt(0, 360);
        rY.setRepeatMode(ValueAnimator.RESTART);
        rY.setRepeatCount(5);
        rY.setDuration(1000);
        rY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setRotationY((int) animation.getAnimatedValue());
            }
        });
//        rY.start();

        AnimatorSet animations = new AnimatorSet();
        animations.play(fi).with(zi);
        animations.play(zi).with(rotateLeft);
        animations.play(rY).after(3000);
        animations.start();
    }

    public void animateOnClick(View view) {
        switch (view.getId()) {
            case R.id.rotateX:
                image.animate().rotationX(360).setDuration(1000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        image.setRotationX(0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).setInterpolator(new BounceInterpolator()).start();
                break;
            case R.id.rotateY:
                ValueAnimator va = ValueAnimator.ofInt(0, 360);
                va.setRepeatMode(ValueAnimator.RESTART);
                va.setRepeatCount(5);
                va.setDuration(1000);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotationY((int) animation.getAnimatedValue());
                    }
                });
                va.start();
                break;
            case R.id.rotateLeft:
                ValueAnimator rotateLeft = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() - 45);
                rotateLeft.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotation((float) animation.getAnimatedValue());
                    }
                });
                rotateLeft.start();
                break;
            case R.id.rotateRight:
                ValueAnimator rotateRight = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() + 45);
                rotateRight.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotation((float) animation.getAnimatedValue());
                    }
                });
                rotateRight.start();
                break;
            case R.id.translateX:
                ValueAnimator tX = ValueAnimator.ofFloat(0, 200);
                tX.setRepeatMode(ValueAnimator.REVERSE);
                tX.setRepeatCount(1);
                tX.setInterpolator(new AnticipateInterpolator());
                tX.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setTranslationX((float) animation.getAnimatedValue());
                    }
                });
                tX.start();
                break;
            case R.id.translateY:
                ValueAnimator tY = ValueAnimator.ofFloat(0, 400);
//                tY.setRepeatMode(ValueAnimator.REVERSE);
//                tY.setRepeatCount(1);
                tY.setInterpolator(new BounceInterpolator());
                tY.setDuration(1000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setTranslationY((float) animation.getAnimatedValue());
                    }
                });
                tY.start();
                break;
            case R.id.fadeIn:
                ValueAnimator fi = ValueAnimator.ofFloat(0, 1);
                fi.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                fi.start();
                break;
            case R.id.fadeOut:
                ValueAnimator fo = ValueAnimator.ofFloat(1, 0);
                fo.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                fo.start();
                break;
            case R.id.zoomIn:
                ValueAnimator zi = ValueAnimator.ofFloat(1, 3);
//                zi.setInterpolator(new CycleInterpolator(3));
                zi.setDuration(3000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setScaleX((float) animation.getAnimatedValue());
                        image.setScaleY((float) animation.getAnimatedValue());
                    }
                });
                zi.start();
                break;
            case R.id.zoomOut:
                ValueAnimator zo = ValueAnimator.ofFloat(3, 1);
                zo.setDuration(300).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setScaleX((float) animation.getAnimatedValue());
                        image.setScaleY((float) animation.getAnimatedValue());
                    }
                });
                zo.start();
                break;
        }
    }
}
