package com.thisischool.chool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.thisischool.chool.Adapters.ClassNotesAdapters;
import com.thisischool.chool.Adapters.NotesAdapter;
import com.thisischool.chool.Classes.AppHelper;
import com.thisischool.chool.Classes.Controller;
import com.thisischool.chool.Models.ClassNotes;
import com.thisischool.chool.Models.Notes;
import com.thisischool.chool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.thisischool.chool.OnlineDatabase.MyReferences.classGroupNotes;

public class ClassNotesActivity extends AppCompatActivity {
        private RecyclerView recyclerView;
        private List<ClassNotes> list;
        private DatabaseReference reference;
        private Dialog dialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_class_notes);

            recyclerView = findViewById(R.id.recyclervew_notes);

            reference = classGroupNotes(this);

            findViewById(R.id.add_note).setOnClickListener(view -> {
                showAddNoteDialog();
            });

        }

        private void showAddNoteDialog() {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.add_note_dialog);
            Objects.requireNonNull(dialog.getWindow())
                    .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            EditText title,content;
            title = dialog.findViewById(R.id.note_title_dialog);
            content = dialog.findViewById(R.id.note_content_dialog);
            dialog.show();
            dialog.findViewById(R.id.ok_note_dialog).setOnClickListener(view -> {
                if (content.getText().toString().isEmpty()) {
                    content.setError("Cannot Add Empty Note");
                } else {
                    String t = title.getText().toString();
                    if (t.isEmpty()) {
                        t = content.getText().toString().substring(0,5);
                    }
                    postNote(t,content.getText().toString());
                }
            });

        }

        private void setAdapter(@NotNull List<ClassNotes> mList) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));
            ClassNotesAdapters adapter = new ClassNotesAdapters(mList, this);
            recyclerView.setAdapter(adapter);
        }
        private void setData() {
            list = new ArrayList<>();
            reference.add : snapshot.getChildren()) {
                            ClassNotes notes = dataSnapshot.getValue(ClassNotes.class);
                            if (notes != null) {
                                list.add(notes);
                            }
                            setAdapter(list);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            setData();
        }

        private void postNote(String title, String content) {
            String id = reference.push().getKey();
            });
        }
    }