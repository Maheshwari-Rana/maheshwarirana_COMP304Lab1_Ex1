package com.example.lab1_ex1;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    private TextView lifeCycles, activityName;
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";
    private String lifeCycle;

    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeCycle = getResources().getString(R.string.onCreate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);
        activityName = view.findViewById(R.id.tvActivity);
        activityName.setText(TAG);

        lifeCycles = view.findViewById(R.id.tvCycles);
        lifeCycles.setTextColor(getResources().getColor(R.color.colorAccent));

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                lifeCycles.setText(allPreviousLifecycleCallbacks);
            }
        }

        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            lifeCycles.append(mLifecycleCallbacks.get(i) + "\n");
        }

        // COMPLETED (5) Clear mLifecycleCallbacks after iterating through it
        /*
         * Once we've appended each callback from the ArrayList to the TextView, we need to clean
         * the ArrayList so we don't get duplicate entries in the TextView.
         */

        mLifecycleCallbacks.clear();
        logAndAppend(getResources().getString(R.string.onCreate));

        Toast.makeText(getActivity(), getResources().getString(R.string.onCreate), Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        logAndAppend(getResources().getString(R.string.onStart));
        Toast.makeText(getActivity(), getResources().getString(R.string.onStart), Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, getResources().getString(R.string.onDestroy));

        logAndAppend(getResources().getString(R.string.onDestroy));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(getResources().getString(R.string.onSavedInstance));
        String lifecycleDisplayTextViewContents = lifeCycle;
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

        lifeCycles.append(lifecycleEvent + "\n");
    }
}
