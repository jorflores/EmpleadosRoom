package com.delnortedevs.empleadosroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.delnortedevs.empleadosroom.adapter.EmpleadosAdapter
import com.delnortedevs.empleadosroom.databinding.FragmentHomeBinding
import com.delnortedevs.empleadosroom.model.Empleado
import com.delnortedevs.empleadosroom.viewmodels.EmpleadosViewModel
import com.delnortedevs.empleadosroom.viewmodels.EmpleadosViewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    private val viewModel: EmpleadosViewModel by activityViewModels {
        EmpleadosViewModelFactory(
            (activity?.application as EmpleadoApp).database.EmpleadoDao()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
/*
            val empleado = Empleado("Kiam","RH","email")
            app.database.EmpleadoDao().insertEmpleado(empleado)*/

            val empleados = viewModel.getAll()
            binding.textView.text = "Número de Empleados: ${empleados.size}"

            val adapter = EmpleadosAdapter(requireContext(),empleados) {
                Log.d("RoomTest", "${it.nombre}")

                val action = HomeFragmentDirections.actionHomeFragmentToActualizarEmpleadoFragment(nombre=it.nombre)
               view.findNavController().navigate(action)

            }

            binding.rvEmpleados.adapter = adapter
            binding.rvEmpleados.layoutManager = LinearLayoutManager(requireContext())


            binding.buttonAlta.setOnClickListener{
               findNavController().navigate(R.id.action_homeFragment_to_altaEmpleadoFragment)
            }

           // Log.d("RoomTest", "${empleados.size}")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}