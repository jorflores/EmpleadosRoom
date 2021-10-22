package com.delnortedevs.empleadosroom

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.delnortedevs.empleadosroom.databinding.FragmentActualizarEmpleadoBinding
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
 * Use the [ActualizarEmpleadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActualizarEmpleadoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentActualizarEmpleadoBinding? = null

    private val binding get() = _binding!!

    private lateinit var nombre: String

    private var empladoId = 0

    private val viewModel: EmpleadosViewModel by activityViewModels {
        EmpleadosViewModelFactory(
            (activity?.application as EmpleadoApp).database.EmpleadoDao()
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            nombre = it.getString("nombre").toString()
        }

        Log.d("RoomTest", "LLegó el empleado: $nombre")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActualizarEmpleadoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val empleado = viewModel.getByName(nombre)

            binding.editTextNombre.setText(empleado.nombre)
            binding.editTextDepartamento.setText(empleado.departamento)
            binding.editTextEmail.setText(empleado.email)
            empladoId = empleado.id
        }


        binding.buttonActualizarEmpleado.setOnClickListener{

            val nombre = binding.editTextNombre.text.toString()
            val departamento = binding.editTextDepartamento.text.toString()
            val correo = binding.editTextEmail.text.toString()

            val empleado = Empleado(empladoId,nombre,departamento,correo)

            lifecycleScope.launch {
                val actualizar = viewModel.updateEmpleado(empleado)
                findNavController().navigate(R.id.action_actualizarEmpleadoFragment_to_homeFragment)
            }


        }

        binding.buttonEliminar.setOnClickListener{
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Eliminar este empleado?")
                    .setCancelable(false)
                    .setPositiveButton("Si") { _, _ ->
                        // Delete
                        lifecycleScope.launch {
                            viewModel.deleteEmpleado(Empleado(empladoId, "", "", ""))
                            findNavController().navigate(R.id.action_actualizarEmpleadoFragment_to_homeFragment)
                        }
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()





        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ActualizarEmpleadoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ActualizarEmpleadoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}