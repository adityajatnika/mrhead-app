package com.example.mrhead.viewholder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrhead.R;

public class BarangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView _txNama, _txHarga;
    ImageView _gambar;
    Context _context;

    public BarangViewHolder(@NonNull View itemView) {
        super(itemView);
        _context = itemView.getContext();
        _txNama = itemView.findViewById(R.id.id_txNamaBarang);
        _txHarga = itemView.findViewById(R.id.id_txHarga);
        _txNama.setOnClickListener(this);
        _gambar = itemView.findViewById(R.id.id_gbBarang);


    }

    @Override
    public void onClick(View view) {
        String url = "https://shopee.co.id/search?keyword="+_txNama.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        _context.startActivity(i);
    }

    public TextView get_txNama() {
        return _txNama;
    }

    public TextView get_txHarga() {
        return _txHarga;
    }

    public ImageView get_gambar() {
        return _gambar;
    }

    public Context get_context() {
        return _context;
    }
}
