package com.example.shareadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;



public class stockadd extends AppCompatActivity {

    private EditText date, bestbefore, ounce, bags;
    private Button save;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("stocks");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockadd);

        date = findViewById(R.id.et_date);
        bestbefore = findViewById (R.id.et_bestbefore);
        ounce = findViewById (R.id.et_ounce);
        bags = findViewById (R.id.et_bags);
        save = findViewById (R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sDate = date.getText().toString();
                String sBest = bestbefore.getText().toString();
                String sOunce = ounce.getText().toString();
                String sBags = bags.getText().toString();
                String id = UUID.randomUUID().toString();

                saveToFireStore(id, sDate ,sBest , sOunce , sBags);

            }
        });

    }
    private void saveToFireStore(String id , String sDate , String sBest , String sOunce , String sBags){

        if (!sDate.isEmpty() && !sBest.isEmpty() && !sOunce.isEmpty() && !sBags.isEmpty()){
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("Date" , sDate);
            map.put("Best Before" , sBest);
            map.put("Ounce" , sOunce);
            map.put("Bags" , sBags);

            db.getReference("Stocks").child(id).setValue(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(stockadd.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(stockadd.this, "Failed !!", Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
    }
}
