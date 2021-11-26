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
import com.example.czystepowietrze.adapter.DetailsIDRecyclerViewAdapter
import com.example.czystepowietrze.databinding.FragmentDetailsIdBinding
import com.example.czystepowietrze.viewModel.FragmentViewModel
import com.example.retrofittest.FragmentViewModelFactory

class DetailsIdFragment : Fragment() {

    private var _binding: FragmentDetailsIdBinding? = null
    private val binding get () = _binding!!

    private val rvAdapter by lazy {
        DetailsIDRecyclerViewAdapter()
    }
    private lateinit var viewModel: FragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsIdBinding.inflate(inflater, container, false)
        val arguments = this.arguments
        val inputData = arguments?.get("position").toString()

        setupRecyclerview()

        val retrofitRepository = RetrofitRepository()
        val viewModelFactory = FragmentViewModelFactory(retrofitRepository)
        var paramIds = ArrayList<String>()
        var paramValues = ArrayList<String>()

        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentViewModel::class.java)
        viewModel.getStationDetails(inputData)
        viewModel.myResponseDetails.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful){
                val responseData = response.body()!!
                for(i in 0..responseData.size-1){
                    paramIds.add(responseData.get(i).id.toString())
                }



                for(i in 0..paramIds.size-1){
                    viewModel.getParamDetails(paramIds[i])
                    viewModel.myResponseParam
                        .observe(viewLifecycleOwner, Observer { response ->
                        if(response.isSuccessful){
                            val responseData = response.body()!!
                            binding.textView3.text = responseData.values.get(1).value.toString()
                            Toast.makeText(context, "Udało się!", Toast.LENGTH_SHORT).show()
                            response.body()?.let {
                                rvAdapter.setDataParams(responseData.values.get(i).value.toString())
                            }
                        }
                    })
                }


                response.body()?.let {
                    rvAdapter.setData(it)
                }

                binding.textView2.text = paramIds.toString()
            }else{
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    private fun setupRecyclerview() {
        binding.recyclerViewDetailsId.adapter = rvAdapter
        binding.recyclerViewDetailsId.layoutManager = LinearLayoutManager(context)
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