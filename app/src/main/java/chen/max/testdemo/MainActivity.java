package chen.max.testdemo;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText edView;
    private TextView tvAdd;
    private TextView tvSave;
    private TextView tvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edView = (EditText) findViewById(R.id.ed_view);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvAdd.setOnClickListener(this);

        tvSave = (TextView) findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);
        tvContent = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                addImg();
                break;
            case R.id.tv_save:
                save();
                break;

        }
    }

    private void save() {
        String content = edView.getText().toString();
        Log.d(TAG, "save: " + content);

        SpannableString ss = new SpannableString(content);

        Pattern p = Pattern.compile("R.drawable.ic_launcher_background");

        Matcher m = p.matcher(content);
        while (m.find()) {
            ImageSpan span = new ImageSpan(this, R.drawable.ic_launcher_background);
            ss.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvContent.setText(ss);
    }


    private void addImg() {
        String path = "R.drawable.ic_launcher_background";
        SpannableString ss = new SpannableString(path + "\n");
        ImageSpan span = new ImageSpan(this, R.drawable.ic_launcher_background);
        ss.setSpan(span, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        Editable et = edView.getText();// 先获取Edittext中的内容
        int start = edView.getSelectionStart();
        et.insert(start, "\n");
        start = edView.getSelectionStart();
        et.insert(start, ss);// 设置ss要添加的位置n
        edView.setText(et);// 把et添加到Edittext中


        edView.setSelection(start + ss.length());// 设置Edittext中光标在最后面显示

    }
}
