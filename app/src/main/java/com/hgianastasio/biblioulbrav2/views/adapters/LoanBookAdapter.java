package com.hgianastasio.biblioulbrav2.views.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 04/05/17.
 */

public class LoanBookAdapter extends RecyclerView.Adapter<LoanBookAdapter.ViewHolder> {
    private List<LoanBookModel> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public LoanBookAdapter(List<LoanBookModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public interface OnItemClickListener{
        void onItemClick(LoanBookModel loanBookModel);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.loan_book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoanBookModel model = list.get(position);
        holder.tvBookTitle.setText(model.getTitle());
        holder.tvDeadline.setText(String.format("Prazo: %s",model.getDeadline()));
        holder.tvBookPenalty.setText(String.format("Multa: R$%s",model.getPenalty()));
        if (onItemClickListener!=null)
            holder.root.setOnClickListener(v->onItemClickListener.onItemClick(model));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvBookTitle) TextView tvBookTitle;
        @BindView(R.id.tvDeadline) TextView tvDeadline;
        @BindView(R.id.tvBookPenalty) TextView tvBookPenalty;
        @BindView(R.id.rlRoot) RelativeLayout root;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
