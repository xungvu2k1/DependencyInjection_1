package com.example.dependencyinjection_1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection_1.R
import com.example.dependencyinjection_1.databinding.ActivityAddNoteBinding
import com.example.dependencyinjection_1.model.Note
import com.example.dependencyinjection_1.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityAddNoteBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        binding.btnAdd.setOnClickListener {
            val note = Note(binding.edtNoteTitle.text.toString(), binding.edtNoteDes.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }

    }
}