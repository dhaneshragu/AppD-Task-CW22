package com.example.myapplication.fragments.update

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.data.Diary
import com.example.myapplication.data.DiaryViewModel

class updateFragment : Fragment() {
    private val args by navArgs<updateFragmentArgs>()
    var updatedemojival=1
    private lateinit var mDiaryViewModel: DiaryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update2, container, false)

        mDiaryViewModel=ViewModelProvider(this).get(DiaryViewModel::class.java)


        view.findViewById<EditText>(R.id.entry_edit).setText(args.currentDiary.desc)
        //TODO:EMOJI
        val happy_mood=view.findViewById<ImageView>(R.id.happy_edit)
        val relieved_mood=view.findViewById<ImageView>(R.id.relieved_edit)
        val angry_mood=view.findViewById<ImageView>(R.id.angry_edit)
        val cry_mood=view.findViewById<ImageView>(R.id.cry_edit)
        when (args.currentDiary.emoji) {
            1 -> {
                happy_mood.setImageResource(R.drawable.ic_happy_selected)
                relieved_mood.setImageResource(R.drawable.ic_relieved)
                angry_mood.setImageResource(R.drawable.ic_angry)
                cry_mood.setImageResource(R.drawable.ic_cry)
                updatedemojival=1
            }
            2 -> {
                happy_mood.setImageResource(R.drawable.ic_happy)
                relieved_mood.setImageResource(R.drawable.ic_relieved_selected)
                angry_mood.setImageResource(R.drawable.ic_angry)
                cry_mood.setImageResource(R.drawable.ic_cry)
                updatedemojival=2
            }
            3 -> {
                happy_mood.setImageResource(R.drawable.ic_happy)
                relieved_mood.setImageResource(R.drawable.ic_relieved)
                angry_mood.setImageResource(R.drawable.ic_angry_selected)
                cry_mood.setImageResource(R.drawable.ic_cry)
                updatedemojival=3
            }
            4 -> {
                happy_mood.setImageResource(R.drawable.ic_happy)
                relieved_mood.setImageResource(R.drawable.ic_relieved)
                angry_mood.setImageResource(R.drawable.ic_angry)
                cry_mood.setImageResource(R.drawable.ic_cry_selected)
                updatedemojival=4
            }
        }

        happy_mood.setOnClickListener{
            updatedemojival=1
            happy_mood.setImageResource(R.drawable.ic_happy_selected)
            relieved_mood.setImageResource(R.drawable.ic_relieved)
            angry_mood.setImageResource(R.drawable.ic_angry)
            cry_mood.setImageResource(R.drawable.ic_cry)
        }

        relieved_mood.setOnClickListener{
            updatedemojival=2
            happy_mood.setImageResource(R.drawable.ic_happy)
            relieved_mood.setImageResource(R.drawable.ic_relieved_selected)
            angry_mood.setImageResource(R.drawable.ic_angry)
            cry_mood.setImageResource(R.drawable.ic_cry)
        }

        angry_mood.setOnClickListener{
            updatedemojival=3
            happy_mood.setImageResource(R.drawable.ic_happy)
            relieved_mood.setImageResource(R.drawable.ic_relieved)
            angry_mood.setImageResource(R.drawable.ic_angry_selected)
            cry_mood.setImageResource(R.drawable.ic_cry)
        }

        cry_mood.setOnClickListener{
            updatedemojival=4
            happy_mood.setImageResource(R.drawable.ic_happy)
            relieved_mood.setImageResource(R.drawable.ic_relieved)
            angry_mood.setImageResource(R.drawable.ic_angry)
            cry_mood.setImageResource(R.drawable.ic_cry_selected)
        }

        view.findViewById<Button>(R.id.submit_edit).setOnClickListener{
            updateItems()
        }

        view.findViewById<ImageButton>(R.id.delete_button).setOnClickListener{
            deleteDiary()
        }

        return view
    }

    private fun deleteDiary() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
                _,_->
            mDiaryViewModel.deleteDiary(args.currentDiary)
            Toast.makeText(requireContext(),"Your feelings are deleted",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_diary_Fragment)
        }
        builder.setNegativeButton("No"){
                _,_->
        }
        builder.setTitle("Deleter")
        builder.setMessage("Are you sure you want to delete your feelings?")
        builder.create().show()
    }

    private fun updateItems(){
        val descri= view?.findViewById<EditText>(R.id.entry_edit)?.text.toString()
        //As of now Dates cant be edited
        //val date="05/06/2022"
        val emoji_selected=updatedemojival
        if(inputCheck(descri)){
            val updatedDiary= Diary(args.currentDiary.id,args.currentDiary.date,descri,emoji_selected)
            mDiaryViewModel.updateDiary(updatedDiary)
            //Navigation to back
            findNavController().navigate(R.id.action_updateFragment_to_diary_Fragment)
            Toast.makeText(requireContext(),"Updated your feelings!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(),"Ah huh feelings can't be blank",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(diary_desc:String):Boolean{
        return !(TextUtils.isEmpty(diary_desc))
    }

}