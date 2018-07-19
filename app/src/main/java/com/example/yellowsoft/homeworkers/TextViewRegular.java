package com.example.yellowsoft.homeworkers;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by info on 16-07-2018.
 */

public class TextViewRegular extends TextView {

    public TextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        appearance();

    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextViewRegular(Context context) {
        super(context);
        init(context);
    }
    public void  appearance(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
//        this.setTextSize(getResources().getDimension(R.dimen.material_micro_text_size));
        this.setLineSpacing(-10,1);
//        this.setGravity(Gravity.CENTER_VERTICAL);
//        this.setTextColor(getResources().getColor(R.color.white));
//        this.setText("Not Started");
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            if (Session.GetLang(context).equals("en")) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/oswald.regular.ttf");
                setTypeface(tf);
            }else {
                Typeface tf_arabic = Typeface.createFromAsset(getContext().getAssets(), "fonts/oswald.regular.ttf");
                setTypeface(tf_arabic);
            }



        }

    }


}

