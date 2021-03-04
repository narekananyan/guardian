package am.codeschool.theguardianapi.ui.adapters

import am.codeschool.theguardianapi.ui.content.ContentFragment
import am.codeschool.theguardianapi.ui.favourite.FavouriteFragment
import am.codeschool.theguardianapi.ui.section.SectionFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalStateException

class FragmentAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    private var contentFragment: ContentFragment =ContentFragment()
    private var sectionFragment: SectionFragment = SectionFragment()
    override fun getItemCount()=3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                sectionFragment
            }
            1 -> {
                contentFragment
            }
            2 -> {
                FavouriteFragment()
            }
            else -> throw IllegalStateException()
        }
        }

}