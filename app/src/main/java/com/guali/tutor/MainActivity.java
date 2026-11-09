package com.guali.tutor;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private EditText editorCodigo;
    private TextView panelConsola;
    private ScrollView scrollConsola;
    private MaterialButton botonCompilar;
    private MaterialButton botonEjecutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editorCodigo = findViewById(R.id.editorCodigo);
        panelConsola = findViewById(R.id.panelConsola);
        scrollConsola = findViewById(R.id.scrollConsola);
        botonCompilar = findViewById(R.id.botonCompilar);
        botonEjecutar = findViewById(R.id.botonEjecutar);

        configurarMenu();
        configurarEditor();
        configurarBotones();
    }

    private void configurarMenu() {
        asignarOpcionMenu(R.id.menuLecciones, R.string.menu_lecciones);
        asignarOpcionMenu(R.id.menuConfiguracion, R.string.menu_configuracion);
        asignarOpcionMenu(R.id.menuHistorial, R.string.menu_historial);
        asignarOpcionMenu(R.id.menuAyuda, R.string.menu_ayuda);
    }

    private void asignarOpcionMenu(int idVista, int idTexto) {
        View opcion = findViewById(idVista);
        opcion.setOnClickListener(v -> escribirEnConsola(getString(R.string.consola_menu, getString(idTexto))));
    }

    private void configurarEditor() {
        editorCodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                botonEjecutar.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void configurarBotones() {
        botonCompilar.setOnClickListener(v -> compilar());
        botonEjecutar.setOnClickListener(v -> ejecutar());
    }

    private void compilar() {
        String codigo = editorCodigo.getText().toString().trim();
        if (codigo.isEmpty()) {
            botonEjecutar.setEnabled(false);
            escribirEnConsola(getString(R.string.consola_editor_vacio));
            return;
        }
        botonEjecutar.setEnabled(true);
        escribirEnConsola(getString(R.string.consola_compilacion_exitosa));
    }

    private void ejecutar() {
        escribirEnConsola(getString(R.string.consola_ejecutando));
        escribirEnConsola(getString(R.string.consola_resultado));
    }

    private void escribirEnConsola(String mensaje) {
        panelConsola.append("\n" + mensaje);
        scrollConsola.post(() -> scrollConsola.fullScroll(View.FOCUS_DOWN));
    }
}