package com.example.shareadrop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class Receive extends AppCompatActivity {

    EditText txtFname, txtLname, txtContact, txtAddress, txtEmail, txtReason;
    Button button_submit;
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        txtFname =  findViewById(R.id.txtFname);
        txtLname =  findViewById(R.id.txtLname);
        txtContact =  findViewById(R.id.txtContact);
        txtAddress =  findViewById(R.id.txtAddress);
        txtEmail =  findViewById(R.id.txtEmail);
        txtReason = findViewById(R.id.txtReason);

        radioGroup = findViewById(R.id.radioGroup);

        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

    }
    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        radioButton.getText();
    }
    private void sendMail() {
        String rButton = radioButton.getText().toString().trim();
        String FName = txtFname.getText().toString().trim();
        String LName = txtLname.getText().toString();
        String Contact = txtContact.getText().toString().trim();
        String Address = txtAddress.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();
        String Reason = txtReason.getText().toString().trim();

        String message = ("TO WHOM IT MAY CONCERN:\n" +
                "\n" +
                "I, "+FName+" "+LName+" am looking for donated breast milk to feed our child to nourish and sustain  him/her at an early age. I couldn't provide this nourishing liquid for a reason of "+Reason+". I’m currently residing at "+ Address+". To inform me, you can contact me at "+Contact+" and "+Email+"\n" +
                "\n" +
                "Your response will be deeply appreciated and this request of mine will be liable for any consequences of an unforeseen incident that may arise. \n" +
                "I certify that I am the person mentioned above, and that I have read and comprehend all of the entries.\n"+"\nMy preferred communication method is "+rButton);
        String mail = "jblhospital.app@gmail.com"; //receiver
        String subject = " ";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}