package com.github.paulajcm.ohmydog.android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.paulajcm.ohmydog.android.R
import com.github.paulajcm.ohmydog.android.databinding.BreedListFragmentBinding
import com.github.paulajcm.ohmydog.android.model.domain.Dog

class BreedListFragment : Fragment() {

    companion object {
        fun newInstance() = BreedListFragment()
    }

    private lateinit var binding: BreedListFragmentBinding
    private lateinit var viewModel: BreedListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.breed_list_fragment, container, false)
        binding = BreedListFragmentBinding.bind(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = BreedListAdapter()
        binding.recyclerView.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(BreedListViewModel::class.java)
        viewModel.refreshBreeds()
        viewModel.dogs.observe(viewLifecycleOwner, observeDataChanges())
    }

    private fun observeDataChanges(): Observer<List<Dog>> {
        return Observer {
            if (it.isEmpty()) {
                return@Observer
            }
            (binding.recyclerView.adapter as BreedListAdapter).addDog(it[it.size - 1], it.size - 1)
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

}