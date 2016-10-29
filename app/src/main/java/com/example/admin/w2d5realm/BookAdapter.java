package com.example.admin.w2d5realm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 10/28/2016.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private ArrayList<Book> mArrayList;

    public BookAdapter(ArrayList<Book> mArrayList) {
        this.mArrayList = mArrayList;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View termView = inflater.inflate(R.layout.recycler_item, parent, false);

        return new BookAdapter.ViewHolder(termView);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        Book book = mArrayList.get(position);

        TextView textViewName = holder.textViewName;
        textViewName.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "BookAdapterTAG_";
        public final TextView textViewName;
        public String value;

        public ViewHolder(final View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.r_item_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }
    }
}
