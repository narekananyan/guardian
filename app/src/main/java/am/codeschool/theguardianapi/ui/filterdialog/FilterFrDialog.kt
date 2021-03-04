package am.codeschool.theguardianapi.ui.filterdialog

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.databinding.FragmentFilterBinding
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import java.util.*
import kotlin.collections.HashMap

class FilterFrDialog : DialogFragment() {
    lateinit var filterBinding: FragmentFilterBinding
    lateinit var c: Calendar
    private var listQuery: MutableMap<String, String> = HashMap()
    var year: Int = 0
    var day: Int = 0
    var month: Int = 0
    lateinit var listener:OnSearchStartListener

    interface OnSearchStartListener{
        fun searchStart(listQuery: MutableMap<String, String>)
    }
    fun addSearchStartListener(listener:OnSearchStartListener){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        filterBinding = FragmentFilterBinding.inflate(inflater,container,false)
        return filterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
    }
    fun initView() {

        c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)

    }
    fun initListeners() {

        filterBinding.searchButton.setOnClickListener {
            if (filterBinding.searchView.query.trim().isNotEmpty()){
                listQuery["q"]=filterBinding.searchView.query.toString()
            }
          listener.searchStart(listQuery)
            Log.d("Dailog","Dial")
            this.dismiss()
        }

        filterBinding.pickFromBtn.setOnClickListener {
            val dpd = DatePickerDialog(requireActivity(),
                R.style.DialogTheme, { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date
                filterBinding.pickFromBtn.text = ("$dayOfMonth.$monthOfYear.$year")
            }, year, month, day)

            dpd.show()
        }
        filterBinding.pickToBtn.setOnClickListener {
            val dpd = DatePickerDialog(requireActivity(),R.style.DialogTheme, { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date
                filterBinding.pickToBtn.text = ("$dayOfMonth.$monthOfYear.$year")
            }, year, month, day)
            dpd.show()
        }

        filterBinding.orderById.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val key = "order-by"
                when(position){
                    0->{
                        listQuery.remove(key)
                    }
                    1->{
                        listQuery[key]=filterBinding.orderById.selectedItem.toString()
                    }
                    2->{
                        listQuery[key]=filterBinding.orderById.selectedItem.toString()
                    }
                    3->{
                        listQuery[key]=filterBinding.orderById.selectedItem.toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        filterBinding.showElementsID.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val key = "show-elements"
                when (position) {
                    0 -> {
                        listQuery.remove(key)
                    }
                    1 -> {
                        listQuery[key] = filterBinding.showElementsID.selectedItem.toString()
                    }
                    2 -> {
                        listQuery[key] = filterBinding.showElementsID.selectedItem.toString()
                    }
                    3 -> {
                        listQuery[key] = filterBinding.showElementsID.selectedItem.toString()
                    }
                    4->{
                        listQuery[key] = filterBinding.showElementsID.selectedItem.toString()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        filterBinding.useDateId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val key = "use-date"
                when(position){
                    0->{
                        listQuery.remove(key)
                    }
                    1->{
                        listQuery[key]=filterBinding.useDateId.selectedItem.toString()
                    }
                    2->{
                        listQuery[key]=filterBinding.useDateId.selectedItem.toString()
                    }
                    3->{
                        listQuery[key]=filterBinding.useDateId.selectedItem.toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    companion object {
        fun instance() = FilterFrDialog()
    }

}