package com.example.administrator.a20_jan_task

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class contactdetails(


        @ColumnInfo var Contact_Name:String="",
        @PrimaryKey(autoGenerate = true) var	Contact_Number:Int=0,
        @ColumnInfo var Contact_Type:String=" Mobile, Home, Work",
        @Embedded(prefix = "address_") var	Address:Addressdetails= Addressdetails()

)
