package ru.cft.shift2023winter.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import org.koin.core.parameter.parametersOf
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.presentation.UserDetailsUiState
import ru.cft.shift2023winter.presentation.UserDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment  : Fragment(R.layout.fragment_user_details) {

    private val progressBar get() = requireView().findViewById<ProgressBar>(R.id.progressBar)
    private val errorText get() = requireView().findViewById<TextView>(R.id.errorText)
    private val userDetailsContent get() = requireView().findViewById<LinearLayout>(R.id.userDetailsContent)

    private val userFullNameText get() = requireView().findViewById<TextView>(R.id.userFullNameText)
    private val userEmailText get() = requireView().findViewById<TextView>(R.id.userEmailText)
    private val userLoginUserNameText get() = requireView().findViewById<TextView>(R.id.userLoginUserNameText)
    private val userLoginPasswordText get() = requireView().findViewById<TextView>(R.id.userLoginPasswordText)
    private val userLocationCountryText get() = requireView().findViewById<TextView>(R.id.userLocationCountryText)
    private val userLocationStateText get() = requireView().findViewById<TextView>(R.id.userLocationStateText)
    private val userLocationStreetText get() = requireView().findViewById<TextView>(R.id.userLocationStreetText)
    private val userPhoneText get() = requireView().findViewById<TextView>(R.id.userPhoneText)
    private val userImageView get() = requireView().findViewById<ImageView>(R.id.userImageDetailsView)

    private val args: UserDetailsFragmentArgs by navArgs()

    private val viewModel: UserDetailsViewModel by viewModel { parametersOf(args.userId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        viewModel.loadData()
    }

    private fun handleState(state: UserDetailsUiState) {
        when (state) {
            UserDetailsUiState.Initial    -> Unit
            UserDetailsUiState.Loading    -> showProgress()
            is UserDetailsUiState.Content -> showContent(state.user)
            is UserDetailsUiState.Error   -> showError(state.message)
        }
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorText.isVisible = false
        userDetailsContent.isVisible = false
    }

    private fun showContent(user: User) {
        progressBar.isVisible = false
        errorText.isVisible = false
        userDetailsContent.isVisible = true

        userFullNameText.setText(user.name)
        userEmailText.setText(user.email)
        userLoginUserNameText.setText(user.login.username)
        userLoginPasswordText.setText(user.login.password)
        userLocationCountryText.setText(user.location.country)
        userLocationStateText.setText(user.location.state)
        userLocationStreetText.setText(user.location.street)
        userPhoneText.setText(user.phone)
        userImageView.load(user.picture.large)
    }


    private fun showError(message: String?) {
        errorText.isVisible = true
        progressBar.isVisible = false
        userDetailsContent.isVisible = false

        errorText.text = message ?: requireContext().getText(R.string.unknown_error)
    }
}