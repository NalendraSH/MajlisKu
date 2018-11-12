package nanz.com.majlisku

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import nanz.com.majlisku.Adapter.adapter_jadwal
import nanz.com.majlisku.Model.jadwal_model

class MainActivity : AppCompatActivity() {

    private var databaseReference: DatabaseReference? = null

    private var jadwalList: MutableList<jadwal_model> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thiscontext: Context = this

        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem): Boolean {
                if (item.itemId == R.id.menu_contact){
                    startActivity(Intent(thiscontext, ContactUsActivity::class.java))
                }
                return true
            }

        })

        databaseReference = FirebaseDatabase.getInstance().reference
        progressBar_main.visibility = View.VISIBLE

//        fab.setOnClickListener { view ->
//            val intent = Intent(this, CreateJadwalActivity::class.java)
//            startActivity(intent)
//        }
        fab.visibility = View.GONE

        val context: Context = this

//        val url = "https://firebasestorage.googleapis.com/v0/b/majlisku-a8acf.appspot.com/o/poster%2F1541733996207.jpeg?alt=media&token=e989aced-e3df-47b9-a621-bdf5bfa00cda"
//
//        Picasso.get().load(url).into(iv_)

        databaseReference?.child("jadwal")?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                progressBar_main.visibility = View.GONE
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                progressBar_main.visibility = View.GONE
                for (noteDataSnapshot in dataSnapshot.children){
                    var jadwal = noteDataSnapshot.getValue(jadwal_model::class.java)

                    jadwalList.add(jadwal!!)
                }

                rv_jadwal_list.adapter = adapter_jadwal(context, jadwalList)
                rv_jadwal_list.layoutManager = LinearLayoutManager(context)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_contact){
            startActivity(Intent(this, ContactUsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
