package com.marangoz.criptocoin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marangoz.criptocoin.adapter.HomeAdapter
import com.marangoz.criptocoin.adapter.SaveAdapter
import com.marangoz.criptocoin.databinding.FragmentSaveBinding
import com.marangoz.criptocoin.model.RoomCripto
import com.marangoz.criptocoin.room.CriptoDaoo
import com.marangoz.criptocoin.room.CriptoDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SaveFragment : Fragment() {
   private lateinit var binding : FragmentSaveBinding
    private val adap: SaveAdapter by lazy { SaveAdapter(requireActivity(),viewModel) }
    private val db: CriptoDataBase by lazy { CriptoDataBase.accsessDatabase(requireActivity())!! }
    private val mDao: CriptoDaoo by lazy { db.getCriptoDao() }

    private val viewModel by lazy {
        ViewModelProvider(this, SaveFragmentViewModelFactory(mDao))[SaveFragmentViewModel::class.java]
    }
   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       binding = FragmentSaveBinding.inflate(inflater,container,false)



       binding.rv.apply {
           setHasFixedSize(true)
           layoutManager = LinearLayoutManager(requireActivity())
           adapter = adap
       }

       viewModel.roomList.observe(requireActivity(), Observer {
           adap.setList(it)
       })

       viewModel.getData()





        return binding.root
    }


}