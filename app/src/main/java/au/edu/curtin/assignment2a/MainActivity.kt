package au.edu.curtin.assignment2a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import au.edu.curtin.assignment2a.controllers.UserController
import au.edu.curtin.assignment2a.fragments.UserFragment
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private var executorService = Executors.newSingleThreadExecutor()
    private var userController = UserController()
    private var userFragment = UserFragment(userController)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val backGroundTaskHandler = BackGroundTaskHandler(this, userFragment, supportFragmentManager, userController)
        executorService.execute(backGroundTaskHandler)
    }
}