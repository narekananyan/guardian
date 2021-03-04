package am.codeschool.theguardianapi.injection

import am.codeschool.theguardian.model.retrofit.Common
import am.codeschool.theguardianapi.model.data.impl.SearchRepositoryImpl
import am.codeschool.theguardianapi.ui.content.ContentFactory
import am.codeschool.theguardianapi.ui.descriptionfragment.DescModelFactory
import am.codeschool.theguardianapi.ui.section.SectionFactory
import androidx.lifecycle.ViewModelProvider

object Injection {
    private val searchRepositoryImpl: SearchRepositoryImpl? = Common.retrofitServices?.let {
        SearchRepositoryImpl(it)
    }
    private val descModelFactory: DescModelFactory? = searchRepositoryImpl?.let {
        DescModelFactory(it)
    }

    fun getSearchRepo(): SearchRepositoryImpl {
        return searchRepositoryImpl!!
    }
    fun providerDescModelFactory(): ViewModelProvider.Factory? {
        return descModelFactory
    }

//    private val sectionFactory: SectionFactory = searchRepositoryImpl?.let {
//     }!!

//    fun sectionFactory(): ViewModelProvider.Factory {
//        return sectionFactory
//    }

//    private val contentFactory: ContentFactory = searchRepositoryImpl?.let {
//        ContentFactory(it)
//    }!!
//
//    fun contentFactory(): ViewModelProvider.Factory {
//        return contentFactory
//    }

}
