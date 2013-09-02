package net.solutinno.tutorial.cardflip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int DURATION = 400;

    View mFrontView;
    View mBackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrontView = findViewById(R.id.front);
        mFrontView.setOnClickListener(this);

        mBackView = findViewById(R.id.back);
        mBackView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.front) flip(mFrontView, mBackView, DURATION);
        else if (id == R.id.back) flip(mBackView, mFrontView, DURATION);
    }

    public void flip(final View front, final View back, final int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            front.animate().rotationY(90).setDuration(duration / 2).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    front.setVisibility(View.GONE);
                    back.setRotationY(-90);
                    back.setVisibility(View.VISIBLE);
                    back.animate().rotationY(0).setDuration(duration / 2).setListener(null);
                }
            });
        }
        else {
            front.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
        }
    }

}

