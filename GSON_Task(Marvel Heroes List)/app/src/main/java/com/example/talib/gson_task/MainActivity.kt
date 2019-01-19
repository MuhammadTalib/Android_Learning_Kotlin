package com.example.talib.gson_task

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var MarvelCharactersList:ArrayList<MarvelCharacters> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CharacterListt.adapter = MarvelAdapter(MarvelCharactersList)
        CharacterListt.layoutManager = LinearLayoutManager(this)

        Httptaask().execute("https://simplifiedcoding.net/demos/marvel/")

    }

    inner class Httptaask : AsyncTask<String, Unit, String>()
    {

        override fun doInBackground(vararg params: String):String
        {
            val url = URL(params[0])
            val urlConnection = url.openConnection() as HttpURLConnection
            return try
            {
                val inpStream = BufferedInputStream(urlConnection.getInputStream())
                val reader = InputStreamReader(inpStream)
                val responseLines = reader.readLines()
                responseLines.joinToString("\n")
            }
            catch (e: Exception)
            {
                return "No Response"
            }
            finally
            {
                urlConnection.disconnect()
            }
        }

        override fun onPostExecute(result: String)
        {
            val CharacterList = Gson().fromJson(result,Array<MarvelCharacters>::class.javaObjectType)
            CharacterList?.also{
                MarvelCharactersList.addAll(it.toList())
                CharacterListt.adapter?.notifyDataSetChanged()
            }
            }

        }

    }
