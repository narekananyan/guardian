package am.codeschool.theguardianapi.ui.extatons

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.ui.base.BaseActivity
import android.annotation.SuppressLint
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

@SuppressLint("ResourceType")
fun BaseActivity.addFragmentNoBackStack(
    fragment: Fragment, @LayoutRes layoutResId: Int = R.id.frameConteiner,
    tag: String? = null
) {
    val trans = supportFragmentManager.beginTransaction()
    trans.add(layoutResId, fragment,tag)
    trans.commit()
}   @SuppressLint("ResourceType")
fun BaseActivity.replaceFragment(fragment: Fragment, @LayoutRes layoutResId: Int = R.id.frameConteiner,
                                      tag: String? = null){
    val trans = supportFragmentManager.beginTransaction()
    trans.replace(layoutResId, fragment)
    trans.commit()
}
@SuppressLint("ResourceType")
fun BaseActivity.addFragmentBackStack(
    fragment: Fragment, @LayoutRes layoutResId: Int = R.id.frameConteiner,
    tag: String? = null
) {
    val trans = supportFragmentManager.beginTransaction()
    trans.addToBackStack(null)
    trans.add(layoutResId, fragment, tag)
    trans.commit()
}