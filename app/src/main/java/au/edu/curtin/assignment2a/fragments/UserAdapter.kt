package au.edu.curtin.assignment2a.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.assignment2a.R
import au.edu.curtin.userinfo.User

class UserAdapter(private val data: ArrayList<User>,
                  private val listener: OnItemClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var username: TextView

        init {
            username = itemView.findViewById(R.id.usernameText)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(user: User) {
            username.text = user.username
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}