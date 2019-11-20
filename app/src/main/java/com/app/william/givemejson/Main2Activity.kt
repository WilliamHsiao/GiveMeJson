package com.app.william.givemejson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.*


class Main2Activity : AppCompatActivity() {

    private var picAdapter = PicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView.apply {
            layoutManager = FlexboxLayoutManager(context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                alignItems = AlignItems.STRETCH
            }
            adapter = picAdapter
        }

        val viewModel = ViewModelProvider(this).get(Main2ViewModel::class.java)

        viewModel.json.observe(this, Observer {
            picAdapter.setList(it)
        })

    }

}
