package org.tsoka.eventcity.general.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import org.tsoka.eventcity.general.common.EventClickListener
import org.tsoka.eventcity.general.common.EventsDiffCallback
import org.tsoka.eventcity.general.common.FavoriteFabClickListener
import org.tsoka.eventcity.general.databinding.ItemCardFavoriteEventBinding
import org.tsoka.eventcity.general.event.Event
import org.tsoka.eventcity.general.favorite.FavoriteEventViewHolder

class SearchPagedListAdapter : PagedListAdapter<Event, FavoriteEventViewHolder>(EventsDiffCallback()) {

    var onEventClick: EventClickListener? = null
    var onFavFabClick: FavoriteFabClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        val binding = ItemCardFavoriteEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        val event = getItem(position)
        if (event != null) {
            holder.apply {
                bind(event, position, "")
                eventClickListener = onEventClick
                favFabClickListener = onFavFabClick
            }
        }
    }

    fun clear() {
        this.submitList(null)
    }
}
