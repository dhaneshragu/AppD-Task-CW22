package com.example.myapplication.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Diary
import com.example.myapplication.data.DiaryViewModel
import java.util.*

class AddEntryFragment : Fragment() {

    private lateinit var mDiaryViewModel:DiaryViewModel
    var emojival='1'
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_add_entry, container, false)
        mDiaryViewModel=ViewModelProvider(this).get(DiaryViewModel::class.java)

        view.findViewById<ImageView>(R.id.happy).setOnClickListener{
            emojival='1'
            view.findViewById<ImageView>(R.id.happy).setImageResource(R.drawable.ic_happy_selected)
            view.findViewById<ImageView>(R.id.relieved).setImageResource(R.drawable.ic_relieved)
            view.findViewById<ImageView>(R.id.angry).setImageResource(R.drawable.ic_angry)
            view.findViewById<ImageView>(R.id.cry).setImageResource(R.drawable.ic_cry)
        }
        view.findViewById<ImageView>(R.id.relieved).setOnClickListener{
            emojival='2'
            view.findViewById<ImageView>(R.id.happy).setImageResource(R.drawable.ic_happy)
            view.findViewById<ImageView>(R.id.relieved).setImageResource(R.drawable.ic_relieved_selected)
            view.findViewById<ImageView>(R.id.angry).setImageResource(R.drawable.ic_angry)
            view.findViewById<ImageView>(R.id.cry).setImageResource(R.drawable.ic_cry)
        }
        view.findViewById<ImageView>(R.id.angry).setOnClickListener{
            emojival='3'
            view.findViewById<ImageView>(R.id.happy).setImageResource(R.drawable.ic_happy)
            view.findViewById<ImageView>(R.id.relieved).setImageResource(R.drawable.ic_relieved)
            view.findViewById<ImageView>(R.id.angry).setImageResource(R.drawable.ic_angry_selected)
            view.findViewById<ImageView>(R.id.cry).setImageResource(R.drawable.ic_cry)
        }
        view.findViewById<ImageView>(R.id.cry).setOnClickListener{
            emojival='4'
            view.findViewById<ImageView>(R.id.happy).setImageResource(R.drawable.ic_happy)
            view.findViewById<ImageView>(R.id.relieved).setImageResource(R.drawable.ic_relieved)
            view.findViewById<ImageView>(R.id.angry).setImageResource(R.drawable.ic_angry)
            view.findViewById<ImageView>(R.id.cry).setImageResource(R.drawable.ic_cry_selected)
        }

        view.findViewById<Button>(R.id.submit).setOnClickListener{
            //Toast.makeText(requireContext(),"Your feelings are recorded!",Toast.LENGTH_LONG).show()
            //val diary_description= view?.findViewById<EditText>(R.id.entry)?.text.toString()
            //Log.i("hello",diary_description)
            insertDatatoDatabase() //pass emojival in this function
        }


        return view
    }

    private fun insertDatatoDatabase() {
        val diary_description= view?.findViewById<EditText>(R.id.entry)?.text.toString()
        //Log.i("hello",diary_description)
        val date_today=getdate()
        val diary_mood=emojival
        //Log.i("hi",diary_mood.toString())
        //Toast.makeText(requireContext(),"Your feelings are recorded!",Toast.LENGTH_LONG).show()
        if(inputCheck(diary_description)){
            //Create Diary Object and pass into it
            val diary= Diary(0,date_today,diary_description,Integer.parseInt(emojival.toString()))

            //Add to Database
            mDiaryViewModel.addDiary(diary)

            //Display A toast message
            Toast.makeText(requireContext(),"Your feelings are recorded!",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addEntryFragment_to_diary_Fragment)

            //Navigate Back to homepage
        }
        else{
            Toast.makeText(requireContext(),"Uh Oh! Your thoughts can't be empty!",Toast.LENGTH_LONG).show()
        }
    }

    private fun getdate(): String {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        val month = (Calendar.getInstance().get(Calendar.MONTH)+1).toString()
        val year = Calendar.getInstance().get(Calendar.YEAR).toString()
        return " $day-$month-$year "

    }

    private fun inputCheck(diary_desc:String):Boolean{
        return !(TextUtils.isEmpty(diary_desc))
    }
}
