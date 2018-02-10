package elak.readinghood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

public class NewsFeedActivity extends AppCompatActivity {
    Threads threads;
    ListView listView;
    MyAdapter adapter;
    SearchView editsearch;
    //alex salah

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        try {
            threads = AppManager.getRecentThreadsOfNewsFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }


        listView=(ListView) findViewById(R.id.MyListView);
        adapter=new MyAdapter(this,threads);
        listView.setAdapter(adapter);

        editsearch = (SearchView) findViewById(R.id.Search);


        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
// do something on text submit
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }




}
