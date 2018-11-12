package nanz.com.majlisku.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.jadwal_item.view.*
import nanz.com.majlisku.R
import nanz.com.majlisku.ReadJadwalDetailActivity
import nanz.com.majlisku.Model.jadwal_model

class adapter_jadwal(private val context: Context, private val items: List<jadwal_model>) :
        RecyclerView.Adapter<adapter_jadwal.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(items: jadwal_model){
            itemView.tv_judul_kegiatan.text = items.judul
            itemView.tv_pemateri.text = items.pemateri
            itemView.tv_alamat.text = items.alamat

//            val storageReference: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(items.poster_url!!)
//            val url = storageReference.downloadUrl

//            items.poster_url?.let {
//            Glide.with(itemView).load().into(itemView.iv_poster)
//            Glide.with(itemView).load(items.poster_url).into(itemView.iv_poster)
            Picasso.get().load(items.poster_url).into(itemView.iv_poster)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.jadwal_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])

        holder.itemView.setOnClickListener { view ->
            context.startActivity(Intent(context, ReadJadwalDetailActivity::class.java).putExtra("data", items.get(position)))
        }
    }
}