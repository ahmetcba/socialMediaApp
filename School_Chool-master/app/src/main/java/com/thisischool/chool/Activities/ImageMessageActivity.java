package com.thisischool.chool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.thisischool.chool.Classes.AppHelper;
import com.thisischool.chool.Classes.Controller;
import com.thisischool.chool.Models.Notes;
import com.thisischool.chool.OnlineDatabase.SendMessage;
import com.thisischool.chool.OurWork.NotesActivity;
import com.thisischool.chool.OurWork.SchoolChatActivity;
import com.thisischool.chool.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.thisischool.chool.OnlineDatabase.MyReferences.classGroupNotes;

public class ImageMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_message);

        activityType = getIntent().getStringExtra("ActivtyName");

        msgEdit = findViewById(R.id.msg_imgmsg);
        imageView = findViewById(R.id.imgview_imgmsg);

        findViewById(R.id.send_imgmsg).setOnClickListener(view -> {
            if (msgEdit.getText().toString().isEmpty()) {geActivity.this, ClassChatGroupActivity.class));
                } else if (activityType.equals("SchoolChatActivity")) {
                    uploadImageSchool(msgEdit.getText().toString());
                    startActivity(new Intent(ImageMessageActivity.this, SchoolChatActivity.class));
                }else  if (activityType.equals("MyNotes")) {
                    uploadNotesWithImage(msgEdit.getText().toString());
                }
                finish();
            }
        });

        imageView.setOnClickListener(view -> {
            selectImage();
        });
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture for your menu Item's Display Picture"),PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data
                != null && data.getData() != null){
            path = data.getData();
            Picasso.get().load(path).fit().centerCrop().noPlaceholder().into(imageView);
        }
    }
    private void uploadImage(String msg) {
        if (path != null) {
                try {
                    final StorageReference mStorageRe
                        SendMessage.sendMessageToGroupChat(this,msg,url,msgEdit);
                    })).addOnFailureListener(e -> {
                        AppHelper.showToast(ImageMessageActivity.this,e.getMessage());
                    });

                } catch (Exception e) {
                    Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                }

            }
        }

    /*............................................*/
    private void uploadImageSchool(String msg) {
            if (path != null) {
                try {
                    final StorageReferencSchoolChat(this,msg,url,msgEdit);
                    })).addOnFailureListener(e -> {
                        AppHelper.showToast(ImageMessageActivity.this,e.getMessage());
                    });

                } catch (Exception e) {
                    Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                }

            }
        }
    /*............................................*/

    private void uploadNotesWithImage(String title){
        if (path != null) {
            try {
                final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference(NOTES_IMAGES_STORAGE_DB)
                        .child(Systeence.push().getKey();

                    Notes notes = new Notes(
                            title,
                            "",
                            pushKey,
                            Controller.CurrentUser.getUserNickname(this)
                            , url,
                            ""
                    );
                    assert pushKey != null;
                    reference.child(pushKey).setValue(notes).addOnCompleteListener(task -> {
                        if (task.isSuImageMessageActivity.this, "Succeed! Note Added", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ImageMessageActivity.this, "Failed! May some network problem", Toast.LENGTH_SHORT).show();
                        }
                    });

                    /*.................*/
                })).addOnFailureListener(e -> {
                    AppHelper.showToast(ImageMessageActivity.this,e.getMessage());
                });
            } catch (Exception e) {
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
            }

        }

    }

}