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

    private val ViewMod: MenuFragmentViewModel by activityViewModels()  //Вот так!!!
    var firedata = FireClass()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // наблюдатель SwitchLed
        ViewMod.LedSwich.observe(viewLifecycleOwner,{
            var list: List<String> = ViewMod.LedSwich.value!!   // взятие значений из LiveSwich

            if (list[1] == "1"){
                binding.switchLed.setChecked(true)
            }else{
                binding.switchLed.setChecked(false)
            }
            binding.textViewLed.text = list[0]
        })


        // наблюдатель SwitchM1
        ViewMod.MOneSwich.observe(viewLifecycleOwner,{
            var list: List<String> = ViewMod.MOneSwich.value!!   // взятие значений из LiveSwich

            if (list[1] == "1"){
                binding.switchup.setChecked(true)
            }else{
                binding.switchup.setChecked(false)
            }
            binding.textViewUp.text = list[0]
        })


        // наблюдатель SwitchM2
        ViewMod.MTwoSwich.observe(viewLifecycleOwner,{
            var list: List<String> = ViewMod.MTwoSwich.value!!   // взятие значений из LiveSwich

            if (list[1] == "1"){
                binding.switchdown.setChecked(true)
            }else{
                binding.switchdown.setChecked(false)
            }
            binding.textViewDown.text = list[0]
        })

        // наблюдатель seekBarUp
        ViewMod.LiveSeekBarUp.observe(viewLifecycleOwner,{
            binding.seekBarUp.progress = ViewMod.LiveSeekBarUp.value!!
            binding.textViewUp.text = ViewMod.LiveSeekBarUp.value.toString()
        })


        // наблюдатель seekBarDowm
        ViewMod.LiveSeekBarDown.observe(viewLifecycleOwner,{
            binding.seekBarDowm.progress = ViewMod.LiveSeekBarDown.value!!
            binding.textViewDown.text = ViewMod.LiveSeekBarDown.value.toString()
        })




        // ползунок seekBarUp
        binding.seekBarUp.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // при перемещении ползунка
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                ViewMod.sendseekup(i) // вызов функции sendseek и передача в неё значений ползунка
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




        // ползунок seekBarDowm
        binding.seekBarDowm.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // при перемещении ползунка
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                ViewMod.sendseekdown(i) // вызов функции sendseek и передача в неё значений ползунка
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
            ViewMod.sendseekdown(0)
            ViewMod.sendseekup(0)
            firedata.senddata("data","numberDown",0)
            firedata.senddata("data","numberUp",0)
        }

        binding.switchLed.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ViewMod.sendswich("Switch on","1")
                val r : Int
                r = 1
                firedata.senddata("data","led",r)

            } else {
                ViewMod.sendswich("Switch off","0")
                val r : Int
                r = 0
                firedata.senddata("data","led",r)
            }
        }

        // switch включения первого двигателя
        binding.switchup.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ViewMod.oneswich("on","1")
                val r : Int
                r = 1
                firedata.senddata("data","Motor1",r)

            } else {
                ViewMod.oneswich("off","0")
                val r : Int
                r = 0
                firedata.senddata("data","Motor1",r)
            }
        }

        // switch включения второго двигателя
        binding.switchdown.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ViewMod.twoswich("on","1")
                val r : Int
                r = 1
                firedata.senddata("data","Motor2",r)

            } else {
                ViewMod.twoswich("off","0")
                val r : Int
                r = 0
                firedata.senddata("data","Motor2",r)
            }
        }

        binding.bSend.setOnClickListener {
            var e: Int
            var t: Int

            e = ViewMod.LiveSeekBarUp.value!!
            t = ViewMod.LiveSeekBarDown.value!!                    // взятие значения из LiveSeekDown

            firedata.senddata("data","numberDown",e)
            firedata.senddata("data","numberUp",t)



        }

    }

}