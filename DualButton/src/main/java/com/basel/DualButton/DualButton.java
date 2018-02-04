package com.basel.DualButton;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class DualButton extends RelativeLayout {

    private Button button1,button2;
    OnDualClickListener cListener;
    private int duration = 500;
    private int dualType,dualDirection = 0;
    private boolean secondClickable = true;
    ValueAnimator dualFlipAnim;
    boolean isDualing;
    boolean secondReveal,secondFlip;
    int x,y;

    public DualButton(Context context) {
        super(context);
        initAttributes(context, null);
    }

    public DualButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
    }

    public DualButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        button1 = new Button(context);
        button1.setLayoutParams(params);
        addView(button1);
        button2 = new Button(context);
        button2.setLayoutParams(params);
        addView(button2);

        button2.setVisibility(INVISIBLE);
        button1.setGravity(Gravity.CENTER);
        button2.setGravity(Gravity.CENTER);
        button1.setTransformationMethod(null);
        button2.setTransformationMethod(null);

        TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.DualButton);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr==R.styleable.DualButton_textFirst){
                setTextFirst(a.getString(attr));
            } else if (attr==R.styleable.DualButton_backgroundColorFirst){
                int color = a.getColor(attr, 0);
                if(color!=0){
                    setBackgroundColorFirst(color);
                }
            } else if (attr==R.styleable.DualButton_backgroundResFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    setBackgroundResFirst(intResId);
                }
            } else if (attr==R.styleable.DualButton_textSizeFirst){
                setTextSizeFirst(a.getDimensionPixelSize(attr, 12));
            } else if (attr==R.styleable.DualButton_textColorFirst){
                setTextColorFirst(a.getColor(attr, Color.BLACK));
            }else if (attr==R.styleable.DualButton_fontStyleFirst){
                setFontStyleFirst(a.getInt(attr, -1));
            } else if (attr==R.styleable.DualButton_gravityFirst){
                setGravityFirst(a.getInt(attr, -1));
            } else if (attr==R.styleable.DualButton_drawableRightFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableRightFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableStartFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = a.getDrawable(attr);
                    setDrawableStartFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableLeftFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableLeftFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableEndFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableEndFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableTopFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableTopFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableBottomFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableBottomFirst(d);
                }
            } else if (attr==R.styleable.DualButton_drawableBottomFirst){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableBottomFirst(d);
                }
            }  else if (attr==R.styleable.DualButton_drawablePaddingFirst){
                setDrawablePaddingFirst(a.getInt(attr, 0));
            } else if (attr==R.styleable.DualButton_textSecond){
                setTextSecond(a.getString(attr));
            } else if (attr==R.styleable.DualButton_backgroundColorSecond){
                int color = a.getColor(attr, 0);
                if(color!=0){
                    setBackgroundColorSecond(color);
                }
            } else if (attr==R.styleable.DualButton_backgroundResSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    setBackgroundResSecond(intResId);
                }
            } else if (attr==R.styleable.DualButton_textSizeSecond){
                setTextSizeSecond(a.getDimensionPixelSize(attr, 12));
            } else if (attr==R.styleable.DualButton_textColorSecond){
                setTextColorSecond(a.getColor(attr, Color.BLACK));
            }else if (attr==R.styleable.DualButton_fontStyleSecond){
                setFontStyleSecond(a.getInt(attr, -1));
            } else if (attr==R.styleable.DualButton_gravitySecond){
                setGravitySecond(a.getInt(attr, -1));
            } else if (attr==R.styleable.DualButton_drawableRightSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableRightSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableStartSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = context.getResources().getDrawable(intResId);
                    setDrawableStartSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableLeftSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = a.getDrawable(attr);
                    setDrawableLeftSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableEndSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableEndSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableTopSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableTopSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableBottomSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableBottomSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawableBottomSecond){
                int intResId = a.getResourceId(attr, 0);
                if(intResId!=0){
                    Drawable d = ContextCompat.getDrawable(context,intResId);
                    setDrawableBottomSecond(d);
                }
            } else if (attr==R.styleable.DualButton_drawablePaddingSecond){
                setDrawablePaddingSecond(a.getInt(attr, 0));
            } else if (attr==R.styleable.DualButton_clickableSecond){
                secondClickable = a.getBoolean(attr, false);
            } else if (attr==R.styleable.DualButton_duration){
                duration = a.getInt(attr, 500);
            } else if (attr==R.styleable.DualButton_dualDirection){
                dualDirection = a.getInt(attr, 0);
            } else if (attr==R.styleable.DualButton_dualType){
                dualType = a.getInt(attr, 0);
                if(dualType==1){
                    dualFlipAnim = ValueAnimator.ofFloat(0f, 1f);
                }
            }
        }

        a.recycle();
    }

    public void setTextFirst(String text) {
        button1.setText(text);
    }

    public void setBackgroundColorFirst(int color) {
        button1.setBackgroundColor(color);
    }

    public void setBackgroundResFirst(int res) {
        button1.setBackgroundResource(res);
    }

    public void setTextSizeFirst(int size) {
        button1.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTextColorFirst(int color) {
        button1.setTextColor(color);
    }

    public void setFontStyleFirst(int fs) {
        button1.setTypeface(button1.getTypeface(), fs);
    }

    public void setGravityFirst(int gravity) {
        button1.setGravity(gravity);
    }

    public void setDrawableRightFirst(Drawable d) {
        setCompoundDrawables(d,button1,2);
    }

    public void setDrawableLeftFirst(Drawable d) {
        setCompoundDrawables(d,button1,0);
    }

    public void setDrawableStartFirst(Drawable d) {
        setCompoundDrawablesRelative(d,button1,0);
    }

    public void setDrawableEndFirst(Drawable d) {
        setCompoundDrawablesRelative(d,button1,2);
    }

    public void setDrawableTopFirst(Drawable d) {
        setCompoundDrawables(d,button1,1);
    }

    public void setDrawableBottomFirst(Drawable d) {
        setCompoundDrawables(d,button1,3);
    }

    public void setDrawablePaddingFirst(int padding) {
        button1.setCompoundDrawablePadding(padding);
    }


    public void setTextSecond(String text) {
        button2.setText(text);
    }

    public void setBackgroundColorSecond(int color) {
        button2.setBackgroundColor(color);
    }

    public void setBackgroundResSecond(int res) {
        button2.setBackgroundResource(res);
    }

    public void setTextSizeSecond(int size) {
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void setTextColorSecond(int color) {
        button2.setTextColor(color);
    }

    public void setFontStyleSecond(int fs) {
        button2.setTypeface(button2.getTypeface(), fs);
    }

    public void setGravitySecond(int gravity) {
        button2.setGravity(gravity);
    }

    public void setDrawableRightSecond(Drawable d) {
        setCompoundDrawables(d,button2,2);
    }

    public void setDrawableLeftSecond(Drawable d) {
        setCompoundDrawables(d,button2,0);
    }

    public void setDrawableStartSecond(Drawable d) {
        setCompoundDrawablesRelative(d,button2,0);
    }

    public void setDrawableEndSecond(Drawable d) {
        setCompoundDrawablesRelative(d,button2,2);
    }

    public void setDrawableTopSecond(Drawable d) {
        setCompoundDrawables(d,button2,1);
    }

    public void setDrawableBottomSecond(Drawable d) {
        setCompoundDrawables(d,button2,3);
    }


    public void setCompoundDrawables(Drawable d,Button btn, int side) {
        int h = d.getIntrinsicHeight();
        int w = d.getIntrinsicWidth();
        d.setBounds(0, 0, w, h);
        btn.setCompoundDrawables(
                side==0?d:btn.getCompoundDrawables()[0],
                side==1?d:btn.getCompoundDrawables()[1],
                side==2?d:btn.getCompoundDrawables()[2],
                side==3?d:btn.getCompoundDrawables()[3]
        );
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setCompoundDrawablesRelative(Drawable d,Button btn, int side) {
        int h = d.getIntrinsicHeight();
        int w = d.getIntrinsicWidth();
        d.setBounds(0, 0, w, h);
        btn.setCompoundDrawablesRelative(
                side==0?d:btn.getCompoundDrawablesRelative()[0],
                side==1?d:btn.getCompoundDrawablesRelative()[1],
                side==2?d:btn.getCompoundDrawablesRelative()[2],
                side==3?d:btn.getCompoundDrawablesRelative()[3]
        );
    }

    public void setDrawablePaddingSecond(int padding) {
        button2.setCompoundDrawablePadding(padding);
    }

    public Button getFirstButton() {
        return button1;
    }

    public Button getSecondButton() {
        return button2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            x = (int)event.getX();
            y = (int)event.getY();
            onClicked();
        }
        return super.dispatchTouchEvent(event);
    }


    public void onClicked() {
        if(!isDualing) {
            if(secondReveal||secondFlip){
                if(secondClickable) dualIt(button2,button1);
            }else{
                dualIt(button1,button2);
            }
        }
    }

    public void dualIt(Button from,Button to) {
        switch (dualType){
            case 0:
                DualReveal(from,to);
                break;
                //case you have any idea : ?
        }
    }

    public void DualReveal(final Button from,final Button to) {
        if(dualDirection!=0) {
                x = dualDirection == 1 ? (int) from.getX() + getWidth() : (int) from.getX();
                y = dualDirection == 1 ? (int) from.getY() + getHeight() : (int) from.getY();
            if (from == button2) {
                x = dualDirection == 1 ? (int) from.getX() : (int) from.getX() + getWidth();
                y = dualDirection == 1 ? (int) from.getY() : (int) from.getY() + getHeight();
            }
        }
        int startRadius = 0;
        int endRadius = (int) Math.hypot(from.getWidth(), from.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(to, x, y, startRadius, endRadius);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(duration);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    to.setVisibility(View.VISIBLE);
                    to.bringToFront();
                    isDualing = true;
                    if(to==button2){
                        secondReveal = true;
                        if(cListener!=null)cListener.onClickFirst(button1);
                    }else{
                        secondReveal = false;
                        if(cListener!=null)cListener.onClickSecond(button2);
                    }
                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    from.setVisibility(View.INVISIBLE);
                    to.setVisibility(View.VISIBLE);
                    isDualing = false;
                }
                @Override
                public void onAnimationCancel(Animator animator) {
                }
                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            anim.start();
        } else {
            to.setVisibility(View.VISIBLE);
        }
    }


    public interface OnDualClickListener {
        void onClickFirst(Button btn);
        void onClickSecond(Button btn);
    }

    public void setDualClickListener(OnDualClickListener clickListener) {
        cListener = clickListener;
    }

}
