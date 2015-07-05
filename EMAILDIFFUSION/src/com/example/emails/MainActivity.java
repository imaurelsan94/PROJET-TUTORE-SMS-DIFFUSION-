package com.example.emails;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
			
        	//---Ceci est juste un exemple---
			public void onClick(View v) {
				String[] to = {"mendoo.aristide@gmail.com", "tchalims@gmail.com"};
				String[] cc = {"admofficiel@gmail.com"};
				sendEmail(to, cc, "Message de l'ESMT", "Message à diffuser");
			}
		});
    }
    //---Envoi d'un message de diffusion par email---
    	private void sendEmail (String[] emailAdresses, String[] carbonCopies, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        String[] to = emailAdresses;
        String[] cc = carbonCopies;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("Message à diffuser");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    	}
    }
