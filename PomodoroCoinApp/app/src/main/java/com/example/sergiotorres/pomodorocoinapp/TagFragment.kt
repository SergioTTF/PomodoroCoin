package com.example.sergiotorres.pomodorocoinapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_tag.*
import kotlinx.android.synthetic.main.fragment_tag.view.*


class TagFragment : Fragment() {
    private var myAdapter:TagAdapter? = null
    private var myManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        myAdapter = TagAdapter(getTagList(), context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tag, container, false)
        view.recyclerView.layoutManager = myManager
        view.recyclerView.adapter = myAdapter
        return view
    }



    private fun getTagList(): List<Tag> {
        return listOf(Tag("Study",3), Tag("Meditate",6), Tag("Work",2))
    }
}
