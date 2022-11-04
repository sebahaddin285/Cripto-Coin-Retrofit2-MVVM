package com.marangoz.criptocoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.criptocoin.R
import com.marangoz.criptocoin.model.Cripto
import com.marangoz.criptocoin.model.RoomCripto
import com.marangoz.criptocoin.ui.SaveFragmentViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class SaveAdapter (val mContext: Context,val viewmodelRoom : SaveFragmentViewModel) : RecyclerView.Adapter<SaveAdapter.ViewHolderClass>() {
    var criptoRoomList: List<RoomCripto> = listOf()

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
        val cripto  = criptoRoomList[position]

        holder.apply {
            nameText.text = cripto.asset_id
            priceText.text = cripto.price_usd.toString()
        }

        holder.favoriButton.setOnClickListener(){
            viewmodelRoom.deleteData(cripto)
            Toast.makeText(mContext,"Succesfully", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        var design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun getItemCount(): Int {
        return criptoRoomList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list : List<RoomCripto>){
        criptoRoomList = list
        notifyDataSetChanged()
    }


}