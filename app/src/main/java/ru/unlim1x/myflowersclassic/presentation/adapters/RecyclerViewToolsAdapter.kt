package ru.unlim1x.myflowersclassic.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.data.enteties.FlowerInventory
import ru.unlim1x.myflowersclassic.domain.enteties.Tool

class RecyclerViewToolsAdapter(val listTool: List<Tool>) :
    RecyclerView.Adapter<RecyclerViewToolsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.achievement_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val a = LayoutInflater.from(parent.context).inflate(R.layout.achievement_card_item, parent, false)
        return ViewHolder(a)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leyka = R.drawable.leyka
        val lopata = R.drawable.shovel
        val udobr = R.drawable.udobrenie
        var decision = 0
        when(position){
            0 -> decision = leyka
            1-> decision = lopata
            2 -> decision = udobr
        }
        holder.image.load(decision) {
            crossfade(true)
            transformations(RoundedCornersTransformation(20f))
            size(100,100)
        }
    }

    override fun getItemCount(): Int {
        return listTool.size
    }
}