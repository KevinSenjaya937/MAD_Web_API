package au.edu.curtin.assignment2a.controllers

import au.edu.curtin.assignment2a.Post
import au.edu.curtin.userinfo.User

class UserController {

    private var userList = ArrayList<User>()
    private var postsMap = HashMap<Int, ArrayList<Post>>()

    fun addUser(user: User) {
        this.userList.add(user)
    }

    fun getUserList(): ArrayList<User> {
        return this.userList
    }

    fun addPost(post: Post) {
        val id = post.userId
        if (postsMap.containsKey(id)) {
            postsMap[id]?.add(post)
        } else {
            postsMap[id] = ArrayList<Post>()
            postsMap[id]?.add(post)
        }
    }

    fun getPosts(userID: Int): ArrayList<Post> {

        if (postsMap.containsKey(userID)) {
            return postsMap[userID]!!
        } else {
            return ArrayList<Post>()
        }
    }
}