package com.example.androidclient.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
    }

    fun setDate()
    {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR).toShort()
        val month = (cal.get(Calendar.MONTH) + 1).toShort()
        val day = cal.get(Calendar.DATE).toShort()
        room_list_date.text = ("${year}년 ${month}월 ${day}일")
    }
}