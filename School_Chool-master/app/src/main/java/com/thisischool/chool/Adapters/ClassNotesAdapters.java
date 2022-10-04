package com.thisischool.chool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thisischool.chool.Models.ClassNotes;
import com.thisischool.chool.Models.Notes;
import com.thisischool.chool.R;

import java.util.List;

public class ClassNotesAdapters extends RecyclerView.Adapter<ClassNotesAdapters.ClassNotesHolder>{

        private List<ClassNotes> list;
        private Context context;

    public ClassNotesAdapters(List <ClassNotes> list, Context context) {
        this.list = list;
        this.context = context;
    }

        @NonNull
        @Override
        public ClassNotesAdapters.ClassNotesHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        return new ClassNotesAdapters.ClassNotesHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_notes_row, parent, false));
    }

        @Override
        public void onBindViewHolder (@NonNull ClassNotesHolder holder,int position){
        ClassNotes notes = list.get(position);
        if (notes != null) {
            holder.name.setText("Note by @" + notes.getName());
            holder.content.setText(notes.getContent());
            holder.title.setText(notes.getTitle());
        }
    }

        @Override
        public int getItemCount () {
        return list.size();
    }

        static class ClassNotesHolder extends RecyclerView.ViewHolder {

            TextView title, content, name;

            public ClassNotesHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_notes);
                content = itemView.findViewById(R.id.content_notes);
                name = itemView.findViewById(R.id.name_notes);
            }
        }
    }