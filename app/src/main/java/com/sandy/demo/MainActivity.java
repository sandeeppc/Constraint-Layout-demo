package com.sandy.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean lessMode = true;
    private boolean lastMode = true;

    private ConstraintSet moreConstraintSet = new ConstraintSet();
    private ConstraintSet lessConstraintSet = new ConstraintSet();
    private ConstraintSet lastConstraintSet = new ConstraintSet();
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        if (getIntent() != null && getIntent().getData() != null) {
            Toast.makeText(this, "OnCreate: " + getIntent().getData().toString(), Toast.LENGTH_SHORT).show();
        }
        constraintLayout = findViewById(R.id.main);
        final ImageView ivButton = findViewById(R.id.ivButton);
        lessConstraintSet.clone(this, R.layout.activity_normal);
        moreConstraintSet.clone(this, R.layout.activity_main);
        lastConstraintSet.clone(this, R.layout.activity_last);
        ivButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TransitionManager.beginDelayedTransition(constraintLayout);
                if (lessMode) {
                    moreConstraintSet.applyTo(constraintLayout);
                    lessMode = false;
                } else {
                    lessConstraintSet.applyTo(constraintLayout);
                    lessMode = true;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        TransitionManager.beginDelayedTransition(constraintLayout);
        if (lastMode) {
            lastConstraintSet.applyTo(constraintLayout);
            lastMode = false;
        } else {
            moreConstraintSet.applyTo(constraintLayout);
            lastMode = true;
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Uri uri = intent.getData();
        if (uri != null) {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
