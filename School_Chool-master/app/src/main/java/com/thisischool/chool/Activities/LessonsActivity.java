package com.thisischool.chool.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.thisischool.chool.OurWork.Fragments.DiaryFragment;
import com.thisischool.chool.OurWork.Fragments.MyNotesFragment;
import com.thisischool.chool.R;

import java.util.ArrayList;
import java.util.List;
public class LessonsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

    }
}