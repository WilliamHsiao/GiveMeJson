package com.app.william.givemejson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.william.givemejson.databinding.ItemPicBinding
import com.google.android.flexbox.FlexboxLayoutManager




class PicAdapter : Adapter<PicAdapter.ViewHolder>() {
  private  val list: ArrayList<PicData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPicBinding = ItemPicBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)

    }

    fun add(l: PicData) {

        list.add(l)
        notifyItemInserted(list.size-1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.set(list[position])
    }

    class ViewHolder(private val binding: ItemPicBinding) : RecyclerView.ViewHolder(binding.layout) {

        fun set(data: PicData) {
            binding.info = data
            val lp: ViewGroup.LayoutParams = binding.layout.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                val flexboxLp = binding.layout.layoutParams as FlexboxLayoutManager.LayoutParams
                flexboxLp.flexGrow = 1.0f
            }
        }

    }
}