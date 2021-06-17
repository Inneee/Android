package com.example.project.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.Activity.GiaoDichActivity;
import com.example.project.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeThuFragment extends Fragment {
    TextView btnBD, btnKT, btnTinh;
    TextView tong;
    EditText ngayBD, ngayKT;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    GiaoDichActivity gdDAO;
    public ThongKeThuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke_thu, container, false);
        btnBD = view.findViewById(R.id.ngayBD);
        btnKT = view.findViewById(R.id.ngayKT);
        btnTinh = view.findViewById(R.id.btnTinh);
        tong = view.findViewById(R.id.tong);
        ngayBD = view.findViewById(R.id.txtNgayBD);
        ngayKT = view.findViewById(R.id.txtNgayKT);
        gdDAO = new GiaoDichActivity(getActivity());
        btnBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int myear = c.get(Calendar.YEAR);
                int mmonth = c.get(Calendar.MONTH);
                int mday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        if (month<9 && dayOfMonth<9){
                            ngayBD.setText(year + "-" + "0" + (month+1) + "-" + "0" + dayOfMonth);
                        }else if(dayOfMonth<9){
                            ngayBD.setText(year + "-"  + (month+1) + "-" + "0" + dayOfMonth);
                        }
                        else if (month<9){
                            ngayBD.setText(year + "-" + "0"  + (month+1) + "-" + dayOfMonth);
                        }else {
                            ngayBD.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, myear, mmonth, mday);
                datePickerDialog.show();
            }
        });
        btnKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int myear = c.get(Calendar.YEAR);
                int mmonth = c.get(Calendar.MONTH);
                int mday = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        if (month<9 && dayOfMonth<9){
                            ngayKT.setText(year + "-" + "0" + (month+1) + "-" + "0" + dayOfMonth);
                        }else if(dayOfMonth<9){
                            ngayKT.setText(year + "-"  + (month+1) + "-" + "0" + dayOfMonth);
                        }
                        else if (month<9){
                            ngayKT.setText(year + "-" + "0"  + (month+1) + "-" + dayOfMonth);
                        }else {
                            ngayKT.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }, myear, mmonth, mday);
                datePickerDialog.show();
            }
        });
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String batdau = ngayBD.getText().toString();
                    String ketthuc = ngayKT.getText().toString();
                    ArrayList<Double> list = gdDAO.thongKeTongThu(batdau,ketthuc);
                    tong.setText(String.valueOf(Sum(list))+" VNĐ");
                } catch (Exception e) {
                    Snackbar.make(view.findViewById(R.id.contkt),"Lổi định dạng ngày!!",2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    }).show();
                }
            }
        });
        return view;
    }
    private int Sum(ArrayList<Double> list){
        int sum = 0;
        for(double num: list){
            sum+=num;
        }
        return sum;
    }
}
