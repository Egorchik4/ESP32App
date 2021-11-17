package com.example.esp32app.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.esp32app.R
import com.example.esp32app.database.FireClass
import com.example.esp32app.databinding.FragmentMenuBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// только получение фрагментов из View
class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding  // название разметки layout + 'binding'
    var ViewMod = MenuFragmentViewModel()
    var firedata = FireClass()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuBinding.inflate(inflater)

        //ViewMod = ViewModelProvider(this).get(MenuFragmentViewModel::class.java)  //??

        Log.e("aaa", "Fragment create")

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("aaa", "Fragment create1")

        // установка наблюдателя
        ViewMod.Live.observe(viewLifecycleOwner, Observer<String> {
            binding.textView.text = ViewMod.Live.value  // выставление

        })

        // ползунок
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //binding.textView.text = ViewModel.sendtext(i.toString())
                ViewMod.sendtext(i.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                //Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
                //Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()
            }
        })


        binding.bSTOP.setOnClickListener {
            //binding.seekBar.progress = ViewModel.sendtext("0").toInt()
            //binding.textView.text = ViewModel.sendtext("0")
            //ViewModel.valueseekBar(0)
            ViewMod.sendtext("0")
        }

        binding.switchLed.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                //binding.textView.text = ViewModel.sendtext("Switch on")
                ViewMod.sendtext("Switch on")
                val r : Int
                r = 1
                firedata.senddata("data","led",r)

            } else {
                //binding.textView.text = ViewModel.sendtext("Switch off") // The switch is disabled
                ViewMod.sendtext("Switch off")
                val r : Int
                r = 0
                firedata.senddata("data","led",r)


            }
        }
        binding.bSend.setOnClickListener {
            var r: String
            var t: Int
            r = binding.textView.text.toString()
            t = r.toInt()


            firedata.senddata("data","number",t)


        }


    }



    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}