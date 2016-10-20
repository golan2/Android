package com.example.iziktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

import network.pipe.Consumer;
import network.pipe.Coordinator;
import network.pipe.Producer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		OnCreate_btnCalculate();

		OnCreate_spinPCT();


		Button btn = (Button) findViewById(R.id.btnSocket);

		btn.setOnClickListener( new OnClicker() );		


		//		new Intent(this, MainActivity)
	}


	public class OnClicker implements OnClickListener {

		@Override
		public void onClick(View v) {
			executor.execute(new Producer(sharedQueue));

			
			ArrayList<String> arrayList = new ArrayList<String>();
			boolean hasMore = true;
			while(hasMore) {
				try {
					String s = sharedQueue.take();
					arrayList.add(s);
					hasMore = !Coordinator.END_OF_SEND.equals(s); 						
				} catch (InterruptedException ex) {
					Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
				}
			}


			Spinner pct = (Spinner) findViewById(R.id.spinPCT);
			String[] data = arrayList.toArray(new String[0]);
			pct.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item , data));
			

		}

	}


	private static class Downloader implements Runnable {

		private final MainActivity mainActivity;

		public Downloader(MainActivity mainActivity) {
			this.mainActivity = mainActivity;
		}

		@Override
		public void run() {


			Socket s = null;
			try
			{
				String host = "computing.dcu.ie";
				String file = "/~humphrys/howtomailme.html";
				int port = 80;

				s = new Socket(host, port);

				OutputStream out = s.getOutputStream();
				PrintWriter outw = new PrintWriter(out, false);
				outw.print("GET " + file + " HTTP/1.0\r\n");
				outw.print("Accept: text/plain, text/html, text/*\r\n");
				outw.print("\r\n");
				outw.flush();

				InputStream in = s.getInputStream();
				InputStreamReader inr = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(inr);
				String line;
				StringBuilder buf = new StringBuilder();
				
				while ((line = br.readLine()) != null)
				{
					buf.append(line);
				}

				TextView txtRes = (TextView) this.mainActivity.findViewById(R.id.txtResult);
				txtRes.setText("#"+buf.toString());				


			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (s != null) {
					try {
						s.close();
					} catch (IOException ignored) {
					}
				}
			}
		}		
	}


	private void OnCreate_btnCalculate() {
		Button btn = (Button) findViewById(R.id.btnCalculate);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText txt = (EditText) findViewById(R.id.txtEnterSum);
				float value = Float.parseFloat(txt.getText().toString());

				Spinner pct = (Spinner) findViewById(R.id.spinPCT);
				float pctValue = Float.parseFloat(pct.getSelectedItem().toString().trim().replace("%", ""));

				value *= pctValue/100.0f;


				TextView txtRes = (TextView) findViewById(R.id.txtResult);
				txtRes.setText(String.valueOf(value));				

			}
		});
	}

	private void OnCreate_spinPCT() {
//		Spinner pct = (Spinner) findViewById(R.id.spinPCT);
//
//		Resources res = getResources();		
//		String[] data = res.getStringArray(R.array.pct);
//
//		pct.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , data));
		//		pct.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item , data));
	}

	public void messageBox(String str,String str2)
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);                      
		dlgAlert.setMessage(str2);
		dlgAlert.setTitle(str);              
		dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
		finish(); 
	}

	private String exceptionToString(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); 
	}

}
