package com.example.Android.model;

public class Khoan {
    public String maKhoan;
    public String tenKhoan;
    public String loaiKhoan;

    public Khoan(String maKhoan, String tenKhoan, String loaiKhoan) {
        this.maKhoan = maKhoan;
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public Khoan(String tenKhoan, String loaiKhoan) {
        this.tenKhoan = tenKhoan;
        this.loaiKhoan = loaiKhoan;
    }

    public Khoan() {
    }
}
