package com.healthy.food.helper.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.healthy.food.helper.R;


/**
 * @author bsl
 * @package com.healthy.food.helper
 * @filename TitleLayout
 * @date 18-4-24
 * @email don't tell you
 * @describe TODO
 */

public class TitleLayout extends LinearLayout {

    private static final String TAG = "TitleLayout";

    private ImageView backImg;

    private TextView titleTxt;

    private TextView buttonTxt;


    private Context context;

    private AttributeSet attrs;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context    = context;
        this.attrs      = attrs;

        initWidgets();
    }

    // init title layout
    private void initWidgets() {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_title, this);

        backImg     = (ImageView) (layout.findViewById(R.id.title_back));
        titleTxt    = (TextView) (layout.findViewById(R.id.title_title));
        buttonTxt   = (TextView) (layout.findViewById(R.id.title_btn));

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.com_healthy_food_helper_layout_TitleLayout);
        String title    = typedArray.getString(R.styleable.com_healthy_food_helper_layout_TitleLayout_title_text);
        String button   = typedArray.getString(R.styleable.com_healthy_food_helper_layout_TitleLayout_button_text);

        setTitle(title);
        setButton(button);

        backImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
            }
        });
    }

    // the setter for title text
    public void  setTitle(String title) {
        titleTxt.setText(title);
    }

    // the setter for button text
    public void setButton(String button){
        buttonTxt.setText(button);
    }

    // set onclick listener for button widget
    public void setBtnListener(OnClickListener listener) {
        if(listener != null) {
            buttonTxt.setOnClickListener(listener);
        }
    }

}
