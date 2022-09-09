package com.example.marviecources

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marviecources.databinding.LayoutActiveCoursesBinding
import com.example.marviecources.databinding.LayoutNewCourcesBinding
import okhttp3.internal.toHexString

class MainAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(MainDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ListItem.ViewType.NEW_COURSE.ordinal -> NewCourseViewHolder(
                LayoutNewCourcesBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )

            ListItem.ViewType.ACTIVE_COURSE.ordinal -> ActiveCourseViewHolder(
                LayoutActiveCoursesBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalStateException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is NewCourseViewHolder -> holder.bind(item as ListItem.NewCourseItem)
            is ActiveCourseViewHolder -> holder.bid(item as ListItem.ActiveCourseItem)
        }
    }

    inner class NewCourseViewHolder(
        private val binding: LayoutNewCourcesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: ListItem.NewCourseItem) {

        }
    }

    inner class ActiveCourseViewHolder(
        private val binding: LayoutActiveCoursesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")

        fun bid(item: ListItem.ActiveCourseItem) {

            val progressBar = binding.progressBar
            progressBar.progress = item.activeCourse.progress.toInt()

            Glide.with(itemView.context)
                .load(item.activeCourse.image)
                .into(binding.iconImageView)

            binding.timeText.text = item.activeCourse.booking_time
            binding.titleText.text = item.activeCourse.title

            val mainColor = item.activeCourse.main_color

            binding.BookedText.setTextColor(
                getRequiredColor(
                    mainColor,
                    100
                )
            )
            binding.timeText.setTextColor(getRequiredColor(mainColor, 100))

            binding.titleText.setTextColor(
                getRequiredColor(
                    mainColor,
                    item.activeCourse.play_button_color_percent.toInt()
                )
            )

            binding.root.backgroundTintList = ColorStateList.valueOf(
                getRequiredColor(
                    mainColor,
                    item.activeCourse.background_color_percent.toInt()
                )
            )

            binding.progressBar.progressDrawable.setColorFilter(
                getRequiredColor(mainColor, 100),
                PorterDuff.Mode.MULTIPLY
            )

            binding.iconImageView.backgroundTintList = ColorStateList.valueOf(
                getRequiredColor(
                    mainColor,
                    100
                )
            )

            binding.playIconImage.imageTintList = ColorStateList.valueOf(
                getRequiredColor(
                    mainColor,
                    100
                )
            )


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

}