package com.example.lab1_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class VRActivity extends AppCompatActivity {

    private TextView lifeCycle, activityName;
    private static final String TAG = VRActivity.class.getSimpleName();

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);

        lifeCycle = findViewById(R.id.tvLifeCycle);
        lifeCycle.setTextColor(getResources().getColor(R.color.colorAccent));
        activityName = findViewById(R.id.tvActivity);
        activityName.setText(TAG);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                lifeCycle.setText(allPreviousLifecycleCallbacks);
            }
        }

        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            lifeCycle.append(mLifecycleCallbacks.get(i) + "\n");
        }

        // COMPLETED (5) Clear mLifecycleCallbacks after iterating through it
        /*
         * Once we've appended each callback from the ArrayList to the TextView, we need to clean
         * the ArrayList so we don't get duplicate entries in the TextView.
         */
        mLifecycleCallbacks.clear();


        logAndAppend(getResources().getString(R.string.onCreate));
    }

    @Override
    public void onStart() {
        super.onStart();

        logAndAppend(getResources().getString(R.string.onStart));
    }

    /**
     * Called when the activity will start interacting with the user. At this point your activity
     * is at the top of the activity stack, with user input going to it.
     * <p>
     * Always followed by onPause().
     */
    @Override
    public void onResume() {
        super.onResume();

        logAndAppend(getResources().getString(R.string.onResume));
    }

    @Override
    public void onPause() {
        super.onPause();

        logAndAppend(getResources().getString(R.string.onPause));
    }

    @Override
    public void onStop() {
        super.onStop();

        mLifecycleCallbacks.add(0, getResources().getString(R.string.onStop));

        logAndAppend(getResources().getString(R.string.onStop));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(getResources().getString(R.string.onRestart));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, getResources().getString(R.string.onDestroy));

        logAndAppend(getResources().getString(R.string.onDestroy));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(getResources().getString(R.string.onSavedInstance));
        String lifecycleDisplayTextViewContents = lifeCycle.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);
    }

    /**
     * Logs to the console and appends the lifecycle method name to the TextView so that you can
     * view the series of method callbacks that are called both from the app and from within
     * Android Studio's Logcat.
     *
     * @param lifecycleEvent The name of the event to be logged.
     */
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        lifeCycle.append(lifecycleEvent + "\n");
    }

}

