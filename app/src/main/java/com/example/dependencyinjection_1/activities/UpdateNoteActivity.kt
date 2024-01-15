package com.example.dependencyinjection_1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection_1.R
import com.example.dependencyinjection_1.databinding.ActivityUpdateNoteBinding
import com.example.dependencyinjection_1.model.Note
import com.example.dependencyinjection_1.viewmodel.NoteViewModel

class UpdateNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUpdateNoteBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_update_note)

        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding.edtNoteTitle.setText(note.title)
        binding.edtNoteDes.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            note.title = binding.edtNoteTitle.text.toString()
            note.description = binding.edtNoteDes.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }

    }
}