package com.example.orwma_lv8

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import layout.ItemClickType
import layout.PersonRecyclerAdapter

class MainActivity : AppCompatActivity(),
    PersonRecyclerAdapter.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: PersonRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.personList)

        db.collection("persons")
            .get()
            .addOnSuccessListener {
                val personList = ArrayList<Person>()
                for (data in it.documents) {
                    val person = data.toObject(Person::class.java)
                    if (person != null) {
                        person.id = data.id
                        personList.add(person)
                    }
                }
                recyclerAdapter = PersonRecyclerAdapter(personList,
                    this@MainActivity)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.",
                    exception)
            }

        val saveBtn = findViewById<Button>(R.id.addSaveButton)
        saveBtn.setOnClickListener {
            val person = Person()
            person.name = findViewById<EditText>(R.id.nameSaveEditText).text.toString()
            person.imageUrl = findViewById<EditText>(R.id.imageSaveEditText).text.toString()
            person.description = findViewById<EditText>(R.id.descriptionSaveEditText).text.toString()
            db.collection("persons")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    person.id = documentReference.id
                    recyclerAdapter.addItem(person)
                    Log.d("MainActivity", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("MainActivity", "Error adding document", e)
                }
        }
    }

    override fun onItemButtonClick(index: Int, person: Person, clickType:
    ItemClickType
    ) {
        if (clickType == ItemClickType.EDIT) {
            db.collection("persons")
                .document(person.id)
                .set(person)
        }
        else if (clickType == ItemClickType.REMOVE) {
            recyclerAdapter.removeItem(index)
            db.collection("persons")
                .document(person.id)
                .delete()
        }
    }
}