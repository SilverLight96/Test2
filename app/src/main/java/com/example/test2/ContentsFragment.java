package com.example.test2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test2.ui.YouTubeAdapter;
import com.example.test2.ui.YouTubeContent;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class ContentsFragment extends Fragment {
    YouTubePlayerView youTubePlayerView;
    View rootview;
    View textview;
    Context context;
//    ListView list;
    YouTubePlayer.OnInitializedListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.youtube_view, container, false);
        //textview = inflater.inflate(R.layout.card_youtube_content,container,false);
        List<YouTubeContent> contents = new ArrayList<>();
        List<String> text = new ArrayList<>();
        context = container.getContext();
        //라운드숄더 교정루틴 A
        contents.add(new YouTubeContent("qMtyhDDmJ-U"));
        //거북목 교정법
        contents.add(new YouTubeContent("Io5NYpzfsEU"));
        //[후기]5분만에 라운드 숄더, 굽은등 펴기
        contents.add(new YouTubeContent("tzJbwflDPhY"));
//        TextView text1=(TextView)textview.findViewById(R.id.textView2);
//        text1.setText(text.get(2));
        //textView.setText(text.get(2));
//        CardView cardView= (CardView)rootview.findViewById(R.id.card_you_tube_content);

        ListView list = (ListView)rootview.findViewById(R.id.list);
        final List<String>data = new ArrayList<>();

        //listview에 추가하는 자료
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        data.add("라운드숄더 교정루틴A");
        data.add("거북목 교정법");
        data.add("[후기]5분만에 라운드 숄더, 굽은등 펴기");
        adapter.notifyDataSetChanged();

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("DELETE")
                        .setMessage("Really want to Delete?")
                        .setNegativeButton("No",null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //삭제 클릭시 아래꺼
                                data.remove(position);
                                adapter.notifyDataSetChanged();
                                Snackbar.make(view,"Delete Complete",2000).show();
                            }
                        })
                        .show();
                return true;
            }
        });


        LayoutInflater inflater1 = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.card_youtube_content,container,false);
        TextView text1=view.findViewById(R.id.textView2);


        RecyclerView recyclerView = (RecyclerView)rootview.findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));
        recyclerView.scrollToPosition(contents.size()-1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new YouTubeAdapter(contents));
//textview로 시도해보면 어떨까?
//        View.OnClickListener listener=new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch(v.getId())
//                {
//                    case R.id.imageView:
//                        text.
//
//                }
//
//            }
//        };
//        ImageView button = (ImageView) imageview.findViewById(R.id.imageView);
//        button.setOnClickListener(listener);
        //유튜브 자동재생 방지
        //getViewLifecycleOwner().getLifecycle().addObserver(youTubePlayerView);
        return rootview;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        youTubePlayerView.release();
//    }
}
//class FavoirteList extends ListFragment{
//    private String[] numbers=new String[]{"1","2","3"};
//    public void onActivityCreated(Bundle savedInstanceState) {
//
//        super.onActivityCreated(savedInstanceState);
//        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, numbers));
//        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); }
//
//    public void onListItemClick(ListView I,View v,int position,long id) {
//        getListView().setItemChecked(position, true);
//    }
//}





