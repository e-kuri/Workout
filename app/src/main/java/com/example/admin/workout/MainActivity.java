package com.example.admin.workout;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    private View fragmentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContainer = findViewById(R.id.fragment_container);
    }

    @Override
    public void itemClicked(long id) {

        if(fragmentContainer != null){
            WorkoutDetailFragment frag = new WorkoutDetailFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            frag.setWorkoutId(id);
            transaction.replace(R.id.fragment_container, frag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);
            transaction.commit();
        }else{
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id);
            startActivity(i);
        }


    }
}
