package com.example.apinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.size.Scale
import com.example.apinews.databinding.ActivityDetailBinding
import com.example.apinews.model.ArticlesItem

class DetailActivity : AppCompatActivity() {

    //variable untuk menangkap data yg di kirimkan oleh MainActivity melalui CdvNewsHeadlineAdapter
    companion object{
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    //untuk menampilkan view,karena kita akan menampilkan detail activity maka yg di extend Activity
    //Jika yang di extend MainActivity maka yg di extend
    //intinya tinggal tambahin tulisan binding akhir
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        //untuk menampilkan data yg dikirimkan oleh MainActivity melalui
        val data = intent.getParcelableExtra<ArticlesItem?>(DETAIL_NEWS) as ArticlesItem

        //untuk membuild layout
        binding.run {
            setContentView(root)

            //untuk membuild actionbar
            setSupportActionBar(toolBar)

            //untuk menampilkan tombol back
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title

            //untuk get image
            imgToolbar.apply {
                load(data.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            //untuk get content
            txtContent.text = data.content

            //untuk get publishAt
            txtDate.text = data.publishedAt
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true

    }
}