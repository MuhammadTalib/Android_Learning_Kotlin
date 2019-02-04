package com.example.administrator.chatclub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_chat_list_member.*
import kotlinx.android.synthetic.main.activity_chat_list.*

class AddChatListMember : AppCompatActivity(), ChildEventListener {

    lateinit var auth: FirebaseAuth
    lateinit var chatUserDB: DatabaseReference
    var CurrentUser: Users? = null
    lateinit var usersList: ArrayList<Users>
   // lateinit var friendlist:ArrayList<String>
    // lateinit var myChatAdapter:RecyclerViewGeneralAdapter<MyChatMessage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_chat_list_member)

        auth = FirebaseAuth.getInstance()
        usersList = arrayListOf()
        //friendlist= arrayListOf()

        MyAddChatList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        MyAddChatList.adapter = AddChatListAdapter(usersList, ::onItemClick)

        chatUserDB = FirebaseDatabase.getInstance().getReference("Chat_Users")
        chatUserDB.addChildEventListener(this)

        if (auth.currentUser == null) {

            exitChat()
            return
        } else {
            FirebaseDatabase.getInstance().getReference("Chat_Users")
                    .child(auth.currentUser?.uid ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            exitChat()
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            CurrentUser = snapshot.getValue(Users::class.java)
                            Log.e("hahaha","User= ${CurrentUser?.Email}")
                            if (CurrentUser == null) {
                                exitChat()
                            }


                        }
                    })

        }
    }
        fun onItemClick(position: Int)
        {
            //friendlist.addAll(CurrentUser!!.FriendListsUid)
          //  CurrentUser?.FriendListsUid?.add(usersList[position].uid!!)
           // friendlist.add(usersList[position].uid!!)
            Log.e("hahaha","clicked")

            FirebaseDatabase.getInstance().getReference("Chat_Users")
                    .child(auth.currentUser?.uid ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            exitChat()
                        }
                        override fun onDataChange(snapshot: DataSnapshot) {
                            snapshot.ref.child("friendListsUid").setValue(usersList)
                        }
                    })

        }

        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?)
        {

        }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            val fbuser = p0.getValue(Users::class.java)
            if (fbuser != null) {
                usersList.add(fbuser)
                Log.e("hahaha","fb=${fbuser.Email}")
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