package ru.unlim1x.myflowersclassic.presentation.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ru.unlim1x.myflowersclassic.R
import ru.unlim1x.myflowersclassic.app.AdapterListener
import ru.unlim1x.myflowersclassic.domain.enteties.FlowerShort

class RecyclerViewFlowersAdapter(
    private var flowersList: List<FlowerShort?>?,
    private var adapterListener: AdapterListener,

    ) :
    RecyclerView.Adapter<RecyclerViewFlowersAdapter.ViewHolder>() {

    private var image: Pair<Int?, Drawable?>? = Pair(null, null)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var flower_name: TextView = itemView.findViewById(R.id.flower_name)
        var flower_watering: TextView = itemView.findViewById(R.id.flower_watering)
        var flower_description: TextView = itemView.findViewById(R.id.flower_description)
        var flower_image: ImageView = itemView.findViewById(R.id.flower_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.flower_card_item, parent, false)
        return ViewHolder(card)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (flowersList != null) {
            holder.flower_name.text = flowersList!![position]?.name
            Log.e("RVA Flower, position", "${flowersList!![position]?.name}, $position")
            val watering = StringBuilder().append("Полив: ")

            val period = flowersList!![position]?.watering
            watering.append(period)
            if (period == 2 || period == 3 || period == 4)
                watering.append(" раза")
            else if (period != null)
                watering.append(" раз")

            when (flowersList!![position]?.period) {
                "месяц" -> watering.append(" в месяц")
                "неделя" -> watering.append(" в неделю")
                "день" -> watering.append(" в день")
                else -> {
                    watering.append(" не требует")
                }
            }
            holder.flower_watering.text = watering.toString()
            holder.flower_description.text = flowersList!![position]?.id.toString()

            if (flowersList!![position]?.id != null && holder.flower_image.drawable == null) {
                holder.flower_image.load(ru.unlim1x.myflowersclassic.data.Strings.PICS_FOLDER +
                        flowersList!![position]?.id +
                        ru.unlim1x.myflowersclassic.data.Strings.PICS_FORMAT_JPG){
                    crossfade(true)
                    transformations(RoundedCornersTransformation(20f))
                }

            }

            if(flowersList!!.size == position+1 )
                adapterListener.allElementsLoaded()

        }

    }

    fun setList(flowersList: List<FlowerShort?>?) {
        this.flowersList = flowersList
    }


    override fun getItemCount(): Int {
        return if (flowersList != null) {
            //Log.e("RVA", "item count called")
            flowersList!!.size
        } else
            0
    }

}