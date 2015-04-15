package com.alexkolpa.fabtoolbar.sample;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.alexkolpa.fabtoolbar.FabToolbar;


public class SampleActivity extends ActionBarActivity implements View.OnClickListener {

	private FabToolbar fabToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);

		fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));

		fabToolbar.setColor(getResources().getColor(R.color.red));

		findViewById(R.id.event).setOnClickListener(this);
		findViewById(R.id.attach).setOnClickListener(this);
		findViewById(R.id.reply).setOnClickListener(this);

		ListView listView = (ListView) findViewById(R.id.list_view);
		fillListView(listView);
		fabToolbar.attachToListView(listView);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, R.string.button_clicked, Toast.LENGTH_SHORT).show();
		fabToolbar.hide();
	}

	private void fillListView(ListView view) {
		List<String> list = new ArrayList<String>(100);
		for(int i = 0; i < 100; i++) {
			list.add("Item " + i);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		view.setAdapter(adapter);
	}
}
