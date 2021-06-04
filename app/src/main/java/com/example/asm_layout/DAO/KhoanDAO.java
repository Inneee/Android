package com.example.asm_layout.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_layout.database.DbHelper;
import com.example.asm_layout.model.GiaoDich;
import com.example.asm_layout.model.Khoan;

import java.util.ArrayList;
import java.util.List;

public class KhoanDAO {
    private SQLiteDatabase db;
    public KhoanDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Khoan khoan){
        ContentValues values = new ContentValues();
        values.put("tenKhoan", khoan.tenKhoan);
        values.put("loaiKhoan",khoan.loaiKhoan);
        return db.insert("KHOAN", null,values);
    }
    public long update(Khoan khoan){
        ContentValues values = new ContentValues();
        values.put("maKhoan",khoan.maKhoan);
        values.put("tenKhoan", khoan.tenKhoan);
        values.put("loaiKhoan",khoan.loaiKhoan);
        return db.update("KHOAN",values,"maKhoan=?",new String[]{khoan.maKhoan});
    }
    public long delete(String maKhoan){
        return db.delete("KHOAN", "maKhoan=?",new String[]{maKhoan});
    }
    public List<Khoan> getKhoan(String sql, String...a){
        List<Khoan> list = new ArrayList<Khoan>();
        Cursor c = db.rawQuery(sql,a);
        while (c.moveToNext()){
            Khoan khoan = new Khoan();
            khoan.maKhoan = c.getString(c.getColumnIndex("maKhoan"));
            khoan.tenKhoan = c.getString(c.getColumnIndex("tenKhoan"));
            khoan.loaiKhoan = c.getString(c.getColumnIndex("loaiKhoan"));
            list.add(khoan);
        }
        return list;
    }

    public List<Khoan> getTenKhoan(){
        String sql = "select tenKhoan from KHOAN where Khoan.maKhoan = GIAODICH.maKhoan";
        return getKhoan(sql);
    }
    //get All
    public List<Khoan> getAll(){
        String sql = "select * from KHOAN";
        return getKhoan(sql);
    }
    //get Khoản theo mã Khoản
    public Khoan getMaKhoan(String maKhoan){
        String sql = "select * from KHOAN where makhoan=?";
        List<Khoan> list = getKhoan(sql, maKhoan);
        return list.get(0);
    }
    //get Khoản theo loại khoản
    public List<Khoan> getLoaiKhoan(String loaiKhoan){
        String sql = "select * from KHOAN where loaiKhoan=?";
        List<Khoan> list = getKhoan(sql, loaiKhoan);
        return list;
    }
}
