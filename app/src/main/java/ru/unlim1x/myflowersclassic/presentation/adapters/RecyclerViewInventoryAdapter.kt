package ru.unlim1x.myflowersclassic.presentation.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.data.enteties.FlowerInventory
import ru.unlim1x.myflowersclassic.domain.enteties.Tool
import ru.unlim1x.myflowersclassic.presentation.InventoryListener

class RecyclerViewInventoryAdapter(private val listInventory: List<FlowerInventory>) :
    RecyclerView.Adapter<RecyclerViewInventoryAdapter.ViewHolder>(), View.OnLongClickListener {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView = itemView.findViewById<TextView>(R.id.flower_inventory_quantity_text)
        var imageView = itemView.findViewById<ImageView>(R.id.flower_inventory_image)
        var radio = itemView.findViewById<RadioButton>(R.id.flower_choose_radio_button)
        var whole = itemView.findViewById<CardView>(R.id.flower_card_inventory)
        var id_text = itemView.findViewById<TextView>(R.id.flower_inventory_name_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val a = LayoutInflater.from(parent.context).inflate(R.layout.inventory_flower_item, parent, false)
        return ViewHolder(a)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val flower = listInventory[position]
        holder.textView.text = "Количество: ${flower.quantity}"
        holder.imageView.load(ru.unlim1x.myflowersclassic.data.Strings.PICS_FOLDER +
                flower.id +
                ru.unlim1x.myflowersclassic.data.Strings.PICS_FORMAT_JPG) {
            crossfade(true)
            transformations(RoundedCornersTransformation(20f))
            size(100,100)
        }

        holder.id_text.apply {
            text = "${flower.id}"
            visibility = View.INVISIBLE
        }

        holder.radio.visibility = View.GONE
        holder.whole.setOnLongClickListener(this)


    }

    override fun getItemCount(): Int {
        return listInventory.size
    }

    override fun onLongClick(v: View?): Boolean {
        val shadowBuilder = View.DragShadowBuilder(v)
        val meta = ClipData.newPlainText("id", v?.findViewById<TextView>(R.id.flower_inventory_name_text)?.text)
        v?.startDragAndDrop(meta, shadowBuilder, v, 0)
        return true
    }
}