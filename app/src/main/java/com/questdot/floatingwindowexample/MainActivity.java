package com.questdot.floatingwindowexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button btnFloating;
    RelativeLayout relativeLayout;
    PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFloating = (Button)findViewById(R.id.btnFloating);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                 popupView = layoutInflater.inflate(R.layout.popup,null);

                popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                Button btnClose = (Button)popupView.findViewById(R.id.btnClose);

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });


                 mCurrentX = 20;
                 mCurrentY = 50;

                        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, mCurrentX, mCurrentY);
                 


                popupView.setOnTouchListener(new View.OnTouchListener() {
                    private float mDx;
                    private float mDy;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();
                        if (action == MotionEvent.ACTION_DOWN) {
                            mDx = mCurrentX - event.getRawX();
                            mDy = mCurrentY - event.getRawY();
                        } else
                        if (action == MotionEvent.ACTION_MOVE) {
                            mCurrentX = (int) (event.getRawX() + mDx);
                            mCurrentY = (int) (event.getRawY() + mDy);
                            popupWindow.update(mCurrentX, mCurrentY, -1, -1);
                        }
                        return true;
                    }
                });

            }
        });
    }
}
