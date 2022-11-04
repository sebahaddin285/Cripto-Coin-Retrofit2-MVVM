package com.marangoz.criptocoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.criptocoin.R
import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.repository.RoomRepo
import com.marangoz.criptocoin.room.CriptoDaoo
import com.marangoz.criptocoin.room.CriptoDataBase
import com.marangoz.criptocoin.ui.SaveFragment
import com.marangoz.criptocoin.ui.SaveFragmentViewModel
import com.marangoz.criptocoin.ui.SaveFragmentViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeAdapter(val mContext: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolderClass>() {
    var criptoList: List<Cripto> = listOf()
    private val db: CriptoDataBase by lazy { CriptoDataBase.accsessDatabase(mContext)!! }
    private val mDao: CriptoDaoo by lazy { db.getCriptoDao() }
    val repo = RoomRepo(mDao)

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView
        val priceText: TextView
        val favoriButton: ImageView

        init {
            nameText = view.findViewById(R.id.criptoNameText)
            priceText = view.findViewById(R.id.criptoPriceText)
            favoriButton = view.findViewById(R.id.favoriButton)
        }

    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val cripto = criptoList[position]

        holder.apply {
            nameText.text = cripto.asset_id

            priceText.text = cripto.price_usd.toString()
        }

        holder.favoriButton.setOnClickListener() {
            CoroutineScope(Dispatchers.Main).launch {
                var count = 0
                repo.getCriptoRoom().forEach {
                    if (it.asset_id == cripto.asset_id) {
                        count = 1
                    }
                }
                if (count != 1) {
                    repo.insertCriptoRoom(cripto)
                    Toast.makeText(mContext, "Succesfully", Toast.LENGTH_SHORT).show()
                }


            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun getItemCount(): Int {
        return criptoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Cripto>) {
        criptoList = list
        notifyDataSetChanged()
    }


}
