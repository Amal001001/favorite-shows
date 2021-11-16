package com.example.amal

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amal.DBviewModel.DBActivityViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DbFragment : Fragment() {

    lateinit var dbActivityViewModel: DBActivityViewModel

    private lateinit var rvBD: RecyclerView
    private lateinit var showsAdapter: ShowsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_db, container, false)

        dbActivityViewModel = ViewModelProvider(this).get(DBActivityViewModel::class.java)
        dbActivityViewModel.readFromDB().observe(viewLifecycleOwner, { shows -> showsAdapter.update(shows) })

        rvBD = view.findViewById(R.id.rvBD)
        showsAdapter = ShowsAdapter(this)
        rvBD.adapter = showsAdapter
        rvBD.layoutManager = LinearLayoutManager(requireContext())
        rvBD.adapter!!.notifyDataSetChanged()

        return view
    }

    fun toastSummary(summary:String){
        Toast.makeText(requireContext(), summary, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    fun deleteDialog(Id: Int){
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val confirmDelete = TextView(requireContext())
        confirmDelete.text = "  Are you sure you want to delete this show?"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    _, _ -> dbActivityViewModel.deleteShow(Id) })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel() })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete Show")
        alert.setView(confirmDelete)
        alert.show()
    }
}