package com.github.paulajcm.ohmydog.android.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.paulajcm.ohmydog.android.R

class BreedListFragment : Fragment() {

    companion object {
        fun newInstance() = BreedListFragment()
    }

    private lateinit var viewModel: BreedListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.breed_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BreedListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}