package com.example.myapplication.fragments.list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Diary
import com.example.myapplication.data.DiaryViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemAdapter :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var diaryList= emptyList<Diary>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_diary_view,
                parent,
                false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = diaryList[position]
        val context=holder.itemView.context
        holder.itemView.findViewById<TextView>(R.id.date_entry).text=currentitem.date
        holder.itemView.findViewById<TextView>(R.id.text_entry).text=currentitem.desc
        when (currentitem.emoji) {
            1 -> {
                holder.itemView.findViewById<ImageView>(R.id.emoji).setImageResource(R.drawable.ic_happy)
            }
            2 -> {
                holder.itemView.findViewById<ImageView>(R.id.emoji).setImageResource(R.drawable.ic_relieved)
            }
            3 -> {
                holder.itemView.findViewById<ImageView>(R.id.emoji).setImageResource(R.drawable.ic_angry)
            }
            4 -> {
                holder.itemView.findViewById<ImageView>(R.id.emoji).setImageResource(R.drawable.ic_cry)
            }
        }

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.cardViewItem.setBackgroundColor(ContextCompat.getColor(
                context,
                R.color.card_view_dark
            ))
        } else {
            holder.cardViewItem.setBackgroundColor(ContextCompat.getColor(
                context,
                R.color.card_view_light
            ))
        }
        //To Update and Delete
        holder.itemView.findViewById<ImageView>(R.id.deleteoredit).setOnClickListener{
            val action=Diary_FragmentDirections.actionDiaryFragmentToUpdateFragment(currentitem)
            holder.itemView.findNavController().navigate(action)
        }


    }



    fun setData(diary:List<Diary>){
        this.diaryList=diary
        notifyDataSetChanged()
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return diaryList.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val date: TextView = view.findViewById(R.id.date_entry)
        val cardViewItem: CardView = view.findViewById(R.id.card_view_item)
        val text: TextView =view.findViewById(R.id.text_entry)
    }
}