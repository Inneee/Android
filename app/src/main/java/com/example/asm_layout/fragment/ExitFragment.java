package com.example.asm_layout.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asm_layout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExitFragment extends Fragment {

    public ExitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exit, container, false);
        System.exit(1);
        return view;
    }
}
