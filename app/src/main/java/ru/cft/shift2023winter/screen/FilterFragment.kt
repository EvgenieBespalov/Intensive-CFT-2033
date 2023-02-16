package ru.cft.shift2023winter.screen

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.User
import ru.cft.shift2023winter.mainActivity
import ru.cft.shift2023winter.presentation.FilterUiState
import ru.cft.shift2023winter.presentation.FilterViewModel
import ru.cft.shift2023winter.presentation.UserListUiState
import ru.cft.shift2023winter.presentation.UserListViewModel

class FilterFragment : Fragment(R.layout.fragment_filter)  {
    private val progressBar get() = requireView().findViewById<ProgressBar>(R.id.progressBar)
    private val errorText get() = requireView().findViewById<TextView>(R.id.errorText)
    private val filterContent get() = requireView().findViewById<LinearLayout>(R.id.filterContent)

    private val viewModel: FilterViewModel by viewModel()

    private val numberOfUsersLabel get() = requireView().findViewById<TextView>(R.id.numberOfUsersLabel)
    private val numberOfUsersSeekBar get() = requireView().findViewById<SeekBar>(R.id.numberOfUsersSeekBar)
    private val genderRadioButtons get() = requireView().findViewById<RadioGroup>(R.id.genderRadioGroup)
    private val searchButton get() = requireView().findViewById<Button>(R.id.filterSearchButton)
    private val seedEditText get() = requireView().findViewById<EditText>(R.id.filterSeedEditText)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        viewModel.initial()

        setListeners()
    }

    private fun setListeners(){
        searchButton.setOnClickListener {
            val selectRadioButton = requireView().findViewById<RadioButton>(genderRadioButtons.checkedRadioButtonId)
            val selectGender = when(selectRadioButton.text){
                "Female" -> "female"
                "Male" -> "male"
                else -> null
            }

            viewModel.setNeedToLoadNewUsers()

            val seed = when(seedEditText.text.toString().length){
                0 -> null
                else -> seedEditText.text.toString()
            }
            mainActivity.searchUsers(numberOfUsersSeekBar.progress, selectGender, seed)
        }

       numberOfUsersSeekBar.setOnSeekBarChangeListener(
           object : SeekBar.OnSeekBarChangeListener {
               override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                   numberOfUsersLabel.setText("Number of users: " + progress)
               }

               override fun onStartTrackingTouch(seekBar: SeekBar?) {}
               override fun onStopTrackingTouch(seekBar: SeekBar?) {}
           })

    }

    private fun handleState(state: FilterUiState) {
        when (state) {
            FilterUiState.Initial    -> showContent()
        }
    }

    private fun showContent() {
        progressBar.isVisible = false
        errorText.isVisible = false
    }
}