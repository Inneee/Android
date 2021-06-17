package com.example.project.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.database.DbHelper;
import com.example.project.model.GiaoDich;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GiaoDichActivity {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public GiaoDichActivity(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public long insert(GiaoDich gd){
        ContentValues values = new ContentValues();
        values.put("ngayGD", sdf.format(gd.ngayGD));
        values.put("motaGD", gd.motaGD);
        values.put("tienGD", gd.tienGD);
        values.put("maKhoan", gd.maKhoan);
        return db.insert("GIAODICH", null,values);
    }
    public long update(GiaoDich gd){
        ContentValues values = new ContentValues();
        values.put("maGD", gd.maGD);
        values.put("ngayGD", sdf.format(gd.ngayGD));
        values.put("motaGD", gd.motaGD);
        values.put("tienGD", gd.tienGD);
        values.put("maKhoan", gd.maKhoan);
        return db.update("GIAODICH",values,"maGD=?",new String[]{gd.maGD});
    }
    public long delete(String maGD){
        return db.delete("GIAODICH", "maGD=?",new String[]{maGD});
    }
    public List<GiaoDich> getGD(String sql, String...a){
        List<GiaoDich> list = new ArrayList<GiaoDich>();
        Cursor c = db.rawQuery(sql,a);
        while (c.moveToNext()){
            try {
                GiaoDich gd = new GiaoDich();
                gd.maGD = c.getString(c.getColumnIndex("maGD"));
                gd.ngayGD = sdf.parse(c.getString(c.getColumnIndex("ngayGD")));
                gd.motaGD = c.getString(c.getColumnIndex("motaGD"));
                gd.tienGD = Double.parseDouble(c.getString(c.getColumnIndex("tienGD")));
                gd.maKhoan = c.getString(c.getColumnIndex("maKhoan"));
                list.add(gd);
            }catch (Exception e){

            }
        }
        return list;
    }
    //get All
    public List<GiaoDich> getAll(){
        String sql = "select * from GIAODICH";
        return getGD(sql);
    }

    public List<GiaoDich> getAllChi(){
        String sql = "SELECT * FROM GIAODICH INNER JOIN KHOAN ON GIAODICH.maKhoan = KHOAN.maKhoan WHERE KHOAN.loaiKhoan ='0'";
        return getGD(sql);
    }



    public List<GiaoDich> getAllThu(){
        String sql = "SELECT * FROM GIAODICH INNER JOIN KHOAN ON GIAODICH.maKhoan = KHOAN.maKhoan WHERE KHOAN.loaiKhoan ='1'";
        return getGD(sql);
    }

    public String getTenKhoan(String i){
        String name = null;
        String sql = "SELECT tenKhoan FROM GIAODICH INNER JOIN KHOAN ON GIAODICH.maKhoan = KHOAN.maKhoan WHERE GIAODICH.makhoan='"+i+"'";
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()){
            name = c.getString(c.getColumnIndex("tenKhoan"));
        }
        return name;
    }
    public GiaoDich getMaGD(String maGD){
        String sql = "select * from GIAODICH where maGD=?";
        List<GiaoDich> list = getGD(sql, maGD);
        return list.get(0);
    }
    public List<GiaoDich> getMaKhoan(String maKhoan){
        String sql = "select * from GIAODICH where maKhoan=?";
        List<GiaoDich> list = getGD(sql, maKhoan);
        return list;
    }
    public ArrayList<Double> thongKeTongThu(String bd, String kt){
        ArrayList<Double> list = new  ArrayList<>();
        String sql = "select tienGD from GIAODICH inner join KHOAN on GIAODICH.maKhoan = KHOAN.maKhoan where KHOAN.loaiKhoan='1' and ngayGD >='"+bd+"' and ngayGD <='"+kt+"'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getDouble(0));
            c.moveToNext();
        }
        return list;
    }
    public ArrayList<Double> thongKeTongChi(String bd, String kt){
        ArrayList<Double> list = new  ArrayList<>();
        String sql = "select tienGD from GIAODICH inner join KHOAN on GIAODICH.maKhoan = KHOAN.maKhoan where KHOAN.loaiKhoan='0' and ngayGD >='"+bd+"' and ngayGD <='"+kt+"'";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getDouble(0));
            c.moveToNext();
        }
        return list;
    }
    public double thongKeConLai(){
        double tong = 0;
        String sql = "select sum(tienGD) as TONG from GIAODICH where ngayGD <= NOW()";
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            tong = Double.parseDouble(c.getString(c.getColumnIndex("TONG")));
        }
        return tong;
    }
}
