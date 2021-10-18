package com.delnortedevs.empleadosroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.delnortedevs.empleadosroom.HomeFragment
import com.delnortedevs.empleadosroom.HomeFragmentDirections
import com.delnortedevs.empleadosroom.R
import com.delnortedevs.empleadosroom.databinding.ListaEmpleadosBinding
import com.delnortedevs.empleadosroom.model.Empleado
import kotlin.coroutines.coroutineContext



class EmpleadosAdapter (val context: Context, var empleados: List<Empleado>): RecyclerView.Adapter<EmpleadosAdapter.ViewHolder>() {

    class ViewHolder (val binding: ListaEmpleadosBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListaEmpleadosBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {

            textViewNombre.text = empleados[position].nombre
            textViewDepartamento.text = empleados[position].departamento
            textViewEmail.text = empleados[position].email
        }


        holder.binding.cardviewId.setOnClickListener{
            Toast.makeText(context,position.toString(),Toast.LENGTH_SHORT).show()

            val action = HomeFragmentDirections.actionHomeFragmentToActualizarEmpleadoFragment(nombre=empleados[position].nombre)

          //  findNavController().navigate(R.id.action_homeFragment_to_altaEmpleadoFragment)

        }

    }

    override fun getItemCount(): Int {
        return empleados.size


    }
}