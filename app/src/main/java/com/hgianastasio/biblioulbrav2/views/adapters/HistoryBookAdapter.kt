package com.hgianastasio.biblioulbrav2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.hgianastasio.biblioulbrav2.R
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
        holder.tvBookTitle!!.text = model?.title
        holder.tvDeadline!!.text = String.format("Prazo: %s", model?.deadline)
        holder.tvDevolution!!.text = String.format("Devolução: %s", model?.devolution)
        holder.tvLibrary!!.text = String.format("Biblioteca: %s", model?.library)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        @kotlin.jvm.JvmField
        @BindView(R.id.tvBookTitle)
        var tvBookTitle: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvDeadline)
        var tvDeadline: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvDevolution)
        var tvDevolution: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvLibrary)
        var tvLibrary: TextView? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }

}