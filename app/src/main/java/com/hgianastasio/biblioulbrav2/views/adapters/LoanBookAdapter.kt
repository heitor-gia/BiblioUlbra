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
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel

/**
 * Created by heitor_12 on 04/05/17.
 */
class LoanBookAdapter(private val list: List<LoanBookModel?>, private val mContext: Context?) : RecyclerView.Adapter<LoanBookAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(loanBookModel: LoanBookModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.loan_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.tvBookTitle!!.text = model?.title
        holder.tvDeadline!!.text = String.format("Prazo: %s", model?.deadline)
        holder.tvBookPenalty!!.text = String.format("Multa: R$%s", model?.penalty)
        if (onItemClickListener != null) holder.root!!.setOnClickListener {  onItemClickListener!!.onItemClick(model) }
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
        @BindView(R.id.tvDeadline)
        var tvDeadline: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.tvBookPenalty)
        var tvBookPenalty: TextView? = null

        @kotlin.jvm.JvmField
        @BindView(R.id.rlRoot)
        var root: RelativeLayout? = null

        init {
            ButterKnife.bind(this, itemView!!)
        }
    }

}