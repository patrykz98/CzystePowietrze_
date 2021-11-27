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
import com.example.czystepowietrze.adapter.SearchByCityRecyclerAdapter
import com.example.czystepowietrze.databinding.FragmentSearchByCityBinding
import com.example.czystepowietrze.viewModel.FragmentViewModel
import com.example.retrofittest.FragmentViewModelFactory

class SearchByCityFragment : Fragment() {

    private var _binding: FragmentSearchByCityBinding? = null
    private val binding get () = _binding!!

    private val rvAdapter by lazy {
        SearchByCityRecyclerAdapter()
    }
    private lateinit var viewModel: FragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchByCityBinding.inflate(inflater, container, false)
        val arguments = this.arguments


        binding.buttonSearchByCity.setOnClickListener{
            if(binding.editTextCity.text.isNotEmpty()){
                val retrofitRepository = RetrofitRepository()
                val viewModelFactory = FragmentViewModelFactory(retrofitRepository)
                viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentViewModel::class.java)
                viewModel.getAllStations()
                viewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
                    if(response.isSuccessful){
                        response.body()?.let {
                            rvAdapter.setData(it, binding.editTextCity.text.toString().lowercase())
                        }
                        setupRecyclerview()
                    }else{
                        Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        return binding.root
    }

    private fun setupRecyclerview() {
        binding.recyclerViewSearchByCity.adapter = rvAdapter
        binding.recyclerViewSearchByCity.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener{
            val fragment = DetailsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainLayout,fragment)?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}