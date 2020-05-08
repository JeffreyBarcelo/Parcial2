package com.example.formativa_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText nombre, identificacion,apto, salario, edtBuscar;
    Button guardar, listar,actualizar,eliminar;

    Usuarios usuarios;
    Spinner spinner_estrato,spinner_nivel;
    String estrato;
    String nivel;

    BaseDatos uc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nombre = findViewById(R.id.txt_name);
        identificacion = findViewById(R.id.txt_id);
        guardar = findViewById(R.id.btn_guardar);
        listar = findViewById(R.id.btn_lista);
        spinner_estrato=(Spinner) findViewById((R.id.txt_apto));
        spinner_nivel=(Spinner) findViewById((R.id.edtHora));
        edtBuscar = (EditText) findViewById(R.id.edtBuscar);
        actualizar = findViewById(R.id.btn_actualizar);
        eliminar = findViewById(R.id.btn_eliminar);
        salario = findViewById(R.id.edtFecha);
        Button btnBuscar = (Button)findViewById(R.id.btnBuscar);
        uc = new BaseDatos(getApplicationContext());

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = uc.buscar(edtBuscar.getText().toString());
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No se pudo obtener la consulta", Toast.LENGTH_SHORT).show();                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("CEDULA: "+ res.getString(0)+"\n");
                    buffer.append("NOMBRE: "+ res.getString(1)+"\n");
                    buffer.append("TU ESTRATO: "+ res.getString(2)+"\n");
                    buffer.append("TU SALARIO: "+ res.getString(3)+"\n");
                    buffer.append("TU NIVEL EDUCATIVO: "+ res.getString(4)+"\n"+"\n");
                }
                Mostrar("CONSULTA",buffer.toString());

            }
        });


        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean actualizar= uc.actualizarDatos(identificacion.getText().toString(),nombre.getText().toString(), spinner_estrato.getSelectedItem().toString(), salario.getText().toString(),spinner_nivel.getSelectedItem().toString());          if (actualizar == true){
                    Toast.makeText(MainActivity.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }


            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer eliminar = uc.eliminarDatos(identificacion.getText().toString());
                if(eliminar > 0){
                    Toast.makeText(MainActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No se pudo eliminar, pa' la proxima quizas, pero hoy no se pudo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean insertar = uc.insertarDato(identificacion.getText().toString(),nombre.getText().toString(), spinner_estrato.getSelectedItem().toString(), salario.getText().toString(),spinner_nivel.getSelectedItem().toString());
                if (insertar == true){
                    Toast.makeText(MainActivity.this, "Dato insertado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Dato no insertado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = uc.obtenerDatos();
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No se pudieron mostrar los datos", Toast.LENGTH_SHORT).show();                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append( res.getString(0)+"\n");
                    buffer.append( res.getString(1)+"\n");
                    buffer.append( res.getString(2)+"\n");
                    buffer.append(res.getString(3)+"\n");
                    buffer.append(res.getString(4)+"\n"+"\n");
                }
                verDatos("RESULTADO",buffer.toString());
            }
        });


    }

    private void verDatos(String titulo,String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.show();


    }

    private void Mostrar(String titulo,String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        estrato =(String)spinner_estrato.getSelectedItem();
        nivel = (String)spinner_nivel.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
