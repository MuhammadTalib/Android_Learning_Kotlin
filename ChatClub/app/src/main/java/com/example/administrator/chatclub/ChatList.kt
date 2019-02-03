package com.example.administrator.chatclub

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_chat_list.*
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.lang.Exception


class ChatList : AppCompatActivity() {

    lateinit var Authentication: FirebaseAuth
    var CurrentUser:UserAccount? = null
    companion object {
        var friend=0
        var new=UserAccount()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        getSupportActionBar()?.hide()

        Authentication = FirebaseAuth.getInstance()

        /* if(MainPage.AccountData[MainPage.MyAccountIndex].userImage is Int)
            userImage.setImageResource(MainPage.AccountData[MainPage.MyAccountIndex].userImage as Int)
        else if(MainPage.AccountData[MainPage.MyAccountIndex].userImage is Bitmap)
            userImage.setImageBitmap(MainPage.AccountData[MainPage.MyAccountIndex].userImage as Bitmap)
*/
        // MyChatList.layoutManager = LinearLayoutManager( this, LinearLayout.VERTICAL,false)
        // MyChatList.adapter = ChatListAdapter(MainPage.AccountData[MainPage.MyAccountIndex].FriendList,::openMessageList)

        if (Authentication.currentUser == null) {
            Log.e("hahaha", "nulll")
            exitChat()
            return
        } else {
            Log.e("hahaha", "not_nulll")
            FirebaseDatabase.getInstance().getReference("Chat_Users")
                    .child(Authentication.currentUser?.uid ?: "")
                    .addListenerForSingleValueEvent(object : ValueEventListener
                    {
                        override fun onCancelled(p0: DatabaseError) {
                            Log.e("hahaha", "cancelled")
                            exitChat()
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {

                            //CurrentUser = snapshot.getValue(UserAccount::class.java)

                            if (CurrentUser == null) {
                                Log.e("hahaha", "is_null")
                               // exitChat()
                            }
                        }

                    })
        }

        userImage.setOnClickListener {
            var intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        add.setOnClickListener {
            val intent = Intent(this, AddChatListMember::class.java)
            startActivityForResult(intent, 10000)
        }

    }

    override fun onResume()
    {
        super.onResume()
       // MyChatList.adapter?.notifyItemChanged(MainPage.AccountData[MainPage.MyAccountIndex].FriendList.size)
    }

    fun openMessageList(AnotherUserIndex:Int){
        new=MainPage.AccountData[AnotherUserIndex]
        friend=AnotherUserIndex
        Log.e("hold","$friend")
        val intent= Intent(this,MessageList::class.java)
        startActivityForResult(intent,10000)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode== Activity.RESULT_OK)
        {
            val intent= Intent(this,ChatList::class.java)
            startActivity(intent)
        }


    }
    private fun exitChat(){
        Authentication.signOut()
        startActivity(Intent(this,MainPage::class.java))
        finish()
    }
}




