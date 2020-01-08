package ru.gonchar17narod.goncharoom.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_entity.*
import ru.gonchar17narod.goncharoom.R

class AddEntityActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entity)

        button_save.setOnClickListener {

            if (edit_entity.text.isNotBlank()) {
                setResult(
                    Activity.RESULT_OK,
                    Intent().apply {
                        putExtra(EXTRA_REPLY, edit_entity.text.toString())
                    }
                )
            } else {
                setResult(
                    Activity.RESULT_CANCELED,
                    Intent()
                )
            }
            finish()
        }
    }
}