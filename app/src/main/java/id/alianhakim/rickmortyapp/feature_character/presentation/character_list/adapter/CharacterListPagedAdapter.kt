package id.alianhakim.rickmortyapp.feature_character.presentation.character_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.alianhakim.rickmortyapp.databinding.CharacterItemBinding
import id.alianhakim.rickmortyapp.feature_character.domain.model.Character

class CharacterListPagedAdapter :
    PagingDataAdapter<Character, CharacterListPagedAdapter.CharacterListViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CharacterListViewHolder(
        val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            avatar.load(
                data = currentItem?.image
            ) {
                crossfade(true)
                build()
            }
            name.text = currentItem?.name
            cvItem.setOnClickListener {
                onItemClickListener?.let {
                    if (currentItem != null) {
                        it(currentItem)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((Character) -> Unit)? = null

    fun setOnItemClickListener(listener: (Character) -> Unit) {
        onItemClickListener = listener
    }
}