package ru.unlim1x.myflowersclassic.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.data.enteties.FlowerInventory
import ru.unlim1x.myflowersclassic.domain.enteties.Tool

class RecyclerViewStoriesAdapter() :
    RecyclerView.Adapter<RecyclerViewStoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val a = LayoutInflater.from(parent.context).inflate(R.layout.achievement_card_item, parent, false)
        return ViewHolder(a)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}