package com.delnortedevs.empleadosroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.delnortedevs.empleadosroom.databinding.FragmentAltaEmpleadoBinding
import com.delnortedevs.empleadosroom.databinding.FragmentHomeBinding
import com.delnortedevs.empleadosroom.model.Empleado
import com.delnortedevs.empleadosroom.viewmodels.EmpleadosViewModel
import com.delnortedevs.empleadosroom.viewmodels.EmpleadosViewModelFactory
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var _binding: FragmentAltaEmpleadoBinding? = null

private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [AltaEmpleadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AltaEmpleadoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        _binding = FragmentAltaEmpleadoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonAltaEmpleado.setOnClickListener{

            lifecycleScope.launch {

                val empleado = Empleado(binding.editTextNombre.text.toString(),binding.editTextDepartamento.text.toString(),
                    binding.editTextEmail.text.toString())
                val insertEmpleado = viewModel.insertEmpleado(empleado)

                if (insertEmpleado >0){
                    Toast.makeText(activity,"Empleado dada de alta",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_altaEmpleadoFragment_to_homeFragment)
                }
                Log.d("RoomTest",insertEmpleado.toString())

            }

        }






    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AltaEmpleadoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AltaEmpleadoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}