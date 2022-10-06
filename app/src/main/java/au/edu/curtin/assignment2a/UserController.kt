package au.edu.curtin.assignment2a

import au.edu.curtin.userinfo.User

class UserController {

    private var userList = ArrayList<User>()

    private fun addUser(user: User) {
        this.userList.add(user)
    }

    private fun getUserList(): ArrayList<User> {
        return this.userList
    }
}