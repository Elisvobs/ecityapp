package org.tsoka.eventcity.general.event.similarevent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import org.tsoka.eventcity.general.common.EventClickListener
import org.tsoka.eventcity.general.common.EventsDiffCallback
import org.tsoka.eventcity.general.common.FavoriteFabClickListener
import org.tsoka.eventcity.general.databinding.ItemCardSimilarEventsBinding
import org.tsoka.eventcity.general.event.Event

class SimilarEventsListAdapter : PagedListAdapter<Event, SimilarEventViewHolder>(EventsDiffCallback()) {

    var onEventClick: EventClickListener? = null
    var onFavFabClick: FavoriteFabClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarEventViewHolder {
        val binding = ItemCardSimilarEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarEventViewHolder, position: Int) {
        val event = getItem(position)
        if (event != null) {
            holder.apply {
                bind(event, position)
                eventClickListener = onEventClick
                favFabClickListener = onFavFabClick
            }
        }
    }

    fun clear() {
        this.submitList(null)
    }
}
