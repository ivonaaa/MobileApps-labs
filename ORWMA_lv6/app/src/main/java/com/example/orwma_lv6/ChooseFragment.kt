package com.example.orwma_lv6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ChooseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choose, container,
            false)

        val button = view.findViewById<Button>(R.id.naprijedButton)
        val bestPlayerFragment = BestPlayerFragment()

        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        var radioButton : RadioButton
        val bundle = Bundle()

        bestPlayerFragment.arguments = bundle
        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            radioButton = view.findViewById(id)
            bundle.putString("Player", radioButton.text.toString())
            bestPlayerFragment.arguments=bundle
        }

        button.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, bestPlayerFragment)
            fragmentTransaction?.commit()
        }

        return view
    }

}