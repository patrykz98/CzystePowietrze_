package com.example.czystepowietrze.fragments

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.czystepowietrze.R
import com.example.czystepowietrze.Repository.RetrofitRepository
import com.example.czystepowietrze.databinding.FragmentAvailableLocationsBinding
import com.example.czystepowietrze.viewModel.FragmentViewModel
import com.example.retrofittest.FragmentViewModelFactory

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AvailableLocationsFragment : Fragment() {

    private var _binding: FragmentAvailableLocationsBinding? = null
    private val binding get () = _binding!!
    private val listGegrLat: ArrayList<String> = ArrayList()
    private val listGegrLan: ArrayList<String> = ArrayList()
    private val listCities: ArrayList<String> = ArrayList()
    private val markersList: ArrayList<MarkerOptions> = ArrayList()

    private lateinit var viewModel: FragmentViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        val poland = LatLng(50.010575, 19.949189)
        googleMap.addMarker(MarkerOptions().position(poland).title("Kraków"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poland, 8f))

        initMarkers()
        binding.buttonShowLocations.setOnClickListener{
            googleMap.clear()
            for (marker in markersList){
                googleMap.addMarker(marker)
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(52.24380703884873, 19.586343879798402), 6f))
        }
        binding.radioButtonMapNormal.setOnClickListener{
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        binding.radioButtonMapSatelite.setOnClickListener{
            googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }

        binding.arrowBack.setOnClickListener{
            val fragment = MenuFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainLayout, fragment)?.commit()
        }

        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.uiSettings.isZoomGesturesEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAvailableLocationsBinding.inflate(inflater, container, false)

        val retrofitRepository = RetrofitRepository()
        val viewModelFactory = FragmentViewModelFactory(retrofitRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentViewModel::class.java)
        viewModel.getAllStations()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
            if(response.isSuccessful){
                val responseBody = response.body()!!
                val responseBodySize = responseBody.size
                for(i in 0..responseBodySize-1){
                    listGegrLat.add(responseBody.get(i).gegrLat.toString())
                    listGegrLan.add(responseBody.get(i).gegrLon.toString())
//                    if(!responseBody.get(i).addressStreet.isEmpty())
                        listCities.add(responseBody.get(i).stationName.toString())
//                    else
//                        listCities.add(responseBody.get(i).stationName.toString())
                }
                setMarkers(listGegrLat, listGegrLan, listCities, listGegrLat.size)
            }else{
                Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }


    private fun setMarkers(listLat: ArrayList<String>, listLon: ArrayList<String>, listCities: ArrayList<String>, size: Int){
        for(i in 0..size-1){
            markersList.add(MarkerOptions().position(LatLng(listLat[i].toDouble(), listLon[i].toDouble())).title(listCities[i]).icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        }
    }

    private fun initMarkers(){
        setMarkers(listGegrLat, listGegrLan, listCities, listGegrLat.size)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }
}