package com.github.paulajcm.ohmydog.android.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.paulajcm.ohmydog.android.R

class BreedListFragment : Fragment() {

    companion object {
        fun newInstance() = BreedListFragment()
    }

    private lateinit var viewModel: BreedListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.breed_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BreedListViewModel::class.java)
        viewModel.refreshBreeds()
        viewModel.dogs.observe(viewLifecycleOwner, Observer { it -> it.forEach { Log.i("DOGG", it.breed.name) } })
    }

}