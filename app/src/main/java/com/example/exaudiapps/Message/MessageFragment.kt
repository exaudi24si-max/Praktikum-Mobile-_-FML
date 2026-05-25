package com.example.exaudiapps.Message

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudiapps.Message.tutorial.TutorialMessageActivity
import com.example.exaudiapps.R
import com.example.exaudiapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://i.pravatar.cc/150?u=1"),
        MessageModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?u=2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?u=3"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?u=4"),
        MessageModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?u=5"),
        MessageModel("Fajar", "Lagi ngapain?", "https://i.pravatar.cc/150?u=6"),
        MessageModel("Gita", "Boleh minta tolong?", "https://i.pravatar.cc/150?u=7"),
        MessageModel("Hana", "Lihat email ya", "https://i.pravatar.cc/150?u=8"),
        MessageModel("Irfan", "Oke noted", "https://i.pravatar.cc/150?u=9"),
        MessageModel("Joko", "Sampai jumpa besok", "https://i.pravatar.cc/150?u=10"),
        MessageModel("Kania", "Kapan kita kumpul?", "https://i.pravatar.cc/150?u=11"),
        MessageModel("Lutfi", "Data sudah saya kirim", "https://i.pravatar.cc/150?u=12"),
        MessageModel("Maya", "Terima kasih infonya", "https://i.pravatar.cc/150?u=13"),
        MessageModel("Nanda", "Sama-sama", "https://i.pravatar.cc/150?u=14"),
        MessageModel("Oki", "Otw lokasi ya", "https://i.pravatar.cc/150?u=15")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Message"
        }
        setHasOptionsMenu(true)

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
