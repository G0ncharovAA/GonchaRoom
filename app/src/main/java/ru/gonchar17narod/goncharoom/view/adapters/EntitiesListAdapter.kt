package ru.gonchar17narod.goncharoom.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gonchar17narod.goncharoom.business.Entity
import ru.gonchar17narod.goncharoom.R

class EntitiesListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<EntitiesListAdapter.EntityViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var entities = emptyList<Entity>() // Cached copy of entities

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entityItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val itemView = inflater.inflate(R.layout.entity_item, parent, false)
        return EntityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val current = entities[position]
        holder.entityItemView.text = current.label
    }

    internal fun setEntities(entities: List<Entity>) {
        this.entities = entities
        notifyDataSetChanged()
    }

    override fun getItemCount() = entities.size
}