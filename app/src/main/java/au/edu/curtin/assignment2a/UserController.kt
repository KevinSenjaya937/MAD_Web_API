package au.edu.curtin.assignment2a

import au.edu.curtin.userinfo.User

class UserController {

    private var userList = ArrayList<User>()

    fun addUser(user: User) {
        this.userList.add(user)
    }

    fun getUserList(): ArrayList<User> {
        return this.userList
    }
}