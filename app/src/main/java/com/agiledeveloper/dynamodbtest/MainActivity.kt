package com.agiledeveloper.dynamodbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.agiledeveloper.dynamodbtest.databinding.ActivityMainBinding
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.datastore.generated.model.Happiness


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configureAmplify()

        fun create(score: Int) {
            val item: Happiness = Happiness.builder()
                .score(score)
                .build()
            Amplify.DataStore.save(
                item,
                { success -> Log.i("Amplify", "Saved item: " + success.item().score) },
                { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
            )
        }

        binding.btnOne.setOnClickListener { create(1) }
        binding.btnTwo.setOnClickListener { create(2) }
        binding.btnThree.setOnClickListener { create(3) }
        binding.btnFour.setOnClickListener { create(4) }
        binding.btnFive.setOnClickListener { create(5) }

    }

    private fun configureAmplify() {
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("Amplify", "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e("Amplify", "Could not initialize Amplify", e)
        }

    }
}



//fun update() {
//    Amplify.DataStore.save(
//        updatedItem,
//        { success -> Log.i("Amplify", "Updated item: " + success.item().name) },
//        { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
//    )
//}
//
//fun delete() {
//    Amplify.DataStore.delete(toDeleteItem,
//        { deleted -> Log.i("Amplify", "Deleted item.") },
//        { failure -> Log.e("Amplify", "Delete failed.", failure) }
//    )
//}
//
//fun query() {
//    Amplify.DataStore.query(
//        Happiness::class.java,
//        { items ->
//            while (items.hasNext()) {
//                val item = items.next()
//                Log.i("Amplify", "Queried item: " + item.id)
//            }
//        },
//        { failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
//    )
//}
