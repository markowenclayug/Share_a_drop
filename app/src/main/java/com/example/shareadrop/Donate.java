package com.example.shareadrop;

import android.app.ProgressDialog;
import android.content.Context;
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

public class Donate extends AppCompatActivity {

    EditText txtFname, txtLname, txtContact, txtAddress, txtEmail;
    Button button_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate2);

        txtFname =  findViewById(R.id.txtFname);
        txtLname =  findViewById(R.id.txtLname);
        txtContact =  findViewById(R.id.txtContact);
        txtAddress =  findViewById(R.id.txtAddress);
        txtEmail =  findViewById(R.id.txtEmail);



        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

    }

    private void sendMail() {
        String FName = txtFname.getText().toString().trim();
        String LName = txtLname.getText().toString();
        String Contact = txtContact.getText().toString().trim();
        String Address = txtAddress.getText().toString().trim();
        String Email = txtEmail.getText().toString().trim();

        String message = ("TO WHOM IT MAY CONCERN:\n" +
                "\n" +
                "I, "+FName +" "+LName+" "+", am voluntarily giving my breastmilk to Milk Bank JBLH without remuneration, and for the use of persons in need of this nourishing milk without regard to rank, race, color, creed, religion, or political persuasion. As I am donating my breast milk, I am requesting a donor screening and blood testing schedule (HIV, Syphilis, Hepatitis B). I’m currently residing at "+ Address+". To inform me, you can contact me at "+ Contact +" and "+ Email +" \n" +
                "\n" +
                "I will not hold the representatives responsible for any unforeseen incident that may arise as a result of my breast milk donation.\n" +
                "I certify that I am the person mentioned above, and that I have read and comprehend all of the entries. \n");
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