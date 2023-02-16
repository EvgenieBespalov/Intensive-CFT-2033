package ru.cft.shift2023winter.screen

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.mainActivity
import ru.cft.shift2023winter.presentation.UserListUiState
import ru.cft.shift2023winter.presentation.UserListViewModel


class UserListFragment : Fragment(R.layout.fragment_user_list){

    private val progressBar get() = requireView().findViewById<ProgressBar>(R.id.progressBar)
    private val errorText get() = requireView().findViewById<TextView>(R.id.errorText)
    private val historyContent get() = requireView().findViewById<LinearLayout>(R.id.usersContent)

    private var historyList: RecyclerView? = null

    private val args: UserListFragmentArgs by navArgs()

    private val viewModel: UserListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyList = view.findViewById(R.id.userList)
        historyList?.adapter = UserListAdapter(::handleLoanClick)

        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        if(args.countOfUsers != 0)
            viewModel.loadData(args.countOfUsers.toString(), args.selectedGender, args.seed)
    }

    private fun handleState(state: UserListUiState) {
        when (state) {
            UserListUiState.Initial    -> Unit
            UserListUiState.Loading    -> showProgress()
            is UserListUiState.Content -> showContent(state.users)
            is UserListUiState.Error   -> showError(state.message)
        }
    }

    private fun showContent(listUser: List<User>) {
        progressBar.isVisible = false
        errorText.isVisible = false
        historyContent.isVisible = true
        (historyList?.adapter as? UserListAdapter)?.users = listUser
    }

    private fun handleLoanClick(user: User) {
       mainActivity.openUserDetails(user.id)
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorText.isVisible = false
        historyContent.isVisible = false
    }

    private fun showError(message: String?) {
        errorText.isVisible = true
        progressBar.isVisible = false
        historyContent.isVisible = false

        errorText.text = message ?: requireContext().getText(R.string.unknown_error)
    }

    override fun onDestroy() {
        historyList?.adapter = null
        historyList = null
        super.onDestroy()
    }
}