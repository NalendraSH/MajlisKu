package nanz.com.majlisku

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        toolbar_fullscreenimage.setNavigationIcon(R.drawable.close_icon)
        toolbar_fullscreenimage.setNavigationOnClickListener { view ->
            finish()
        }

        val poster_url: String = intent.getSerializableExtra("poster") as String

        Picasso.get().load(poster_url).into(iv_full_poster)

        var tampilToolbar = true

        iv_full_poster.setOnClickListener { view ->
            tampilToolbar = !tampilToolbar

            if (tampilToolbar){
                toolbar_fullscreenimage.visibility = View.VISIBLE
            }else{
                toolbar_fullscreenimage.visibility = View.GONE
            }
        }

        if (tampilToolbar){
            toolbar_fullscreenimage.visibility = View.VISIBLE
        }else{
            toolbar_fullscreenimage.visibility = View.GONE
        }
    }
}
