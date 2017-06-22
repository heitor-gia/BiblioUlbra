package com.hgianastasio.biblioulbrav2.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 04/05/17.
 */

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.ViewHolder> {
    private List<SearchBookModel> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public SearchBookAdapter(List<SearchBookModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    public interface OnItemClickListener{
        void onItemClick(SearchBookModel SearchBookModel);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_book_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchBookModel model = list.get(position);
        holder.tvBookTitle.setText(model.getTitle());
        holder.tvAuthorName.setText(String.format("Autor: %s",model.getAuthor()));
        holder.tvBookYear.setText(String.format("Ano: %s",model.getYear()));
        holder.tvBookNumber.setText(String.format("#%d",model.getNumber()));
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
        @BindView(R.id.tvAuthorName) TextView tvAuthorName;
        @BindView(R.id.tvBookYear) TextView tvBookYear;
        @BindView(R.id.tvBookNumber) TextView tvBookNumber;
        @BindView(R.id.rlRoot) RelativeLayout root;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
