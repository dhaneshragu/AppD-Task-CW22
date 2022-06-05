package com.example.myapplication.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.DiaryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Diary_Fragment : Fragment() {

    private lateinit var mDiaryViewModel: DiaryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_diary_, container, false)

        //Recycler View initialisations
        val adapter=ItemAdapter()
        val recyclerview=view.findViewById<RecyclerView>(R.id.recycler_view_items)
        recyclerview.adapter=adapter
        recyclerview.layoutManager=LinearLayoutManager(requireContext())

        // View Model
        mDiaryViewModel=ViewModelProvider(this).get(DiaryViewModel::class.java)
        mDiaryViewModel.readAllData.observe(viewLifecycleOwner, Observer { diary->adapter.setData(diary) })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{
            findNavController().navigate(R.id.action_diary_Fragment_to_addEntryFragment)
        }
        return view
    }
}