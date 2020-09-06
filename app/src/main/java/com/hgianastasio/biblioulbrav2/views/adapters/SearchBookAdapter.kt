package com.hgianastasio.biblioulbrav2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.hgianastasio.biblioulbrav2.R
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
        holder.tvBookTitle?.text = model?.title
        holder.tvAuthorName!!.text = String.format("Autor: %s", model?.author)
        holder.tvBookYear!!.text = String.format("Ano: %s", model?.year)
        holder.tvBookNumber!!.text = String.format("#%d", model?.number)
        if (onItemClickListener != null) holder.root!!.setOnClickListener { onItemClickListener!!.onItemClick(model) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        @kotlin.jvm.JvmField
        @BindView(R.id.tvBookTitle)
        var tvBookTitle: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvAuthorName)
        var tvAuthorName: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvBookYear)
        var tvBookYear: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvBookNumber)
        var tvBookNumber: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.rlRoot)
        var root: RelativeLayout? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }

}