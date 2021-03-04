package am.codeschool.theguardianapi.ui.descriptionfragment

import am.codeschool.theguardianapi.databinding.FragmentDescriptionBinding
import am.codeschool.theguardianapi.injection.Injection
import am.codeschool.theguardianapi.ui.base.BaseFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class DescriptionFragment : BaseFragment() {
    private lateinit var descriptionBinding: FragmentDescriptionBinding
    private lateinit var decsViewModel: DescViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        decsViewModel = ViewModelProvider(this, Injection.providerDescModelFactory()!!).get(
            DescViewModel::class.java
        )

        val bundle = this.arguments
        if (bundle != null) {
            decsViewModel.loadPathData(bundle.getString("url")!!)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        descriptionBinding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)

        return descriptionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initView()
    }

    override fun initView() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun observeLiveData() {
        decsViewModel._getSectionData.observe(viewLifecycleOwner, {
            if (it != null) {
                descriptionBinding.headLine.text = it.response.content.fields.headline
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(it.response.content.fields.thumbnail)
                    .into(descriptionBinding.thumbnailDesc)
                descriptionBinding.bodyDesc.settings.javaScriptEnabled = true
                descriptionBinding.bodyDesc.loadDataWithBaseURL(
                    null,
                    "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + it.response.content.fields.body,
                    "text/html; charset=utf-8",
                    "UTF-8",
                    null
                )
                hideLoader()
            }
        })
    }
}