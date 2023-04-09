package com.example.myfirstapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapplication.adapter.UserAdapter
import com.example.myfirstapplication.model.realm.User
import io.realm.Realm
import io.realm.exceptions.RealmException
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var userAdapter: UserAdapter
    val lm = LinearLayoutManager(activity)
    lateinit var realm: Realm

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        action()
    }

    fun initView() {
        rv_user.layoutManager = lm
        userAdapter = UserAdapter(activity!!)
        rv_user.adapter = userAdapter

        realm = Realm.getDefaultInstance()
        getAllUser()
    }

    fun action() {
        btn_ad.setOnClickListener {
            var b = AlertDialog.Builder(activity)
            b.setTitle("Move to Movie Detail Page")
            b.setMessage("Are You Sure?")
            b.setPositiveButton("Yes", {dialog: DialogInterface?, which: Int ->
                var i = Intent(activity, MovieDetailActivity::class.java)
                startActivity(i)
            })

            b.setNegativeButton("No", {dialog: DialogInterface?, which: Int -> })

//            b.setNeutralButton("Ok", {dialog: DialogInterface?, which: Int ->
//                var i = Intent(activity, MovieDetailActivity::class.java)
//                startActivity(i)
//            })
            b.show()
        }

        btn_toast.setOnClickListener {
            Toast.makeText(activity, "Hello!", Toast.LENGTH_SHORT).show()
        }

        btn_add.setOnClickListener {
            realm.beginTransaction()
            var count = 0

            realm.where(User::class.java).findAll().let {
                for (i in it) {
                    count++
                }
            }

            try {
                var user = realm.createObject(User::class.java)
                user.setId(count+1)
                user.setNama(et_name.text.toString())
                user.setEmail(et_email.text.toString())

                realm.where(User::class.java).findAll().let {
                    userAdapter.setUser(it)
                }
//                tv_result.text = user.getId().toString() + " " + user.getNama() + " " + user.getEmail()
                et_name.setText("")
                et_email.setText("")

                realm.commitTransaction()

            } catch (e: RealmException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", et_id.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_name.text.toString())
                it!!.setEmail(et_email.text.toString())
            }
            realm.commitTransaction()
        }

        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id", et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
                getAllUser()
            }
            realm.commitTransaction()
        }
    }

    fun getAllUser() {
        realm.where(User::class.java).findAll().let {
            userAdapter.setUser(it)
        }
    }
}