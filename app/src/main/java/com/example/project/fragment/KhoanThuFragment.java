package com.example.project.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activity.GiaoDichActivity;
import com.example.project.Activity.KhoanActivity;
import com.example.project.R;
import com.example.project.adapter.KhoanThuAdapter;
import com.example.project.adapter.SpinnerAdapter;
import com.example.project.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhoanThuFragment extends Fragment {
    List<GiaoDich> list;
    RecyclerView recyclerView;
    FloatingActionButton fb;
    GiaoDichActivity gdDAO;
    KhoanThuAdapter khoanThuAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public KhoanThuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoan_thu, container, false);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.lvKhoanThu);
        fb = view.findViewById(R.id.btnAddKhoanThu);
        list = new ArrayList<>();
        gdDAO = new GiaoDichActivity(getActivity());
        list = gdDAO.getAllThu();
        khoanThuAdapter = new KhoanThuAdapter(list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(khoanThuAdapter);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View n) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.add_khoan_alertdialog, null);
                Spinner loaiGD = view1.findViewById(R.id.spnadd);
                TextView ngayGD = view1.findViewById(R.id.edt_ngayadd);
                EditText motaGD = view1.findViewById(R.id.edt_noiDungadd);
                EditText tienGD = view1.findViewById(R.id.edt_tienadd);
                ImageView btnDate = view1.findViewById(R.id.btnDate);
                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getActivity(),new KhoanActivity(getActivity()).getLoaiKhoan("1"));
                loaiGD.setAdapter(spinnerAdapter);
                String date = GetDay();
                ngayGD.setText(date);
                btnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        int myear = c.get(Calendar.YEAR);
                        int mmonth = c.get(Calendar.MONTH);
                        int mday = c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                if (month<9){
                                    ngayGD.setText(year + "-" + "0" + (month+1) + "-" + dayOfMonth);
                                }else {
                                    ngayGD.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                }
                            }
                        }, myear, mmonth, mday);
                        datePickerDialog.show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            int id = loaiGD.getSelectedItemPosition();
                            String idLoai = new KhoanActivity(getActivity()).getLoaiKhoan("1").get(id).maKhoan;
                            Double tienNew = Double.parseDouble(tienGD.getText().toString());
                            Date soNgay = sdf.parse(ngayGD.getText().toString());
                            String mota = motaGD.getText().toString();
                            gdDAO.insert(new GiaoDich(soNgay, mota, tienNew, idLoai));
                            khoanThuAdapter.addData(gdDAO.getAllThu());
                            recyclerView.scrollToPosition(gdDAO.getAllThu().size()-1);
                            recyclerView.setAdapter(khoanThuAdapter);
                        }catch (NumberFormatException e) {
                            Snackbar.make(view.findViewById(R.id.conss1),"Ti???n kh??ng h???p l???!",2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                        } catch (RuntimeException e) {
                            Toast.makeText(getActivity(), "Ch??a c?? lo???i", Toast.LENGTH_SHORT).show();
                        }catch (Exception ex){
                            Snackbar.make(view.findViewById(R.id.conss1),"Ng??y kh??ng h???p l???!",2000).setActionTextColor(Color.RED).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }

        });

        return view;
    }
    private String GetDay(){
        long time = System.currentTimeMillis();
        String day = sdf.format(time);
        return day;
    }
}
