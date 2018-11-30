package net.songsong.funcode

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import model.ProjectModel
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import kotlinx.android.synthetic.main.activity_gtihub_stars.*

class GtihubStarsActivity : AppCompatActivity()
{
    private var searchButtonClickHandler = View.OnClickListener{

        val username = EditText_SearchName.text

        if (username.isEmpty())
        {
            Toast.makeText(this@GtihubStarsActivity, "please input username", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        val client = OkHttpClient()

        var request = Request.Builder()
                .url("https://api.github.com/users/$username/starred")
                .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call?, e: IOException?)
            {
                Toast.makeText(this@GtihubStarsActivity,"get data failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call?, response: Response?)
            {
                val responseData = response?.body()?.string()
                val json = JSONArray(responseData)
                val projects:ArrayList<ProjectModel> = ArrayList()

                for(i in 0..(json.length() -1))
                {
                    val item = json.getJSONObject(i)

                    val owner = item.getJSONObject("owner")
                    val ownerName = owner.get("login").toString()
                    val avatarURL = owner.get("avatar_url").toString()
                    val projectName = item.get("name").toString()
                    val description = item.get("description").toString()
                    val starCount = item.get("stargazers_count").toString().toInt()
                    val forkCount = item.get("forks_count").toString().toInt()

                    val project = ProjectModel(projectName,description,avatarURL,starCount,forkCount,ownerName)
                    projects.add(project)
                }

                val intent = Intent(this@GtihubStarsActivity,ProjectListActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("projects",projects)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gtihub_stars)

        setupView()
    }

    fun setupView()
    {
        Button_SearchGitHub.setOnClickListener(searchButtonClickHandler)
    }
}
