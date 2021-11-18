package com.example.esp32app.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
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
    //val ViewMod = MenuFragmentViewModel()
    private val ViewMod: MenuFragmentViewModel by activityViewModels()  //Вот так!!!
    var firedata = FireClass()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuBinding.inflate(inflater)

        //ViewMod = ViewModelProvider(this).get(MenuFragmentViewModel::class.java)  //??
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.e("aaa", "Fragment create1")

        /*ViewMod.Live.observe(viewLifecycleOwner,{
            binding.textView.text = ViewMod.Live.value

        })*/
        ViewMod.LiveSwich.observe(viewLifecycleOwner,{
            var list: List<String> = ViewMod.LiveSwich.value!!

            if (list[1] == "1"){
                binding.switchLed.setChecked(true)
            }else{
                binding.switchLed.setChecked(false)
            }
            binding.textView.text = list[0]
        })

        ViewMod.LiveSeek.observe(viewLifecycleOwner,{
            binding.seekBar.progress = ViewMod.LiveSeek.value!!
            binding.textView.text = ViewMod.LiveSeek.value.toString()
        })



        // ползунок
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //binding.textView.text = ViewModel.sendtext(i.toString())
                ViewMod.sendseek(i)
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
            ViewMod.sendseek(0)
        }

        binding.switchLed.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                //binding.textView.text = ViewModel.sendtext("Switch on")
                ViewMod.sendswich("Switch on","1")
                val r : Int
                r = 1
                firedata.senddata("data","led",r)

            } else {
                //binding.textView.text = ViewModel.sendtext("Switch off") // The switch is disabled
                ViewMod.sendswich("Switch off","0")
                val r : Int
                r = 0
                firedata.senddata("data","led",r)


            }
        }
        binding.bSend.setOnClickListener {
            var t: Int
            t = ViewMod.LiveSeek.value!!  // взятие значения из LiveSeek
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