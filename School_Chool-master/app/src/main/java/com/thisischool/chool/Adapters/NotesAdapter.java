package com.thisischool.chool.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.thisischool.chool.Classes.Controller;
import com.thisischool.chool.Models.Notes;
import com.thisischool.chool.R;

import java.util.ArrayList;
import java.util.List;
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {

    private List<Notes> list;
    private Context context;
    ////
    private List<Notes> listNotes;

    public NotesAdapter(List<Notes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.notes_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        Notes notes = list.get(position);
        listNotes =new ArrayList<>();

        if (notes != null) {
            holder.title.setText(notes.getTitle());
            if (notes.getImageUrl()!=null) {
                Picasso.get().load(notes.getImageUrl()).placeholder(R.drawable.abcd).into(holder.imageView);
            }
        }
        /*------------------------------------*/
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(Controller.CurrentUser.getUID()).child("My_Notes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    listNotes.clear();
                    Notes data = snapshot.getValue(Notes.class);
                    /*for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Notes data = dataSnapshot.getValue(Notes.class);
                            if (notes.getId().equals(data.getId())) {
                                holder.del_from_fvrt.setVisibility(View.VISIBLE);
                                holder.add_to_fvrt.setVisibility(View.GONE);
                            }else {
                                holder.del_from_fvrt.setVisibility(View.GONE);
                                holder.add_to_fvrt.setVisibility(View.VISIBLE);
                            }
                    }*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }});



        /*.........*/
        holder.add_to_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.add_to_fvrt.setVisibility(View.GONE);
                holder.del_from_fvrt.setVisibility(View.VISIBLE);
                addToFavrt(notes.getTitle(), notes.getContent(), notes.getId(), notes.getName(), notes.getImageUrl());
            }
        });
        /*.........*/
        holder.del_from_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.del_from_fvrt.setVisibility(View.GONE);
                holder.add_to_fvrt.setVisibility(View.VISIBLE);
                delFromFavrt(notes.getId());
            }
        });
        /*.........*/
    }
/*.......*/
    private void addToFavrt(String title, String content, String id, String name, String imgUrl){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(Controller.CurrentUser.getUID()).child("My_Notes");
//        String pushKey = reference.push().getKey();
        Notes notes = new Notes(
                title,
                content,
                id,
                name,
                imgUrl,
                ""
        );
        reference.child(id).setValue(notes).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Succeed! Note Added to Favorites", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed! May some network problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*.......*/
    private void delFromFavrt(String id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(Controller.CurrentUser.getUID()).child("My_Notes");

        reference.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Succeed! Notes removed from Favorites", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Failed! May some network problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NotesHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView imageView, add_to_fvrt, del_from_fvrt;
        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_notes);
            imageView = itemView.findViewById(R.id.img_row_notes);
            add_to_fvrt = itemView.findViewById(R.id.add_to_fvrt_row_notes);
            del_from_fvrt = itemView.findViewById(R.id.del_to_fvrt_row_notes);

        }
    }
}
