package com.example.marviecources

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marviecources.databinding.LayoutNewCourcesBinding
import okhttp3.internal.toHexString

class SecondAdapter :
    ListAdapter<NewCourse, SecondAdapter.NewCourseItemViewHolder>(MainDiffUtil()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SecondAdapter.NewCourseItemViewHolder {
        return NewCourseItemViewHolder(
            LayoutNewCourcesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: SecondAdapter.NewCourseItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    inner class NewCourseItemViewHolder(
        private val binding: LayoutNewCourcesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: NewCourse) {
            val mainColor = item.main_color


            binding.root.backgroundTintList = ColorStateList.valueOf(
                getRequiredColor(
                    mainColor,
                    100
                )
            )

            binding.titleTextView.text = item.title
            binding.questionText.text = item.question
            binding.timeTextView.text = "${item.duration.toInt() / 3600} min"

            if (item.icon_type == "settings"){
                Glide.with(itemView.context)
                    .load(R.drawable.ic_setting)
                    .into(binding.iconImageView)
            }else{
                Glide.with(itemView.context)
                    .load(R.drawable.ic_card)
                    .into(binding.iconImageView)
            }

        }
    }


    private fun getRequiredColor(mainColor: String, percent: Int): Int {
        val color = Color.parseColor("#${percent.toHexString()}$mainColor")
//            val colorWithAlpha30 = ColorUtils.setAlphaComponent(
//                color,
////                Integer.decode()
//            )
        return color
    }

}