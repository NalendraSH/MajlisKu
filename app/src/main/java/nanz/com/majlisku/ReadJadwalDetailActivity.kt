package nanz.com.majlisku

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_read_jadwal_detail.*
import nanz.com.majlisku.Model.jadwal_model

class ReadJadwalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_jadwal_detail)

        toolbar_detail.setNavigationIcon(R.drawable.back_icon)
        toolbar_detail.setNavigationOnClickListener { view ->
            finish()
        }

        val jadwal: jadwal_model = intent.getSerializableExtra("data") as jadwal_model

        Picasso.get().load(jadwal.poster_url).into(iv_detail_poster)

        tv_detail_judul.text = jadwal.judul
        tv_detail_pemateri.text = jadwal.pemateri
        tv_jadwal.text = jadwal.jadwal
        tv_detail_alamat.text = jadwal.alamat

        iv_detail_poster.setOnClickListener { view ->
            startActivity(Intent(this, FullScreenImageActivity::class.java).putExtra("poster", jadwal.poster_url))
        }

        tv_map_address.setOnClickListener { view ->
//            tv_map_address.setTextColor(Color.RED)

            val url_maps = "geo:0,0?q="+jadwal.alamat

            val gmmIntentUri : Uri = Uri.parse(url_maps)

            var mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

            mapIntent.setPackage("com.google.android.apps.maps")

            startActivity(mapIntent)
        }

        tv_map_address.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when(event.action){
                    MotionEvent.ACTION_DOWN -> {
                        tv_map_address.setTextColor(Color.RED)
                        return false
                    }
                    MotionEvent.ACTION_UP -> {
                        tv_map_address.setTextColor(resources.getColor(R.color.colorPrimary))
                        return false
                    }
                }
                return false
            }

        })
    }
}
