package ru.gonchar17narod.goncharoom.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.gonchar17narod.goncharoom.R
import ru.gonchar17narod.goncharoom.business.Entity
import ru.gonchar17narod.goncharoom.view.adapters.EntitiesListAdapter
import ru.gonchar17narod.goncharoom.view.view_models.EntityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var entityViewModel: EntityViewModel
    private val newWordActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter =
            EntitiesListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager =
            ChipsLayoutManager.newBuilder(this)
                .build()

        entityViewModel = ViewModelProvider(this).get(EntityViewModel::class.java)
        entityViewModel.allEntities.observe(this, Observer { entities ->
            // Update the cached copy of the words in the adapter.
            entities?.let { adapter.setEntities(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEntityActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddEntityActivity.EXTRA_REPLY)?.let {
                val entity = Entity(it)
                entityViewModel.insert(entity)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}
