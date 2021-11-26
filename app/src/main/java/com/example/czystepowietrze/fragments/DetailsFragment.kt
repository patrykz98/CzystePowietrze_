package com.example.czystepowietrze.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.czystepowietrze.R
import com.example.czystepowietrze.Repository.RetrofitRepository
import com.example.czystepowietrze.adapter.DetailsRecyclerViewAdapter
import com.example.czystepowietrze.databinding.FragmentDetailsBinding
import com.example.czystepowietrze.viewModel.FragmentViewModel
import com.example.retrofittest.FragmentViewModelFactory

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get () = _binding!!

    private lateinit var viewModel: FragmentViewModel
    private val rvAdapter by lazy {
        DetailsRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setupRecyclerview()

        val retrofitRepository = RetrofitRepository()
        val viewModelFactory = FragmentViewModelFactory(retrofitRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentViewModel::class.java)
        viewModel.getAllStations()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
            if(response.isSuccessful){
                response.body()?.let {
                    rvAdapter.setData(it)
                }
            }else{
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })


        return binding.root
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = rvAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener{
            val fragment = MenuFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainLayout,fragment)?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}