package au.edu.curtin.assignment2a.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.assignment2a.Post
import au.edu.curtin.assignment2a.R
import au.edu.curtin.assignment2a.controllers.UserController

class PostsFragment(private val controller: UserController,
                    private val userId: Int) : Fragment() {

    private lateinit var adapter: PostsAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView = view.findViewById(R.id.postsRecycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        val postList = controller.getPosts(userId)
        adapter = PostsAdapter(postList, userId)
        recyclerView.adapter = adapter

        val backBtn = view.findViewById<Button>(R.id.backBtn)

        backBtn.setOnClickListener {
            val user = controller.getUser(userId)
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.userListFragment, UserDetailsFragment(user!!, controller))
                commit()
            }
        }
    }
}