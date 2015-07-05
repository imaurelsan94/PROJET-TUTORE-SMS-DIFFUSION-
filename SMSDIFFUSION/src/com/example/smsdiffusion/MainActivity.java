package com.example.smsdiffusion;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btnSendSMS;
	IntentFilter intentFilter;

	private BroadcastReceiver intentReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			//---Afficher le message reçu dans le TextView---
			TextView SMSes = (TextView) findViewById(R.id.textView1);
			SMSes.setText(intent.getExtras().getString("sms"));
		}
	};

	/** On l'appel lorsque l'activity vient d'être créée. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//---intent pour filtrer les SMS reçus---
		intentFilter = new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");
		
		//---Enregistrement du recepteur---
		registerReceiver(intentReceiver, intentFilter);
		
		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		btnSendSMS.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				// Préenregistrement de tous les contacts à prendre en compte pour le service---

				Intent i = new
						Intent(android.content.Intent.ACTION_VIEW);
				i.putExtra("address", "777128395; 770000000; 777777777; 5554; 5556");
				i.putExtra("sms_body", "Message à diffuser");
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);				
			}
		});
	}
	@Override
	protected void onResume() {
		//---Enregistrement du recepteur---
		registerReceiver(intentReceiver, intentFilter);
		super.onResume();
	}
	@Override
	protected void onPause() {
		//---Supprimer l'enregistrement du recepteur---
		unregisterReceiver(intentReceiver);
		super.onPause();
	}
}