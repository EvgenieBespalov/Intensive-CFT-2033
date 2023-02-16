package ru.cft.shift2023winter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import ru.cft.shift2023winter.data.repository.UserRepositoryImpl
import ru.cft.shift2023winter.screen.FilterFragmentDirections
import ru.cft.shift2023winter.screen.UserListFragmentDirections

class MainActivity : AppCompatActivity() {

	private val navController get() = findNavController(R.id.fragmentContainer)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun openUserDetails(userIdName: String) {
		val action = UserListFragmentDirections.actionUserFragmentToUserDetailsFragment2(userIdName)
		navController.navigate(action)
	}

	fun searchUsers(countOfUsers: Int, selectGender: String?, seed: String?) {
		val action = FilterFragmentDirections.actionFilterFragmentToUserFragment(selectGender, seed, countOfUsers)
		navController.navigate(action)
	}
}