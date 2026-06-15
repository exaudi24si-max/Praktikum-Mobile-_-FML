package com.example.exaudiapps.pertemuan3

import android.Manifest
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.exaudiapps.R
import com.example.exaudiapps.databinding.ActivityThirdBinding
import com.example.exaudiapps.utils.NotificationHelper
import com.example.exaudiapps.utils.PermissionHelper
import com.example.exaudiapps.utils.ReminderHelper


class ThidActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }
        // Inisialisasi komponen
//        val noTujuan: EditText = findViewById(R.id.inputNoTujuan)
//        val btnKirim: Button = findViewById(R.id.btnKirim)

        binding.btnKirim.setOnClickListener {
            //Mengambil value dari inputNama dan menampilkan di Logcat
            val nomor = binding.inputNoTujuan.text
            Toast.makeText(this, "Pesan Berhasil Dikirim Ke $nomor", Toast.LENGTH_SHORT).show()
//
//            val intent = Intent(this, ThirdResultctivity::class.java)
////            startActivity(intent)

            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
            }
            ReminderHelper.setReminder(
                context = this, //Jika panggil di fragment maka requireContext()
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $nomor, reminder ini muncul 1 menit setelah tombol ditekan",
                targetActivity = ThirdResultctivity::class.java
            )
            Toast.makeText(
                this,
                "Silahkan tunggu 1 Menit untuk menerima Notifikasi...",
                Toast.LENGTH_SHORT
            ).show()
        }
//
//            NotificationHelper.showNotification(
//                this, //Jika panggil di fragment maka requireContext()
//                "Pesanan Anda",
//                "Halo $nomor, Pesanan Anda Sedang Diproses",
//                intent
//            )
    }

}


