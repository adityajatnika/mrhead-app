package com.example.mrhead.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mrhead.R;
import com.example.mrhead.model.Barang;
import com.example.mrhead.viewholder.BarangViewHolder;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangViewHolder> {

    private ArrayList<Barang> _barangList;
    private Context _context;

    public BarangAdapter(ArrayList<Barang> _barangList, Context context) {
        this._barangList = _barangList;
        this._context = context;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent, false);
        BarangViewHolder viewHolder = new BarangViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        holder.get_txNama().setText(_barangList.get(position).get_nama());
        holder.get_txHarga().setText(_barangList.get(position).get_harga());
        Glide.with(_context).load(_barangList.get(position).get_gambar()).into(holder.get_gambar());

    }

    @Override
    public int getItemCount() {
        return _barangList.size();
    }
}
