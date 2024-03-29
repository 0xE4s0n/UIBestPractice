package eason.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<Msg>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private  MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMags();// 初始化消息数据
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content))
                {
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);//当有新消息时刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    inputText.setText("");// 清空输入框中的内容
                }
                else
                {
                    Msg msg = new Msg("呸",Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);//当有新消息时刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    inputText.setText("");// 清空输入框中的内容
                }
            }
        });
    }

    private void initMags() {
        Msg msg1 =new Msg("Hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 =new Msg("Hello who is that?.",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 =new Msg("This is Tom. Nice talking yo you.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
