package com.example.muhammadtalib.view_pager


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_frag1.*

class Frag1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view= inflater.inflate(R.layout.fragment_frag1, container, false)
        var button: Button =  view.findViewById(R.id.textshowbtn);
        button.setOnClickListener {
            text1.visibility=View.VISIBLE
        }
        return view

    }

   /* @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

   View view = inflater.inflate(R.layout.fragment_rssitem_detail,
    container, false);
   Button button = (Button) view.findViewById(R.id.btn_conferma);
   button.setOnClickListener(new OnClickListener()
   {
             @Override
             public void onClick(View v)
             {
                // do something
             }
   });
   return view;

*/


}
