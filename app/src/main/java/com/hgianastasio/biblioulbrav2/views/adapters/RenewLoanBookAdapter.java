package com.hgianastasio.biblioulbrav2.views.adapters;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.loanbooks.LoanBookModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 04/05/17.
 */

public class RenewLoanBookAdapter extends RecyclerView.Adapter<RenewLoanBookAdapter.ViewHolder> {
    private List<LoanBookModel> list;
    private Context mContext;

    public RenewLoanBookAdapter(List<LoanBookModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.renew_book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoanBookModel model = list.get(position);
        holder.tvBookTitle.setText(model.getTitle());
        holder.tvDeadline.setText(model.getDeadline().concat(model.isSingleCopy()?"*":""));
        int color;
        if(model.isOverdue())
            color = R.color.redPenalty;
        else if(model.wasJustRenewed())
            color = R.color.yellowWarning;
        else
            color = R.color.greenPenalty;

        holder.tvDeadline.setTextColor(ContextCompat.getColor(mContext,color));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvBookTitle) TextView tvBookTitle;
        @BindView(R.id.tvDeadline) TextView tvDeadline;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
