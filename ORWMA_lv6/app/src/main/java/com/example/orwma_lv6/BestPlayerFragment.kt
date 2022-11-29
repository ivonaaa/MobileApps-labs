package com.example.orwma_lv6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class BestPlayerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_player, container,false)

        view.findViewById<TextView>(R.id.BestTextView).text = arguments?.getString("Player").toString()

        view.findViewById<Button>(R.id.NazadButton).setOnClickListener {
            val chooseFragment = ChooseFragment()
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, chooseFragment)
            fragmentTransaction?.commit()
        }

        return view
    }
}