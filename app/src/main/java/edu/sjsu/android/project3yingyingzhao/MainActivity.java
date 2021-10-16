package edu.sjsu.android.project3yingyingzhao;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private MyView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MyView(this);
        setContentView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.startSimulation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        view.stopSimulation();
    }
}