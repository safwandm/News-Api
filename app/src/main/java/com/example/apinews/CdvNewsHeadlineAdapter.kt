package com.example.apinews

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.size.Scale
import com.example.apinews.databinding.CdvNewsHeadlineBinding
import com.example.apinews.model.ArticlesItem

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlineVH>(){

    //untuk mengambil data d dalam model articel item
    private val listData = ArrayList<ArticlesItem>()

    //fungsi ini berfungsi untuk add data ke dalam recyclerview
    fun addData(item: List<ArticlesItem>){
        listData.clear()
        listData.addAll(item)
        notifyDataSetChanged()
    }

    //berfungsi untuk menginflate atau menerapkan operasi yang di buat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlineVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater,parent,false)
        return CdvNewsHeadlineVH(binding)
    }

    //digunakan untuk mengatahui panjang/banyak data(size) guna kebutuhan looping
    override fun getItemCount():Int {
        return listData.size

    }

    //digunakan untuk memposisikan widget pada layout model
    override fun onBindViewHolder(holder: CdvNewsHeadlineVH, position: Int) {
        holder.bind(listData[position])
    }

}
    //sebagai adapter recyclerview
class CdvNewsHeadlineVH(private val binding: CdvNewsHeadlineBinding) :
       RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticlesItem){
            binding.run {
                txtTitle.text = cropText(article.title?: "Tidak ada judul")
                txtSubtitle.text = article.publishedAt
                imgHeadline.apply {
                    load(article.urlToImage){
                        scale(Scale.FILL)
                    }
                    contentDescription = article.description
                }
                root.setOnClickListener{
                    val intent = Intent(it.context,DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_NEWS,article)//DETAIL_NEWS BEFUNGSI SEBAGAI VARIABLE TG BERISI DATA YG AKAN DI KIRIMKAN KE DETAILACTIVITY

                    }
                    it.context.startActivity(intent)

                }
            }
        }
        //untuk memotong text yg lebih dari 50
        private fun cropText(text: String): String{
            return text.take(50) + if (text.length > 50)"..." else ""

        }
}
