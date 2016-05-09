package com.appspot.bgje0341.coursera;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputLayout tiNombre;
    private DatePicker dpFecha;
    private TextInputLayout tiTelefono;
    private TextInputLayout tiEmail;
    private TextInputLayout tiDescripcion;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle parametros = getIntent().getExtras();

        tiNombre = (TextInputLayout)findViewById(R.id.itNombre);
        dpFecha = (DatePicker)findViewById(R.id.fecha);
        tiTelefono = (TextInputLayout)findViewById(R.id.itTelefono);
        tiEmail = (TextInputLayout)findViewById(R.id.itEmail);
        tiDescripcion = (TextInputLayout)findViewById(R.id.itDescripcion);
        btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);
        if (parametros!=null){
            setTexto(R.id.nombre, parametros.getString("N"));

            setTexto(R.id.telefono, parametros.getString("T"));
            setTexto(R.id.email,parametros.getString("E"));
            setTexto(R.id.descripcion,parametros.getString("D"));
        }

        tiNombre.setCounterMaxLength(100);
        tiNombre.setCounterEnabled(true);
        Date fecha = new Date();
        dpFecha.setMaxDate(fecha.getTime());
        tiTelefono.setCounterMaxLength(10);
        tiTelefono.setCounterEnabled(true);
        tiEmail.setCounterMaxLength(255);
        tiEmail.setCounterEnabled(true);
        tiDescripcion.setCounterMaxLength(255);
        tiDescripcion.setCounterEnabled(true);
    }


    @Override
    public void onClick(View v) {

        String fecha= dpFecha.getDayOfMonth()+"/"+(dpFecha.getMonth()+1)+"/"+dpFecha.getYear();
        Intent intent = new Intent(this,Detalle.class);
        if(getTexto(R.id.nombre)!=null && getTexto(R.id.telefono)!=null && getTexto(R.id.email)!=null
                &&getTexto(R.id.descripcion)!=null ){
            intent.putExtra("N",getTexto(R.id.nombre));
            intent.putExtra("F",fecha);
            if(getTexto(R.id.telefono).length()>=8){
                intent.putExtra("T",getTexto(R.id.telefono));
            }else{
                EditText edTel = (EditText)busca(R.id.telefono);
                edTel.setError("porfavor ingresa un numero valido");
            }
            intent.putExtra("D", getTexto(R.id.descripcion));
            String valida="[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
                    +"(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                    +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
                    +"[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
            if (getTexto(R.id.email).matches(valida)){
                intent.putExtra("E",getTexto(R.id.email));
                startActivity(intent);
            }else{
                EditText edEmail = (EditText)busca(R.id.email);
                edEmail.setError("porfavor ingresa un correo valido");
            }

        }
    }

    public String getTexto(int editText){
        
        EditText texto = (EditText)busca(editText);
        if(texto.getText().toString().isEmpty()){
            texto.setError("El campo no puede estar vacio");
            return null;
        }else {
            texto.setError(null);
            return texto.getText().toString();
        }

    }

    public void setTexto(int editText,String texto){

        EditText edit = (EditText)busca(editText);
        edit.setText(texto);
    }

    public  View busca(int idView) {
        final View v = findViewById(idView);
        if (v == null) {
            throw new NullPointerException("no se encontro view");
        } else {
            return v;
        }
    }

}
