package com.example.cryptoapp2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp2.databinding.CryptoItemBinding
import com.example.cryptoapp2.model.CryptoModel

class CryptoRecyclerViewAdapter(val cryptoList: ArrayList<CryptoModel>):
    RecyclerView.Adapter<CryptoRecyclerViewAdapter.CryptoViewHolder>() {

        class CryptoViewHolder(val binding: CryptoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.binding.cryptoName.setText(cryptoList.get(position).currency)
        holder.binding.cryptoPrice.setText(cryptoList.get(position).price)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

}