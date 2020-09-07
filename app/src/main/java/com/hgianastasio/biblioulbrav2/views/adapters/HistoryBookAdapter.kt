package com.hgianastasio.biblioulbrav2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.databinding.HistoryBookItemBinding
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel

/**
 * Created by heitor_12 on 04/05/17.
 */
class HistoryBookAdapter(private val list: List<HistoryBookModel?>, private val mContext: Context?) : RecyclerView.Adapter<HistoryBookAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.history_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.binding.tvBookTitle.text = model?.title
        holder.binding.tvDeadline.text = String.format("Prazo: %s", model?.deadline)
        holder.binding.tvDevolution.text = String.format("Devolução: %s", model?.devolution)
        holder.binding.tvLibrary.text = String.format("Biblioteca: %s", model?.library)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = HistoryBookItemBinding.bind(itemView)
    }

}