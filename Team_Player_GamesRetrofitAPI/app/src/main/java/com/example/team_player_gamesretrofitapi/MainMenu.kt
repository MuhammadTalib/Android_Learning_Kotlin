package com.example.team_player_gamesretrofitapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.content_main_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,retrofit2.Callback<TeamObject> {

    var mTeamData:ArrayList<TeamData>?=null
    var mPlayerData:ArrayList<PlayersData>?=null
    var mGamesData:ArrayList<GamesData>?=null
    var fetchid=0
    var pageno=1
    var check=true

    @SuppressLint("ShowToast")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)
        mPlayerData= arrayListOf()
        mTeamData= arrayListOf()
        mGamesData= arrayListOf()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        teamlistmenu.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = teamlistmenu.getLayoutManager() as LinearLayoutManager
                val totalItemCount = linearLayoutManager.getItemCount()
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
               // Log.e("hahaha","total item count $totalItemCount $lastVisibleItem+6")
                if ( totalItemCount <= lastVisibleItem+6 && check) {
                    check=false
                    Log.e("hahaha","scrollong")
                   // Toast.makeText(this@MainMenu,"Scrolling",Toast.LENGTH_SHORT).show()
                    if(fetchid==3){
                        check=false
                        pageno++
                        fetch_games(pageno)
                    }
                    if(fetchid==2){
                        check=false
                        pageno++
                        fetch_players(pageno)
                    }
                    if(fetchid==1){
                        check=false
                        pageno++
                        fetch_teams(pageno)

                    }
                    Log.e("hahaha","hahaha $dx $dy")
                }
                if ( totalItemCount <= lastVisibleItem+1 && dy>0){
                    progress_bar.visibility=View.VISIBLE
                }
                if(dy<0){
                    progress_bar.visibility=View.GONE
                }
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.team -> {
                pageno=1
                setTitle("Teams")
                fetchid=1
                fetch_teams(1)
                mTeamData= arrayListOf()
                teamlistmenu.adapter=TeamAdapter(mTeamData!!)
                teamlistmenu.layoutManager=LinearLayoutManager(this@MainMenu)
            }
            R.id.player -> {
                pageno=1
                setTitle("Players")
                fetchid=2
                fetch_players(1)
                mPlayerData= arrayListOf()
                teamlistmenu.adapter=PlayerAdapter(mPlayerData!!)
                teamlistmenu.layoutManager=LinearLayoutManager(this@MainMenu)
            }
            R.id.games -> {
                pageno=1
                setTitle("Games")
                fetchid=3
                fetch_games(1)
                mGamesData= arrayListOf()
                teamlistmenu.adapter=GameAdapter(mGamesData!!)
                teamlistmenu.layoutManager= LinearLayoutManager(this@MainMenu)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun fetch_games(i:Int){
        Api().service.getOneGamePage("$i").enqueue(object :Callback<GamesObject>{
            override fun onFailure(call: Call<GamesObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<GamesObject>, response: Response<GamesObject>) {
                val myGames = response.body()
                //Log.e("hahaha",myGames?.home_team?.full_name)
                var prevsize=mGamesData?.size
                Log.e("hahaha",prevsize!!.toString())
                mGamesData?.addAll(myGames?.data!!)
                teamlistmenu.adapter?.notifyDataSetChanged()
                check=true
                progress_bar.visibility=View.GONE
            }
        })
    }
    fun fetch_players(i:Int){
        Api().service.getOnePlayerPage("$i").enqueue(object :Callback<PlayersObject>{
            override fun onFailure(call: Call<PlayersObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<PlayersObject>, response: Response<PlayersObject>) {
                val myPlayers = response.body()
                //Log.e("hahaha",myGames?.home_team?.full_name)
                var prevsize=mPlayerData?.size
                mPlayerData?.addAll(myPlayers?.data!!)
                teamlistmenu.adapter?.notifyDataSetChanged()
                 check=true
                progress_bar.visibility=View.GONE
            }
        })
    }
    fun fetch_teams(i:Int){
        Api().service.getOneTeamPage("$i").enqueue(object :Callback<TeamObject>{
            override fun onFailure(call: Call<TeamObject>, t: Throwable) {
            }

            override fun onResponse(call: Call<TeamObject>, response: Response<TeamObject>) {
                val myTeam = response.body()
                val prevsize=mTeamData?.size
                mTeamData?.addAll(myTeam?.data!!)
                teamlistmenu.adapter?.notifyDataSetChanged()
                 check=true
                progress_bar.visibility=View.GONE
            }
        })
    }

    override fun onFailure(call: Call<TeamObject>, t: Throwable) {
    }

    override fun onResponse(call: Call<TeamObject>, response: Response<TeamObject>) {
        Log.e("hahaha","onResponse")
        val myTeam = response.body()
        mTeamData =myTeam?.data
        teamlistmenu.adapter=TeamAdapter(mTeamData!!)
        teamlistmenu.layoutManager= LinearLayoutManager(this)

    }
}

