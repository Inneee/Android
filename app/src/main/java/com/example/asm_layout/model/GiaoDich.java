package com.example.asm_layout.model;

import java.util.Date;

public class GiaoDich {
    public String maGD;
    public Date ngayGD;
    public String motaGD;
    public double tienGD;
    public String maKhoan;

    public GiaoDich(String maGD, Date ngayGD, String motaGD, double tienGD, String maKhoan) {
        this.maGD = maGD;
        this.ngayGD = ngayGD;
        this.motaGD = motaGD;
        this.tienGD = tienGD;
        this.maKhoan = maKhoan;
    }

    public GiaoDich(Date ngayGD, String motaGD, double tienGD, String maKhoan) {
        this.ngayGD = ngayGD;
        this.motaGD = motaGD;
        this.tienGD = tienGD;
        this.maKhoan = maKhoan;
    }

    public GiaoDich() {
    }
}
