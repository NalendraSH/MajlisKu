package nanz.com.majlisku

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.cketti.mailto.EmailIntentBuilder
import kotlinx.android.synthetic.main.activity_contact_us.*

class ContactUsActivity : AppCompatActivity() {

    val emailDestination = "nalendrasetya@gmail.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        toolbar_contact_us.setNavigationIcon(R.drawable.back_icon)
        toolbar_contact_us.setNavigationOnClickListener { view ->
            finish()
        }

        btn_send_email.setOnClickListener { view ->
//            val sendEmail = Intent(android.content.Intent.ACTION_SEND)
//
            val message = "Nama: "+et_name.text.toString()+"\nPesan: "+et_message.text.toString()
//
//            sendEmail.setType("plain/text");
//            sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, )
//            sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, et_subject.text.toString())
//            sendEmail.putExtra(android.content.Intent.EXTRA_TEXT, message)
//
//            startActivity(Intent.createChooser(sendEmail, "Kirim Pesan"))

            val sendEmail: Intent = EmailIntentBuilder.from(this).to(emailDestination).subject(et_subject.text.toString())
                    .body(message).build()

            startActivity(Intent.createChooser(sendEmail, "Kirim Pesan"))
        }
    }
}
