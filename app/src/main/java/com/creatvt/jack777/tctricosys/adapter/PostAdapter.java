package com.creatvt.jack777.tctricosys.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creatvt.jack777.tctricosys.R;
import com.creatvt.jack777.tctricosys.model.Post;

import java.util.List;



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;
    private int rowLayout;
    private Context context;


    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title,body,Id,userId;



        public PostViewHolder(View v) {
            super(v);
            title =  v.findViewById(R.id.title);
            body =  v.findViewById(R.id.body);
            Id =  v.findViewById(R.id.Id);
            userId =  v.findViewById(R.id.userId);
        }
    }

    public PostAdapter(List<Post> posts, int rowLayout, Context context) {
        this.posts = posts;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getBody());
        holder.Id.setText(posts.get(position).getId());
        holder.userId.setText(posts.get(position).getUserId().toString());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
