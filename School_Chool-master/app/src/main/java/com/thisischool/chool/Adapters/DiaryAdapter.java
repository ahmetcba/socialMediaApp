package com.thisischool.chool.Adapters;
import android.content.Context;
import android.content.Intent;
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
import com.thisischool.chool.Models.Diary;
import com.thisischool.chool.Models.Notes;
import com.thisischool.chool.OurWork.ShowEditDiary;
import com.thisischool.chool.R;

import java.util.ArrayList;
import java.util.List;
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.NotesHolder> {

    private List<Diary> list;
    private Context context;
    ////
    private List<Diary> listNotes;

    public DiaryAdapter(List<Diary> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.diary_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        Diary data = list.get(position);
        listNotes =new ArrayList<>();

        if (data != null) {
            holder.titleTv.setText(data.getContent());
            holder.timeStampTv.setText(data.getTimeStamp());

        }
        /*------------------------------------*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ShowEditDiary.class)
                        .putExtra("intentType", "edit")
                        .putExtra("title", data.getTitle())
                        .putExtra("description", data.getContent())
                        .putExtra("id", data.getId())
                        .putExtra("name", data.getName())
                        .putExtra("date", data.getTimeStamp())
                        .putExtra("imageUrl", data.getImageUrl())
                );
            }
        });



        /*.........*/
        holder.add_to_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.add_to_fvrt.setVisibility(View.GONE);
                holder.del_from_fvrt.setVisibility(View.VISIBLE);
//                addToFavrt(notes.getTitle(), notes.getContent(), notes.getId(), notes.getName(), notes.getImageUrl());
            }
        });
        /*.........*/
        holder.del_from_fvrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.del_from_fvrt.setVisibility(View.GONE);
                holder.add_to_fvrt.setVisibility(View.VISIBLE);
//                delFromFavrt(notes.getId());
            }
        });
        /*.........*/
    }
/*.......*/
    private void addToFavrt(String title, String content, String id, String name, String imgUrl){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(Controller.CurrentUser.getUID()).child("My_");
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

        private TextView titleTv, timeStampTv;
        private ImageView add_to_fvrt, del_from_fvrt;
        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_row_diary);
            timeStampTv = itemView.findViewById(R.id.timestamp_row_diary);
            add_to_fvrt = itemView.findViewById(R.id.add_to_fvrt_row_diary);
            del_from_fvrt = itemView.findViewById(R.id.del_from_fvrt_row_diary);

        }
    }
}
