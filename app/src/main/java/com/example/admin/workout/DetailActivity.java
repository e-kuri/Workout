package com.example.admin.workout;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by admin on 7/11/2016.
 */
public class DetailActivity extends Activity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment workoutDetailFragment = ((WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag));
        workoutDetailFragment.setWorkoutId(((long) getIntent().getExtras().get(EXTRA_WORKOUT_ID)));
    }
}
