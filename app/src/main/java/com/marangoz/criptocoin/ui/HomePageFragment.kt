package com.marangoz.criptocoin.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.marangoz.criptocoin.R
import com.marangoz.criptocoin.adapter.HomeAdapter
import com.marangoz.criptocoin.databinding.FragmentHomePageBinding
import com.marangoz.criptocoin.repository.RetrofitRepo
import com.marangoz.criptocoin.utils.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomePageFragment : Fragment(), SearchView.OnQueryTextListener, MenuProvider {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val repo by lazy { RetrofitRepo() }
        val viewModelFactory by lazy { HomePageViewModelFactory(repo) }
        val adap: HomeAdapter by lazy { HomeAdapter(requireActivity()) }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(HomePageViewModel::class.java)


        binding.rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adap
        }



        viewModel.criptoList.observe(requireActivity()) {
            it.body()?.let { it1 -> adap.setList(it1) }
        }

        viewModel.getCripto()



        return binding.root
    }


    override fun onQueryTextSubmit(p0: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(asset_id: String?): Boolean {
        if (asset_id != null) {
            viewModel.getCriptoRetrofit(asset_id)
        }
        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.toolbarmenu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }


}


