package net.songsong.funcode

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_star_list_item.view.*
import model.ProjectModel

class ProjectListAdapter(var projects: ArrayList<ProjectModel>) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_star_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bindProjectModel(projects[position])
    }

    override fun getItemCount(): Int
    {
        return projects.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindProjectModel(model: ProjectModel)
        {
            with(model)
            {
                itemView.TextView_Project.text = projectName
                itemView.TextView_Description.text = description
                itemView.TextView_Star.text = starCount.toString()
                itemView.TextView_Fork.text = forkCount.toString()
                itemView.TextView_UserName.text = userName
            }
        }
    }
}