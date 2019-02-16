package com.example.administrator.chatclub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_group_maker.*

class GroupMaker : AppCompatActivity(), ChildEventListener {


    lateinit var userRef: DatabaseReference
    var CurrentUser: Users? = null
    var tempuser:Users?=null
    lateinit var auth: FirebaseAuth
    lateinit var UserAdapter:UserListAdapter
    lateinit var usersList: ArrayList<Users> //all user list
    lateinit var GroupList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_maker)
        getSupportActionBar()?.hide()

        usersList = arrayListOf()
        GroupList= arrayListOf()

        auth = FirebaseAuth.getInstance()
        UserAdapter= UserListAdapter(usersList, ::OnCheckboxChecked, ::OnCheckboxUnChecked)

        list.adapter = UserAdapter
        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        if (auth.currentUser == null) {
            exitChat()
            return
        }
        else
        {
            FirebaseDatabase.getInstance().getReference("Chat_Users")
                    .child(auth.currentUser?.uid ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            exitChat()
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            CurrentUser = snapshot.getValue(Users::class.java)
                            Log.e("hahaha","Current User=${CurrentUser?.Username}")
                            if (CurrentUser == null) {
                                exitChat()
                            }

                        }
                    })
        }
        group_done.setOnClickListener {
            var G:GroupOfUsers=GroupOfUsers().apply {
                this.Name=GroupList.map { u->u.Username }.joinToString(",")
                this.uid=GroupList.map { u->u.uid }.joinToString("||||")
                this.UsersUid=  GroupList.map { u->u.uid } as ArrayList<String>



            }
           /* CurrentUser?.GroupListUid?.add(G)

            FirebaseDatabase.getInstance().getReference("Chat_Users")
                    .child(CurrentUser?.uid ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            exitChat()
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.ref.child("GroupListUid").setValue(CurrentUser?.GroupListUid)
                        }
                    })*/
            CurrentUser?.add_GroupList(G)
            for(i in G.UsersUid)
            {
                if(i!=CurrentUser?.uid)
                {
                    FirebaseDatabase.getInstance().getReference("Chat_Users")
                            .child(i ?: "")
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {
                                    exitChat()
                                }

                                override fun onDataChange(snapshot: DataSnapshot) {
                                    tempuser = snapshot.getValue(Users::class.java)
                                    tempuser?.add_GroupList(G)

                                }
                            })

                }
            }
            startActivity(Intent(this,ChatListPage::class.java))

        }

        userRef = FirebaseDatabase.getInstance().getReference("Chat_Users")
        userRef.addChildEventListener(this)

    }
    fun OnCheckboxChecked(index:Int)
    {
        GroupList.add(usersList[index])
    }

    fun OnCheckboxUnChecked(index:Int)
    {
        GroupList.remove(usersList[index])
    }
    override fun onCancelled(p0: DatabaseError) {
    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        val fbuser = p0.getValue(Users::class.java)
        if (fbuser != null)
        {
            usersList.add(fbuser)
            UserAdapter.notifyItemChanged(usersList.size-1)
            Log.e("hahaha","userrr=${fbuser?.Username}")
        }
    }

    override fun onChildRemoved(p0: DataSnapshot) {
    }

    private fun exitChat(){
        auth.signOut()
        startActivity(Intent(this,MainPage::class.java))
        finish()
    }
}
