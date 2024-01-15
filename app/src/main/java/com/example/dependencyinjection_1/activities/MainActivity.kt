package com.example.dependencyinjection_1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjection_1.R
import com.example.dependencyinjection_1.adapter.NoteAdapter
import com.example.dependencyinjection_1.databinding.ActivityMainBinding
import com.example.dependencyinjection_1.model.Note
import com.example.dependencyinjection_1.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initControls()
        initEvents()
    }

    private fun initEvents() {
        binding.btnOpenAddActivity.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter = NoteAdapter(this@MainActivity, onItemClick, onItemDelete)

        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = adapter

        noteViewModel.getAllNote().observe(this, {
            adapter.setNotes(it)
        })

    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)

    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}