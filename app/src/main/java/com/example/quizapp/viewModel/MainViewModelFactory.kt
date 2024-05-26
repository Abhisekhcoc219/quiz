package com.example.quizapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.model.ListOfQuizModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory:ViewModelProvider.Factory {
    private val listOfQuiz= listOf(
        ListOfQuizModel("What is the capital of France?", R.drawable.paris_ans,
        listOf("Berlin","Madrid", "Paris", "London"),"Paris"),
        ListOfQuizModel("Which element has the chemical symbol 'O'?",
        R.drawable.oxygen_cyclinder,
        listOf("Gold","Oxygen", "Hydrogen", "Carbon"),"Oxygen"),
        ListOfQuizModel(" Who wrote 'Romeo and Juliet'",
        R.drawable.willam_shakespear,
        listOf("Mark Twain"," Charles Dickens", "William Shakespeare", "Jane Austen"),"William Shakespeare"),
        ListOfQuizModel("What is the largest planet in our Solar System?",
        R.drawable.neptune,
        listOf("Earth","Mars","Jupiter", "Saturn"),"Jupiter"),
        ListOfQuizModel("What is the boiling point of water at sea level in Celsius?",
        R.drawable.thermometers,
        listOf("50°C","75°C","100°C", "150°C"),"100°C"),
        ListOfQuizModel(" Who painted the Mona Lisa?",
        R.drawable.painted,
        listOf("Vincent van Gogh ","Pablo Picasso " ,"Leonardo da Vinci", "Michelangelo"),"Leonardo da Vinci"),
        ListOfQuizModel("What is the smallest prime number?",
        R.drawable.prime_number,
        listOf("0","1","2","3"),"2"),
        ListOfQuizModel("Which planet is known as the Red Planet?", R.drawable.mars_dessert,
        listOf("Venus", "Mars", "Mercury", "Neptune"),"Mars"),
        ListOfQuizModel(" In which year did the Titanic sink?",
        R.drawable.titanic_sink,
        listOf("1905","1912","1923", "1931"),"1912"),
        ListOfQuizModel("What is the hardest natural substance on Earth?",
        R.drawable.diamond,
        listOf("Gold","Iron","Diamond","Platinum"),"Diamond")
    )
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(listOfQuiz) as T
    }

}