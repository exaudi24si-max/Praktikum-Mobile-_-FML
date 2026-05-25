package com.example.exaudiapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.example.exaudiapps.data.api.CatFactApiClient
import com.example.exaudiapps.databinding.FragmentHomeBinding
import com.example.exaudiapps.pertemuan2.SecondActivity
import com.example.exaudiapps.pertemuan3.ThidActivity
import com.example.exaudiapps.pertemuan4.FourthActivity
import com.example.exaudiapps.pertemuan5.FifthActivity
import com.example.exaudiapps.pertemuan6.AuthActivity
import com.example.exaudiapps.pertemuan7.SevenActivity
import com.example.exaudiapps.Home.pertemuan_9.NinthActivity
import com.example.exaudiapps.Home.Pertemuan10.TenthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // Setup Toolbar secara aman
        val activity = activity
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(binding.toolbar)
            activity.supportActionBar?.title = "Home"
        }

        // Memanggil fungsi loadCatFact saat pertama kali fragment dibuat
        loadCatFact()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        binding.btnP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.btnP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThidActivity::class.java))
        }
        binding.btnP4.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }
        binding.btnP5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        binding.btnP6.setOnClickListener {
            startActivity(Intent(requireContext(), AuthActivity::class.java))
        }
        binding.btnP7.setOnClickListener {
            startActivity(Intent(requireContext(), SevenActivity::class.java))
        }
        binding.btnP9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        binding.btnP10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
        binding.bntLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit() {
                        clear()
                        apply()
                    }
                    requireActivity().finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    private fun loadCatFact() {
        // PERBAIKAN: Menggunakan viewLifecycleOwner agar proses otomatis berhenti saat pindah halaman
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // PERBAIKAN: Menggunakan _binding? untuk menghindari NullPointerException
                _binding?.tvCatFact?.text = "Loading cat fact..."
                val response = CatFactApiClient.apiService.getCatFact()
                _binding?.tvCatFact?.text = "\"${response.fact}\""
            } catch (e: Exception) {
                _binding?.tvCatFact?.text = "Gagal mengambil fakta kucing."
                Log.e("Retrofit Error", e.message.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
