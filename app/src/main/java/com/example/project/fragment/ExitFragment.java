package com.example.project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExitFragment extends Fragment {

    public ExitFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exit, container, false);
        System.exit(1);
        return view;
    }
}
