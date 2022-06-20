package com.example.mrhead.model;

public class Barang {

    String _nama, _harga;
    int _gambar;

    public Barang(String _nama, String _harga, int _gambar) {
        this._nama = _nama;
        this._harga = _harga;
        this._gambar = _gambar;
    }

    public String get_nama() {
        return _nama;
    }

    public String get_harga() {
        return _harga;
    }

    public int get_gambar() {
        return _gambar;
    }
}
