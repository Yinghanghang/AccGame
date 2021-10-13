package edu.sjsu.android.project3yanchen;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    // TODO: add a MyView attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: construct the MyView object
        // TODO: and set content view to MyView instead of the layout file.
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: use the startSimulation method in MyView to register the listener
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO:  use the stopSimulation method in MyView to unregister the listener
    }
}