package es.xuan.appbasic;

import android.os.Bundle;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etLog;
    Button btCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        inicializarPantalla();
    }

    private void inicializarPantalla() {
        etLog = findViewById(R.id.etLog);
        btCerrar = findViewById(R.id.btCerrar);
        // Configurar el listener para cerrar la aplicación
        btCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finalizar la actividad actual (cerrar la aplicación)
                finish();
            }
        });
    };

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if ((event.getSource() & InputDevice.SOURCE_JOYSTICK) == InputDevice.SOURCE_JOYSTICK &&
                event.getAction() == MotionEvent.ACTION_MOVE) {
            // Leer ejes del joystick
            float xAxis = event.getAxisValue(MotionEvent.AXIS_X);
            float yAxis = event.getAxisValue(MotionEvent.AXIS_Y);

            // Hacer algo con los valores
            System.out.println("Eje X: " + xAxis + ", Eje Y: " + yAxis);
            String str = etLog.getText() + "Eje X: " + xAxis + ", Eje Y: " + yAxis + "\n";
            etLog.setText(str);
            return true;
        }
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Detectar pulsaciones de botones
        System.out.println("Botón presionado: " + KeyEvent.keyCodeToString(keyCode));
        String str = etLog.getText() + "Botón presionado: " + KeyEvent.keyCodeToString(keyCode) + "\n";
        etLog.setText(str);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Detectar liberación de botones
        System.out.println("Botón liberado: " + KeyEvent.keyCodeToString(keyCode));
        String str = etLog.getText() + "Botón liberado: " + KeyEvent.keyCodeToString(keyCode) + "\n";
        etLog.setText(str);
        return super.onKeyUp(keyCode, event);
    }
}