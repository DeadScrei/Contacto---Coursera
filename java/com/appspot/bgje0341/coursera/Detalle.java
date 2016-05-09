package com.appspot.bgje0341.coursera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detalle extends AppCompatActivity implements View.OnClickListener {

    private Button btnEditar;
    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle);

        Bundle parametros = getIntent().getExtras();

        tvNombre = (TextView) findViewById(R.id.d_Nombre);
        tvFecha = (TextView) findViewById(R.id.d_Fecha);
        tvTelefono = (TextView) findViewById(R.id.d_Telefono);
        tvEmail = (TextView) findViewById(R.id.d_Email);
        tvDescripcion = (TextView) findViewById(R.id.d_Descripcion);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);
        tvTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvTelefono.getText().toString())));
            }
        });

        tvNombre.setText(parametros.getString("N"));
        tvFecha.setText(parametros.getString("F"));
        tvTelefono.setText(parametros.getString("T"));
        tvEmail.setText(parametros.getString("E"));
        tvDescripcion.setText(parametros.getString("D"));

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("N",tvNombre.getText().toString());
        intent.putExtra("F",tvFecha.getText().toString());
        intent.putExtra("T",tvTelefono.getText().toString());
        intent.putExtra("E",tvEmail.getText().toString());
        intent.putExtra("D",tvDescripcion.getText().toString());
        startActivity(intent);
    }
}
