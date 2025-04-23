package com.example.conjugateit.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conjugateit.R

class MainActivity : AppCompatActivity() {

    data class MenuItem(val id: Int, val title: String, val param: String)

    private inner class MenuAdapter(
        private val items: List<MenuItem>,
        private val onClick: (MenuItem) -> Unit
    ) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val button: android.widget.Button = view.findViewById(R.id.button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_button, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            with(holder) {
                button.text = item.title
                button.setOnClickListener { onClick(item) }
            }
        }

        override fun getItemCount() = items.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuItems = listOf(
            MenuItem(1, "Present", "present"),
            MenuItem(2, "Future", "future"),
            MenuItem(3, "Imperfect", "imperfect"),
            MenuItem(4, "Simple Past", "simple_past"),
            MenuItem(5, "Past Participle", "past_participle"),
            MenuItem(6, "Present Participle", "present_participle"),
            MenuItem(7, "Gerund", "gerund"),
            MenuItem(8, "Imperative", "imperative"),
            MenuItem(9, "Conditional", "conditional"),
            MenuItem(10, "Present Subjunctive", "present_subjunctive"),
            MenuItem(11, "Imperfect Subjunctive", "imperfect_subjunctive")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuAdapter(menuItems) { item ->
            val intent = Intent(this, QuizActivity::class.java).apply {
                putExtra("TENSE", item.param)
                putExtra("NAME", item.title)
            }
            startActivity(intent)
        }

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.divider)!!)
            }
        )
    }
}