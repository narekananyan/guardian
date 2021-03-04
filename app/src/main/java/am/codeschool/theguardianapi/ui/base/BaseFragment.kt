package am.codeschool.theguardianapi.ui.base

import am.codeschool.theguardianapi.ui.extatons.addFragmentBackStack
import am.codeschool.theguardianapi.ui.extatons.addFragmentNoBackStack
import am.codeschool.theguardianapi.ui.extatons.replaceFragment
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract fun initView()
    abstract fun observeLiveData()
    fun showLoader(){
        (requireActivity() as BaseActivity).showLoader()
    }
    fun hideLoader(){
        (requireActivity() as BaseActivity).hideLoader()
    }
    fun addFrNoBackStack(fragment: Fragment){
        (requireActivity() as BaseActivity).addFragmentNoBackStack(fragment)
    }
    fun addFrBackStack(fragment: Fragment){
        (requireActivity() as BaseActivity).addFragmentBackStack(fragment)
    }
    fun replaceFragment(fragment: Fragment){
        (requireActivity() as BaseActivity).replaceFragment(fragment)
    }
}