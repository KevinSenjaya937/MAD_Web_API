package au.edu.curtin.assignment2a.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.assignment2a.Post
import au.edu.curtin.assignment2a.R
import org.w3c.dom.Text

class PostsAdapter(private val data: ArrayList<Post>, private val userId: Int): RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var postId: TextView
        private var title: TextView
        private var body: TextView

        init {
            postId = itemView.findViewById(R.id.postIdText)
            title = itemView.findViewById(R.id.titleText)
            body = itemView.findViewById(R.id.bodyText)
        }

        fun bind(post: Post) {
            postId.text = post.id.toString()
            title.text  = post.title
            body.text   = post.body
        }
    }


}