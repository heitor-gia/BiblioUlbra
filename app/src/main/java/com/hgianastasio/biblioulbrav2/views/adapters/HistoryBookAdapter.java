package com.hgianastasio.biblioulbrav2.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.historybooks.HistoryBookModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 04/05/17.
 */

public class HistoryBookAdapter extends RecyclerView.Adapter<HistoryBookAdapter.ViewHolder> {
    private List<HistoryBookModel> list;
    private Context mContext;

    public HistoryBookAdapter(List<HistoryBookModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryBookModel model = list.get(position);
        holder.tvBookTitle.setText(model.getTitle());
        holder.tvDeadline.setText(String.format("Prazo: %s",model.getDeadline()));
        holder.tvDevolution.setText(String.format("Devolução: %s",model.getDevolution()));
        holder.tvLibrary.setText(String.format("Biblioteca: %s",model.getLibrary()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvBookTitle) TextView tvBookTitle;
        @BindView(R.id.tvDeadline) TextView tvDeadline;
        @BindView(R.id.tvDevolution) TextView tvDevolution;
        @BindView(R.id.tvLibrary) TextView tvLibrary;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
