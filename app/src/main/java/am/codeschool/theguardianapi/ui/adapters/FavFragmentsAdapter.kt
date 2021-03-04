package am.codeschool.theguardianapi.ui.adapters

import am.codeschool.theguardianapi.ui.favourite.subFragments.FavoriteContentFr
import am.codeschool.theguardianapi.ui.favourite.subFragments.FavoriteSectionFr
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalStateException

class FavFragmentsAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2


    override fun createFragment(position: Int) = when (position) {
        0 -> FavoriteSectionFr.newInstance()
        1 -> FavoriteContentFr.newInstance()
        else -> throw IllegalStateException()
    }

}