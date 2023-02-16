package ru.cft.shift2023winter

import androidx.fragment.app.Fragment

val Fragment.mainActivity: MainActivity
	get() = requireActivity() as MainActivity