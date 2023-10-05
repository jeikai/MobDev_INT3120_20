package com.example.bai_tap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bai_tap.databinding.ItemNumbersBinding

class NumbersAdapter(
    var numbers : List<Numbers>
) : RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>() {
    inner class NumbersViewHolder(val binding: ItemNumbersBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNumbersBinding.inflate(layoutInflater,parent,false)
        return NumbersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.binding.tvNumber.text = numbers[position].number.toString()
    }

}