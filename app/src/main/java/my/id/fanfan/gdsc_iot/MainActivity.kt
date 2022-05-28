package my.id.fanfan.gdsc_iot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("")

        btn_on.setOnClickListener {
            ref.child("led").setValue(1)
        }

        btn_off.setOnClickListener {
            ref.child("led").setValue(0)
        }

        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    tv_suhu.setText(snapshot.child("temperature").value.toString())
                    tv_kelembapan.setText(snapshot.child("kelembapan").value.toString())
                    tv_status.setText(snapshot.child("led").value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // kosong
            }

        })
    }
}