package com.vinaylogics.restappkotlin.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinaylogics.restappkotlin.R
import com.vinaylogics.restappkotlin.model.Post
import kotlinx.android.synthetic.main.layout_post_item.view.*

/**
 * Created by vinaylogics on 27-05-2017.
 */
class PostAdapter (val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val  post = posts[position]
        holder.userIdTextView.text = "${post.userId}"
        holder.idTextView.text = "${post.id}"
        holder.titleTextView.text = post.title
        holder.bodyTextView.text = post.body

    }

    override fun getItemCount() =  posts.size


    override fun onCreateViewHolder(parent: ViewGroup, position : Int)= PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_post_item,parent,false))


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val userIdTextView = itemView.userIdTextView
     val idTextView = itemView.idTextView
     val  titleTextView = itemView.titleTextView
     val bodyTextView = itemView.bodyTextView

    }
}