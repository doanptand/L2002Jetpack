package com.ddona.pokemon.di_demo;
//we don't own. so we can't modify anything in this class
public class Wheel {

    int kichThuoc;

    public Wheel(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public Wheel() {

    }

    public String printInfo() {
        return "wheel size:" + kichThuoc;
    }
}
