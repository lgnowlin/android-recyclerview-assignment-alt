package com.ualr.recyclerviewassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.R;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

public class AdapterList extends RecyclerView.Adapter {
    private List<Inbox> mItems;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public AdapterList(Context context, List<Inbox> items){
        this.mItems = items;
        this.mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        RecyclerView.ViewHolder vh;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email_chat, parent, false);
        vh = new InboxViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InboxViewHolder viewHolder = (InboxViewHolder)holder;
        Inbox i = mItems.get(position);
        viewHolder.from.setText(i.getFrom());
        viewHolder.message.setText(i.getMessage());
        viewHolder.date.setText(i.getDate());
        viewHolder.email.setText(i.getEmail());
    }

    @Override
    public int getItemCount(){
        return this.mItems.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, Inbox obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }

    public class InboxViewHolder extends RecyclerView.ViewHolder {
        public TextView from;
        public TextView message;
        public TextView date;
        public TextView email;
        public View lyt_parent;

        public InboxViewHolder(@NonNull View v) {
            super(v);
            from = v.findViewById(R.id.name);
            message = v.findViewById(R.id.message);
            date = v.findViewById(R.id.date);
            email = v.findViewById(R.id.email);
            lyt_parent = v.findViewById(R.id.lyt_parent);

            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, mItems.get(getLayoutPosition()), getLayoutPosition());
                }
            });
        }
    }

        public void addItem(int position, Inbox inbox){
            mItems.add(0, inbox);
            notifyItemInserted(position);
        }

        public void deleteItem(int position){
            if (position >= mItems.size())
                return;
            mItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }

    }

