package com.nicofrnds02734.latihanstorages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "konten.txt";
    TextView tampil;
    Button btn_hapus, btn_buat, btn_ubah, btn_baca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tampil = findViewById(R.id.tv_tampil);
        btn_baca = findViewById(R.id.btn_baca);
        btn_buat = findViewById(R.id.btn_buat);
        btn_hapus = findViewById(R.id.btn_hapus);
        btn_ubah = findViewById(R.id.btn_ubah);

        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buatFile();
            }
        });
        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusFile();
            }
        });
        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahFile();
            }
        });
        btn_baca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bacaFile();
            }
        });
    }

    private void ubahFile() {
        String ubah = "Semangat Pagi! Pagi, Pagi, Pagi!";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "File telah diubah", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private void bacaFile() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }
            tampil.setText(sb.toString());

        } catch (FileNotFoundException e) {
            tampil.setText("Tidak ada file yang ditulis !" );
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()){
            file.delete();
            tampil.setText("");
            Toast.makeText(this, "File Berhasil dihapus !", Toast.LENGTH_SHORT).show();
        }
    }

    private void buatFile() {
        String isiFile = "\n Coba isi data pada file ini";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(this, "Berhasil di simpan !", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}