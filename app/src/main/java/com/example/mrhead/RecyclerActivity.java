package com.example.mrhead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrhead.adapter.BarangAdapter;
import com.example.mrhead.model.Barang;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    BarangAdapter barangAdapter;
    ArrayList<Barang> _barangList;
    Button _btTambah;
    int[] gambar ={R.drawable.drum_set, R.drawable.headphone, R.drawable.radio,
            R.drawable.electric_guitar, R.drawable.electronic_music, R.drawable.glasses,
            R.drawable.keyboard, R.drawable.music_speaker, R.drawable.smartphone};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        _btTambah = findViewById(R.id.btTambah);
        _btTambah.setOnClickListener(this);
        loadData();
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.id_rvBarang);
        barangAdapter = new BarangAdapter(_barangList, this);
        recyclerView.setAdapter(barangAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addData(){
        Random random = new Random();
        //pilih harga
        int pilHarga = random.nextInt(999);
        //pilih barang
        int pilBarang = random.nextInt(9);
        //tentukan nama barang
        String namaBarang;

        switch (pilBarang){
            case 0: namaBarang = "Drum set"; break;
            case 1: namaBarang = "Headphone"; break;
            case 2: namaBarang = "Radio"; break;
            case 3: namaBarang = "Gitar"; break;
            case 4: namaBarang = "TV"; break;
            case 5: namaBarang = "Kacamata"; break;
            case 6: namaBarang = "Piano"; break;
            case 7: namaBarang = "Speaker"; break;
            case 8: namaBarang = "HP"; break;

            default:
                throw new IllegalStateException("Unexpected value: " + pilBarang);
        }

        _barangList.add(new Barang(namaBarang, "Rp."+pilHarga+".000,-",gambar[pilBarang]));

    }

    private void loadData(){

        _barangList = new ArrayList<>();
        _barangList.add(new Barang("Drum Set", "Rp. 200.000,-",gambar[0]));
        _barangList.add(new Barang("Headphone", "Rp. 99.000,-",gambar[1]));
        _barangList.add(new Barang("Radio", "Rp. 250.000,-",gambar[2]));

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==_btTambah.getId()){
            addData();
            barangAdapter.notifyDataSetChanged();
        }
    }
}