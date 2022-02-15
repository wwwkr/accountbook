package com.passcombine.accountbook.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.passcombine.accountbook.util.OnCustomClickListener;
import com.passcombine.accountbook.vo.AccountBookVo;
import com.passcombine.accountbook.R;


import java.util.ArrayList;

public class RvAdapter_AccBook extends RecyclerView.Adapter<RvAdapter_AccBook.ItemViewHolder> {


    Activity mActivity;

    ArrayList<AccountBookVo> mAccountBookVo;


    private OnCustomClickListener listener = null;


    public void setOnLongClickListener(OnCustomClickListener listener){
        this.listener = listener;

    }
    public RvAdapter_AccBook(Activity mActivity, ArrayList<AccountBookVo> mAccountBookVo) {
        this.mActivity = mActivity;
        this.mAccountBookVo = mAccountBookVo;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_book, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {


            holder.onBind(mAccountBookVo.get(position));

    }

    @Override
    public int getItemCount() {
        return mAccountBookVo.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private TextView date;
        private TextView inOut;
        private TextView price;

        ItemViewHolder(View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.tv_content);
            date = itemView.findViewById(R.id.tv_date);
            inOut = itemView.findViewById(R.id.tv_inOut);
            price = itemView.findViewById(R.id.tv_price);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){

                        if(listener != null){
                            listener.onLongClick(view, position);
                        }
                    }

                    return false;
                }
            });
        }

        void onBind(AccountBookVo data) {
            content.setText(data.getContent());
            date.setText(data.getDate());
            inOut.setText(data.getInOut());
            price.setText(data.getPrice());


            if(inOut.getText().equals("수입")){

                inOut.setTextColor(Color.RED);

            }else {
                inOut.setTextColor(Color.BLUE);
            }

      }
    }

    public void setData(ArrayList<AccountBookVo> mAccountBookVo){

        this.mAccountBookVo = mAccountBookVo;

    }

}
