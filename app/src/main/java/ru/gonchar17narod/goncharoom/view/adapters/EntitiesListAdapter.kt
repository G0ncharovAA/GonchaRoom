package ru.gonchar17narod.goncharoom.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.entity_item.view.*
import ru.gonchar17narod.goncharoom.R
import ru.gonchar17narod.goncharoom.business.Entity

class EntitiesListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<EntitiesListAdapter.EntityViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entities = emptyList<Entity>() // Cached copy of entities

    inner class EntityViewHolder(
        itemView: View,
        val entityItemView: TextView = itemView.textView
    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EntityViewHolder(
            inflater.inflate(R.layout.entity_item, parent, false)
        )

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.entityItemView.text = entities[position].label
    }

    internal fun setEntities(entities: List<Entity>) {
        this.entities = entities
        notifyDataSetChanged()
    }

    override fun getItemCount() = entities.size
}