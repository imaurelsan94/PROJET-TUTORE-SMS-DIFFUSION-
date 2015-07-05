package com.example.smsdiffusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReception extends BroadcastReceiver {

	@Override	
public void onReceive(Context context, Intent intent) {
		//---Reception du message envoy� de l'autre telephone---
		Bundle bundle = intent.getExtras(); 
		SmsMessage[] msgs = null;
		String str = "";
		if (bundle != null)
		{
			//---R�cup�ration du message re�u---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i=0; i<msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				str += "SMS de ESMT " + msgs[i].getOriginatingAddress();
				str += " :";
				str += msgs[i].getMessageBody().toString();
				str += "\n";
		}
		//---Affichage du nouveau message re�u---
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		//---Lancement du MainActivity---
		Intent mainActivityIntent = new Intent(context, MainActivity.class);
		mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(mainActivityIntent);
		
		//---Envoi d'un broadcast intent pour actualiser le nouveau message re�u, dans l'activity---
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction("SMS_RECEIVED_ACTION");
		broadcastIntent.putExtra("sms", str);
		context.sendBroadcast(broadcastIntent);
		}
	}
}

//---Aurel Yahouedeou---
