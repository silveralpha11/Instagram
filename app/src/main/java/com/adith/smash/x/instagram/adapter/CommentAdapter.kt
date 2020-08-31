package com.adith.smash.x.instagram.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adith.smash.x.instagram.R
import com.adith.smash.x.instagram.model.Comment
import com.adith.smash.x.instagram.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView



class CommentAdapter(private val mContext: Context, private val mComment: MutableList<Comment>)
    :RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

    private var firebaseUser: FirebaseUser? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //inisialisasi id comment Layout
        var imageProfile: CircleImageView = itemView.findViewById(R.id.user_profile_image_comment)
        var userNameCommentTV: TextView = itemView.findViewById(R.id.user_name_comment)
        var commentTv: TextView = itemView.findViewById(R.id.comment_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.comment_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mComment.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val comment = mComment[position]

        holder.commentTv.text = comment.getComment()
        //create method untuk mengambil datauser

        getUserInfo(holder.imageProfile, holder.userNameCommentTV, comment.getPublisher())
    }

    private fun getUserInfo(
        imageProfile: CircleImageView,
        userNameCommentTV: TextView,
        publisher: String
    ) {
        val userRef = FirebaseDatabase.getInstance().reference.child("Users").child(publisher)
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val user = p0.getValue(User::class.java)

                    Picasso.get().load(user!!.getImage()).placeholder(R.drawable.profile)
                        .into(imageProfile)
                    userNameCommentTV.text = user!!.getUsername()
                }
            }
        })
    }

}