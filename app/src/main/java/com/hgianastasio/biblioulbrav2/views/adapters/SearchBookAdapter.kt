package com.hgianastasio.biblioulbrav2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.databinding.SearchBookItemBinding
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel

/**
 * Created by heitor_12 on 04/05/17.
 */
class SearchBookAdapter(private val list: List<SearchBookModel?>, private val mContext: Context?) : RecyclerView.Adapter<SearchBookAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(SearchBookModel: SearchBookModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.search_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.binding.tvBookTitle.text = model?.title
        holder.binding.tvAuthorName.text = String.format("Autor: %s", model?.author)
        holder.binding.tvBookYear.text = String.format("Ano: %s", model?.year)
        holder.binding.tvBookNumber.text = String.format("#%d", model?.number)
        holder.binding.root.setOnClickListener { onItemClickListener?.onItemClick(model) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val binding = SearchBookItemBinding.bind(itemView)
    }

}