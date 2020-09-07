package com.hgianastasio.biblioulbrav2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.databinding.RenewBookItemBinding
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel

/**
 * Created by heitor_12 on 04/05/17.
 */
class RenewLoanBookAdapter(private val list: List<LoanBookModel?>, private val mContext: Context?) : RecyclerView.Adapter<RenewLoanBookAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.renew_book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.binding.tvBookTitle.text = model?.title
        holder.binding.tvDeadline.text = model?.deadline + if (model!!.isSingleCopy) "*" else ""
        val color: Int
        color = if (model.isOverdue) R.color.redPenalty else if (model.wasJustRenewed()) R.color.yellowWarning else R.color.greenPenalty
        holder.binding.tvDeadline.setTextColor(ContextCompat.getColor(mContext!!, color))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RenewBookItemBinding.bind(itemView)
    }

}