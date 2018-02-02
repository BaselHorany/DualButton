package basel.com.DualButtonSample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basel.DualButton.DualButton;


public class MainActivity extends AppCompatActivity {

    DualButton dualBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        dualBtn = findViewById(R.id.dualBtn);

        dualBtn.setDualClickListener(new DualButton.OnDualClickListener() {
            public void onClickFirst(Button btn) {
                progressBar.setVisibility(View.VISIBLE);
                Toast tst = Toast.makeText(MainActivity.this, btn.getText().toString()+" clicked", Toast.LENGTH_SHORT);
                tst.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                tst.show();
            }
            public void onClickSecond(Button btn) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast tst = Toast.makeText(MainActivity.this, btn.getText().toString()+" clicked", Toast.LENGTH_SHORT);
                tst.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 100);
                tst.show();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
