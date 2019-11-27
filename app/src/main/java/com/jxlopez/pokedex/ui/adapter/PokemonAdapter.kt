package com.jxlopez.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.data.network.Response.Results
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter (
    val context: Context,
    private val items: List<Results>?) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.tvName?.text = items?.get(position)?.name
    }

    override fun getItemCount(): Int {
        return items?.size.let {it }?: 0
    }
    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView? = itemView.itemNombreTextView
        val ivImage: ImageView? = itemView.itemPokemonImageView
    }
}