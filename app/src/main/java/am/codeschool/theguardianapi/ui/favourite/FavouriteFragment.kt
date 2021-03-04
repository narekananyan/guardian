package am.codeschool.theguardianapi.ui.favourite

import am.codeschool.theguardianapi.MainActivity
import am.codeschool.theguardianapi.MyApplication
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.databinding.FavouritFragmentBinding
import am.codeschool.theguardianapi.ui.adapters.FavFragmentsAdapter
import am.codeschool.theguardianapi.ui.base.BaseFragment
import am.codeschool.theguardianapi.ui.favourite.subFragments.FavContentFact
import am.codeschool.theguardianapi.ui.favourite.subFragments.FavoriteItemViewModel
import com.google.android.material.tabs.TabLayoutMediator


class FavouriteFragment : BaseFragment(), MainActivity.DeleteAllClickLitenere {
    private lateinit var favAdapter:FavFragmentsAdapter
    private lateinit var fragmentBinding: FavouritFragmentBinding
     private val viewModel: FavouritViewModel by lazy { ViewModelProvider(this,
        FavFragFactory((requireActivity().application as MyApplication).repository)
    ).get(FavouritViewModel::class.java)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FavouritFragmentBinding.inflate(inflater,container,false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    override fun initView(){
        (requireActivity() as MainActivity).addDeleteListener(this)
        val tabNames = resources.getStringArray(R.array.fav_names)
        favAdapter = FavFragmentsAdapter(requireActivity())
        fragmentBinding.favPager.adapter= favAdapter
        TabLayoutMediator(fragmentBinding.favTab,fragmentBinding.favPager){tab,position->
            tab.text = tabNames[position]
        }.attach()
    }

    override fun observeLiveData() {

    }

    companion object {
        fun newInstance() = FavouriteFragment()
    }

    override fun deleteAllFavorites() {
        viewModel.deleteAll()
    }

}