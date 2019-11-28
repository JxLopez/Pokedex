package com.jxlopez.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jxlopez.pokedex.BuildConfig
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.data.database.entity.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter (
    val context: Context,
    private val items: ArrayList<Pokemon>?) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var lastPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        if (position > lastPosition) {
            holder.llContent?.animation = AnimationUtils.loadAnimation(
                context,
                R.anim.abc_slide_in_bottom
            )
            lastPosition = position
        }
        holder.tvName?.text = items?.get(position)?.name
        Picasso.get()
            .load(BuildConfig.IMAGE_URL.format(items?.get(position)?.id))
            .tag(context)
            .into(holder.ivImage)
    }

    override fun getItemCount(): Int {
        return items?.size.let {it }?: 0
    }

    fun addData(items: List<Pokemon>) {
        this.items?.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.items?.clear()
    }
    fun setData(items: List<Pokemon>) {
        clearData()
        this.items?.addAll(items)
        notifyDataSetChanged()
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val llContent: LinearLayout? = itemView.itemContentLinearLayout
        val tvName: TextView? = itemView.itemNombreTextView
        val ivImage: ImageView? = itemView.itemPokemonImageView
    }
}